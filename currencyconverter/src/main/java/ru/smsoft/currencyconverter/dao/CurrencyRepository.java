package ru.smsoft.currencyconverter.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.smsoft.currencyconverter.model.Currency;

import java.util.Optional;

@Repository
public interface CurrencyRepository extends JpaRepository<Currency,String> {

    Optional<Currency> findCurrencyByCurrencyId(String id);
}
