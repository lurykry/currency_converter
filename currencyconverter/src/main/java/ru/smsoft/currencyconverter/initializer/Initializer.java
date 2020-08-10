package ru.smsoft.currencyconverter.initializer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import ru.smsoft.currencyconverter.controller.MainController;
import ru.smsoft.currencyconverter.exception.FailedToGetCurrencyXmlException;
import ru.smsoft.currencyconverter.exception.FailedToParseCurrencyXmlException;
import ru.smsoft.currencyconverter.model.Currency;
import ru.smsoft.currencyconverter.service.currency.CurrencyService;
import ru.smsoft.currencyconverter.xmlparser.CurrencyXmlParser;

import java.util.List;
@Component
public class Initializer {

    private static final Logger LOGGER = LoggerFactory.getLogger(MainController.class);

    private final CurrencyService service;

    @Autowired
    public Initializer(CurrencyService service) {
        this.service = service;
    }

    @EventListener(ApplicationReadyEvent.class)
    public void initialize(){
        CurrencyXmlParser parser = new CurrencyXmlParser();
        List<Currency> currencies;
        try {
            currencies = parser.getCurrencies();
            service.saveAllCurrencies(currencies);
        } catch (FailedToGetCurrencyXmlException | FailedToParseCurrencyXmlException e) {
            LOGGER.error("initializer: initialize(): ",e);
            throw new RuntimeException("failed to initialize");
        }
    }
}
