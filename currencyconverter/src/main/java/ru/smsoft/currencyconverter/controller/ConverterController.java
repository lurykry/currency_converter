package ru.smsoft.currencyconverter.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.smsoft.currencyconverter.dto.ConverterDto;
import ru.smsoft.currencyconverter.dto.ServerResponse;
import ru.smsoft.currencyconverter.exception.FailedToUpdateCurrencyException;
import ru.smsoft.currencyconverter.model.ConversionHistory;
import ru.smsoft.currencyconverter.model.Currency;
import ru.smsoft.currencyconverter.model.User;
import ru.smsoft.currencyconverter.config.security.UserDecorator;
import ru.smsoft.currencyconverter.service.convhistory.ConversionHistoryService;
import ru.smsoft.currencyconverter.service.currency.CurrencyService;
import ru.smsoft.currencyconverter.util.Rounder;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@RestController
@RequestMapping("/converter")
public class ConverterController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ConverterController.class);

    private static final String PATTERN = "yyyy.MM.dd";
    private static final String SUCCESS = "200";
    private static final String MESSAGE = "The currency rate we have might be a bit outdated.." +
            "last date of update is: ";
    private static final Long EXTRA_DAY = 1L;

    private final CurrencyService currencyService;
    private final ConversionHistoryService conversionHistoryService;

    @Autowired
    public ConverterController(CurrencyService currencyService, ConversionHistoryService conversionHistoryService) {
        this.currencyService = currencyService;
        this.conversionHistoryService = conversionHistoryService;
    }

    @PostMapping (consumes = "application/json", produces = "application/json")
    public ServerResponse convert(@RequestBody ConverterDto converterDto){

        boolean isCurrencyFromUpdateSucceeded;
        boolean isCurrencyToUpdateSucceeded;
        UserDecorator userDecorator = (UserDecorator) SecurityContextHolder
                    .getContext()
                    .getAuthentication()
                    .getPrincipal();

        User user = userDecorator.getUser();

        LocalDate dateOfConversion = LocalDate.parse(converterDto.getDateOfConversion(),
                DateTimeFormatter.ofPattern(PATTERN));

        Currency currencyFrom = currencyService.findCurrencyById(converterDto.getCurrencyFromId());
        Currency currencyTo = currencyService.findCurrencyById( converterDto.getCurrencyToId());

        boolean isCurrencyFromUpdated = isCurrencyUpdated(currencyFrom, dateOfConversion);

        boolean isCurrencyToUpdated = isCurrencyUpdated(currencyTo, dateOfConversion);

        isCurrencyFromUpdateSucceeded = secondCheckIfCurrencyHaveBeenUpdated(isCurrencyFromUpdated, currencyFrom, dateOfConversion);
        isCurrencyToUpdateSucceeded = secondCheckIfCurrencyHaveBeenUpdated(isCurrencyToUpdated, currencyTo, dateOfConversion);

        Double result = calculateResult(converterDto, currencyFrom, currencyTo);

        ConversionHistory conversionHistory = buildConversionHistory(converterDto, user, currencyFrom, currencyTo, result);
        conversionHistoryService.saveConversionHistory(conversionHistory);


        return (isCurrencyFromUpdateSucceeded && isCurrencyToUpdateSucceeded) ? new ServerResponse(result, SUCCESS,"") :
                new ServerResponse(result, SUCCESS,
                        (currencyFrom.getCurrencyDateOfUpdate().isBefore(currencyTo.getCurrencyDateOfUpdate()) ?
                                MESSAGE + currencyFrom.getCurrencyDateOfUpdate()
                                : MESSAGE + currencyTo.getCurrencyDateOfUpdate()));
    }


    private ConversionHistory buildConversionHistory(ConverterDto converterDto, User user, Currency currencyFrom,
                                                     Currency currencyTo ,double result){

        String currencyFromId = converterDto.getCurrencyFromId();
        String currencyToId = converterDto.getCurrencyToId();
        String currencyFromCharCode = converterDto.getCurrencyCharCodeFrom();
        String currencyToCharCode = converterDto.getCurrencyCharCodeTo();

        LocalDate dateOfConversion = LocalDate.parse(converterDto.getDateOfConversion(), DateTimeFormatter.ofPattern(PATTERN));
        Double amountOfMoney = converterDto.getAmountOfMoney();

        ConversionHistory conversionHistory = new ConversionHistory();
        conversionHistory.setDateOfConversion(dateOfConversion);
        conversionHistory.setUser(user);
        conversionHistory.setCurrencyCharCodeFrom(currencyFromCharCode);
        conversionHistory.setCurrencyCharCodeTo(currencyToCharCode);
        conversionHistory.setCurrencyFromId(currencyFromId);
        conversionHistory.setCurrencyToId(currencyToId);
        conversionHistory.setAmountOfMoneyFrom(amountOfMoney);
        conversionHistory.setAmountOfMoneyTo(result);
        conversionHistory.setCurrencyRate(Rounder.round(currencyFrom.getCurrencyValue() / currencyTo.getCurrencyValue(), 4));

        return conversionHistory;
    }

    private Double  calculateResult(ConverterDto converterDto, Currency currencyFrom, Currency currencyTo){
        return Rounder.round(converterDto.getAmountOfMoney() *
                (currencyFrom.getCurrencyValue() / currencyTo.getCurrencyValue()),4);
    }

    private boolean isCurrencyUpdated(Currency currency, LocalDate dateOfConversion){
        return currency.getCurrencyDateOfUpdate().equals(dateOfConversion)
                || currency.getCurrencyDateOfUpdate().equals(dateOfConversion.plusDays(EXTRA_DAY));
    }
    private boolean secondCheckIfCurrencyHaveBeenUpdated(boolean isUpdated, Currency currency, LocalDate dateOfConversion){
        if(!isUpdated){
            try {
                currency = currencyService.updateCurrencyById(currency.getCurrencyId());
                isUpdated = isCurrencyUpdated(currency, dateOfConversion);
                return isUpdated;

            } catch (FailedToUpdateCurrencyException e) {
                LOGGER.error("controller: updateCurrencyByIdMethod(): id -" + currency.getCurrencyId(), e);
                return false;
            }
        }
        return true;
    }
}
