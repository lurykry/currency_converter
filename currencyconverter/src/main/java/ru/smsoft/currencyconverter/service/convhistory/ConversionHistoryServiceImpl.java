package ru.smsoft.currencyconverter.service.convhistory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.smsoft.currencyconverter.dao.ConversionHistoryRepository;
import ru.smsoft.currencyconverter.model.ConversionHistory;
import ru.smsoft.currencyconverter.model.User;

import java.time.LocalDate;
import java.util.List;

@Service
@Transactional
public class ConversionHistoryServiceImpl implements ConversionHistoryService{

    private ConversionHistoryRepository conversionHistoryRepository;

    public ConversionHistoryServiceImpl() {
    }

    @Autowired
    public ConversionHistoryServiceImpl(ConversionHistoryRepository conversionHistoryRepository) {
        this.conversionHistoryRepository = conversionHistoryRepository;
    }

    @Override
    public List<ConversionHistory> findRecordsByDate(String date) {
        return conversionHistoryRepository.findRecordsByDateOfConversion(LocalDate.parse(date));
    }

    @Override
    public void saveConversionHistory(ConversionHistory conversionHistory) {
        conversionHistoryRepository.save(conversionHistory);
    }

    @Override
    public List<ConversionHistory> findConversionHistoryByUser(User user) {
        return conversionHistoryRepository.findConversionHistoriesByUser(user);
    }

    @Override
    public List<ConversionHistory> findAll() {
        return conversionHistoryRepository.findAll();
    }
}
