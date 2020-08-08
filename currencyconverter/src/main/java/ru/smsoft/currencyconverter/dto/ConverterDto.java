package ru.smsoft.currencyconverter.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class ConverterDto {

    @NotNull
    @NotEmpty
    private String currencyFromId;

    @NotNull
    @NotEmpty
    private String currencyToId;

    @NotNull
    @NotEmpty
    private String currencyCharCodeFrom;

    @NotNull
    @NotEmpty
    private String currencyCharCodeTo;

    @NotNull
    @NotEmpty
    private Double amountOfMoney;

    @NotNull
    @NotEmpty
    private String dateOfConversion;


    public ConverterDto() {
    }

    public ConverterDto(@NotNull @NotEmpty String currencyFromId, @NotNull @NotEmpty String currencyToId,
                        @NotNull @NotEmpty String currencyCharCodeFrom, @NotNull @NotEmpty String currencyCharCodeTo, @NotNull @NotEmpty Double amountOfMoney, @NotNull @NotEmpty String dateOfConversion) {
        this.currencyFromId = currencyFromId;
        this.currencyToId = currencyToId;
        this.currencyCharCodeFrom = currencyCharCodeFrom;
        this.currencyCharCodeTo = currencyCharCodeTo;
        this.amountOfMoney = amountOfMoney;
        this.dateOfConversion = dateOfConversion;
    }

    public String getCurrencyFromId() {
        return currencyFromId;
    }

    public void setCurrencyFromId(String currencyFromId) {
        this.currencyFromId = currencyFromId;
    }

    public String getCurrencyToId() {
        return currencyToId;
    }

    public void setCurrencyToId(String currencyToId) {
        this.currencyToId = currencyToId;
    }

    public Double getAmountOfMoney() {
        return amountOfMoney;
    }

    public void setAmountOfMoney(Double amountOfMoney) {
        this.amountOfMoney = amountOfMoney;
    }

    public String getDateOfConversion() {
        return dateOfConversion;
    }

    public void setDateOfConversion(String dateOfConversion) {
        this.dateOfConversion = dateOfConversion;
    }

    public String getCurrencyCharCodeFrom() {
        return currencyCharCodeFrom;
    }

    public void setCurrencyCharCodeFrom(String currencyCharCodeFrom) {
        this.currencyCharCodeFrom = currencyCharCodeFrom;
    }

    public String getCurrencyCharCodeTo() {
        return currencyCharCodeTo;
    }

    public void setCurrencyCharCodeTo(String currencyCharCodeTo) {
        this.currencyCharCodeTo = currencyCharCodeTo;
    }

    @Override
    public String toString() {
        return "ConverterDto{" +
                "currencyFromId='" + currencyFromId + '\'' +
                ", currencyToId='" + currencyToId + '\'' +
                ", currencyCharCodeFrom='" + currencyCharCodeFrom + '\'' +
                ", currencyCharCodeTo='" + currencyCharCodeTo + '\'' +
                ", amountOfMoney=" + amountOfMoney +
                ", dateOfConversion='" + dateOfConversion + '\'' +
                '}';
    }
}
