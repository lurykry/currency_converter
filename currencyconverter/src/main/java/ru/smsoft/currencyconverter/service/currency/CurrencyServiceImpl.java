package ru.smsoft.currencyconverter.service.currency;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.smsoft.currencyconverter.dao.CurrencyRepository;
import ru.smsoft.currencyconverter.exception.CurrencyNotFoundException;
import ru.smsoft.currencyconverter.exception.FailedToGetCurrencyXmlException;
import ru.smsoft.currencyconverter.exception.FailedToParseCurrencyXmlException;
import ru.smsoft.currencyconverter.exception.FailedToUpdateCurrencyException;
import ru.smsoft.currencyconverter.model.Currency;
import ru.smsoft.currencyconverter.xmlparser.CurrencyXmlParser;

@Service
@Transactional
public class CurrencyServiceImpl implements CurrencyService{

    private static final Logger LOGGER = LoggerFactory.getLogger(CurrencyService.class);
    private CurrencyRepository currencyRepository;
    private CurrencyXmlParser parser;

    public CurrencyServiceImpl() {
    }

    @Autowired
    public CurrencyServiceImpl(CurrencyRepository currencyRepository, CurrencyXmlParser parser) {
        this.currencyRepository = currencyRepository;
        this.parser = parser;
    }

    @Override
    public void saveAllCurrencies(Iterable<Currency> currencies){
        currencyRepository.saveAll(currencies);
    }


    @Override
    public void saveCurrency(Currency currency) {
        currencyRepository.save(currency);
    }

    @Override
    public Currency findCurrencyById(String id){
        return currencyRepository.findCurrencyByCurrencyId(id)
                .orElseThrow(() -> new CurrencyNotFoundException("no currency with such id"));
    }

    @Override
    public Currency updateCurrencyById(String id) throws FailedToUpdateCurrencyException {
        try {
            Currency currency =  parser.updateCurrencyInfo(id)
                    .orElseThrow(() -> new FailedToUpdateCurrencyException("failed to update currency"));
            currencyRepository.save(currency);
            return currency;
        } catch (FailedToGetCurrencyXmlException | FailedToParseCurrencyXmlException e) {
            LOGGER.error("service: updateCurrencyById(): id " + id, e);
            throw new FailedToUpdateCurrencyException("failed to update currency");
        }
    }
}
