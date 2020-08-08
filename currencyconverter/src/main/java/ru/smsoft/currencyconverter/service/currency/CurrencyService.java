package ru.smsoft.currencyconverter.service.currency;

import ru.smsoft.currencyconverter.exception.FailedToUpdateCurrencyException;
import ru.smsoft.currencyconverter.model.Currency;

public interface CurrencyService {

    void saveAllCurrencies(Iterable<Currency> currencies);

    void saveCurrency(Currency currency);
    Currency findCurrencyById(String id);

    Currency updateCurrencyById(String id) throws FailedToUpdateCurrencyException;
}
