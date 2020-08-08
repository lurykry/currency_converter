package ru.smsoft.currencyconverter.xmlparser;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import ru.smsoft.currencyconverter.exception.FailedToGetCurrencyXmlException;
import ru.smsoft.currencyconverter.exception.FailedToParseCurrencyXmlException;
import ru.smsoft.currencyconverter.model.Currency;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.StringReader;
import java.net.URISyntaxException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ExecutionException;

@Component
public class CurrencyXmlParser {

    private static final Logger LOGGER = LoggerFactory.getLogger(CurrencyXmlParser.class);

    private static final String DD_MM_YYYY = "dd.MM.yyyy";
    private static final String YYYY_MM_DD = "yyyy.MM.dd";

    public List<Currency> getCurrencies() throws FailedToGetCurrencyXmlException, FailedToParseCurrencyXmlException {
        CurrencyXmlHttpResponse response = new CurrencyXmlHttpResponse();
        String xml;
        List<Currency> currencies;
        try {
            xml = response.getCurrencyXml();
        } catch (URISyntaxException | IOException | InterruptedException | ExecutionException e) {
            LOGGER.error("xmlparser: getCurencies(): ", e);
            throw new FailedToGetCurrencyXmlException("failed to load xml file");
        }

        try {
            Document currencyXml = buildDocument(xml);
            currencies = getAllCurrenciesFromXml(currencyXml);

        } catch (ParserConfigurationException | IOException | SAXException e) {
            LOGGER.error("xmlparser: getCurencies(): ", e);
            throw new FailedToParseCurrencyXmlException("failed to parse xml file");
        }
        return currencies;
    }

    public Optional<Currency> updateCurrencyInfo(String currencyId) throws FailedToGetCurrencyXmlException, FailedToParseCurrencyXmlException {

        CurrencyXmlHttpResponse response = new CurrencyXmlHttpResponse();
        String xml;

        try {
            xml = response.getCurrencyXml();
        } catch (URISyntaxException | IOException | InterruptedException | ExecutionException e) {
            LOGGER.error("xmlparser: updateCurrencyInfo(): ", e);
            throw new FailedToGetCurrencyXmlException("failed to load xml file");
        }

        try {
            Document document = buildDocument(xml);
            LocalDate date = getDate(document);

            NodeList valutes = document.getElementsByTagName("Valute");
            for(int i = 0; i < valutes.getLength(); i++){
                Node valute = valutes.item(i);
                String id = valute.getAttributes().getNamedItem("ID").getNodeValue();
                if(currencyId.equals(id)){
                    Currency currency = getCurrencyFromXml(valute);
                    currency.setCurrencyDateOfUpdate(date);
                    return Optional.of(currency);
                }
            }

            return Optional.empty();
        } catch (ParserConfigurationException | IOException | SAXException e) {
            LOGGER.error("xmlparser: updateCurrencyInfo(): ", e);
            throw new FailedToParseCurrencyXmlException("failed to parse xml file");
        }


    }

    private List<Currency> getAllCurrenciesFromXml(Document document) {

        List<Currency> currencies = new ArrayList<>();
        NodeList valutes = document.getElementsByTagName("Valute");
        LocalDate date = getDate(document);


        for (int i = 0; i < valutes.getLength(); i++) {
            Node valute = valutes.item(i);
            Currency currency = getCurrencyFromXml(valute);
            currency.setCurrencyDateOfUpdate(date);
            currencies.add(currency);
        }
        return currencies;
    }

    private Currency getCurrencyFromXml(Node valute) {

        Currency currency = new Currency();

        Node nodeId = valute.getAttributes().getNamedItem("ID");
        String id = nodeId.getNodeValue();

        NodeList valuteInside = valute.getChildNodes();
        currency.setCurrencyId(id);

        for (int j = 0; j < valuteInside.getLength(); j++) {

            Node valuteInsideItem = valuteInside.item(j);
            String value = valuteInsideItem.getTextContent();
            String name = valuteInsideItem.getNodeName();

            switch (name) {
                case "NumCode" -> currency.setCurrencyNumCode(value);
                case "CharCode" -> currency.setCurrencyCharCode(value);
                case "Name" -> currency.setCurrencyName(value);
                case "Value" -> currency.setCurrencyValue(Double.parseDouble(value.replaceAll(",", ".")));
            }

        }

        return currency;
    }


    private LocalDate getDate(Document document){
        String stringDate = document.getDocumentElement().getAttribute("Date");
        String formattedDate = LocalDate.parse(
                stringDate,
                DateTimeFormatter.ofPattern(DD_MM_YYYY)
        ).format(DateTimeFormatter.ofPattern(YYYY_MM_DD));
        return LocalDate.parse(formattedDate, DateTimeFormatter.ofPattern(YYYY_MM_DD));
    }


    private Document buildDocument(String xml) throws ParserConfigurationException, IOException, SAXException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();

        return builder.parse(new InputSource(new StringReader(xml)));
    }
}


