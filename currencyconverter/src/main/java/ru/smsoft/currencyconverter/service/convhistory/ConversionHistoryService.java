package ru.smsoft.currencyconverter.service.convhistory;

import ru.smsoft.currencyconverter.model.ConversionHistory;
import ru.smsoft.currencyconverter.model.User;

import java.util.List;

public interface ConversionHistoryService {
    List<ConversionHistory> findRecordsByDate(String date);

    void saveConversionHistory(ConversionHistory conversionHistory);
    List<ConversionHistory> findConversionHistoryByUser(User user);
    List<ConversionHistory> findAll();
}
