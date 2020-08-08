package ru.smsoft.currencyconverter.exceptionhandler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import ru.smsoft.currencyconverter.exception.CurrencyNotFoundException;

@ControllerAdvice
public class ConverterControllerAdviser {

    private static final Logger LOGGER = LoggerFactory.getLogger(ConverterControllerAdviser.class);

    @ExceptionHandler(CurrencyNotFoundException.class)
    public ResponseEntity<CurrencyNotFoundException> handleCurrencyNotFoundException(RuntimeException e){
        LOGGER.error("controller: convert(): " + e);
        return new ResponseEntity<>(new CurrencyNotFoundException("currency not found"),
                HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
