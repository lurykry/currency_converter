package ru.smsoft.currencyconverter.dal;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.smsoft.currencyconverter.model.ConversionHistory;
import ru.smsoft.currencyconverter.model.User;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ConversionHistoryRepository extends JpaRepository<ConversionHistory,Long> {

    List<ConversionHistory> findRecordsByDateOfConversion(LocalDate date);
    List<ConversionHistory> findConversionHistoriesByUser(User user);
    List<ConversionHistory> findAll();
}
