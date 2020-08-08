package ru.smsoft.currencyconverter.model;

import ru.smsoft.currencyconverter.util.DateConverter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "conversion_history")
public class ConversionHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "record_id")
    private Long recordId;

    @Column(name = "currency_from_id")
    private String currencyFromId;

    @Column(name = "currency_to_id")
    private String currencyToId;

    @Column(name = "currency_from_char_code")
    private String currencyCharCodeFrom;

    @Column(name = "currency_to_char_code")
    private String currencyCharCodeTo;

    @Column(name = "amount_of_money_from")
    private Double amountOfMoneyFrom;

    @Column(name = "amount_of_money_to")
    private Double amountOfMoneyTo;

    @Convert(converter = DateConverter.class)
    @Column(name =  "date_of_conversion")
    private LocalDate dateOfConversion;

    @Column(name = "currency_rate")
    private Double currencyRate;

    @ManyToOne
    @JoinColumn(name = "user_user_id")
    private User user;

    public ConversionHistory() {
    }

    public ConversionHistory(String currencyFromId, String currencyToId,
                             String currencyCharCodeFrom, String currencyCharCodeTo, Double amountOfMoneyFrom,
                             Double amountOfMoneyTo, LocalDate dateOfConversion,
                             Double currencyRate, User user) {
        this.currencyFromId = currencyFromId;
        this.currencyToId = currencyToId;
        this.currencyCharCodeFrom = currencyCharCodeFrom;
        this.currencyCharCodeTo = currencyCharCodeTo;
        this.amountOfMoneyFrom = amountOfMoneyFrom;
        this.amountOfMoneyTo = amountOfMoneyTo;
        this.dateOfConversion = dateOfConversion;
        this.currencyRate = currencyRate;
        this.user = user;
    }

    public Long getRecordId() {
        return recordId;
    }

    public void setRecordId(Long recordId) {
        this.recordId = recordId;
    }

    public String getCurrencyFromId() {
        return currencyFromId;
    }

    public void setCurrencyFromId(String currencyFrom) {
        this.currencyFromId = currencyFrom;
    }

    public String getCurrencyToId() {
        return currencyToId;
    }

    public void setCurrencyToId(String currencyTo) {
        this.currencyToId = currencyTo;
    }

    public Double getAmountOfMoneyFrom() {
        return amountOfMoneyFrom;
    }

    public void setAmountOfMoneyFrom(Double moneyFrom) {
        this.amountOfMoneyFrom = moneyFrom;
    }

    public Double getAmountOfMoneyTo() {
        return amountOfMoneyTo;
    }

    public void setAmountOfMoneyTo(Double moneyTo) {
        this.amountOfMoneyTo = moneyTo;
    }

    public LocalDate getDateOfConversion() {
        return dateOfConversion;
    }

    public void setDateOfConversion(LocalDate conversionDate) {
        this.dateOfConversion = conversionDate;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Double getCurrencyRate() {
        return currencyRate;
    }

    public void setCurrencyRate(Double currencyRate) {
        this.currencyRate = currencyRate;
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
        return "ConversionHistory{" +
                "recordId=" + recordId +
                ", currencyFromId='" + currencyFromId + '\'' +
                ", currencyToId='" + currencyToId + '\'' +
                ", currencyCharCodeFrom='" + currencyCharCodeFrom + '\'' +
                ", currencyCharCodeTo='" + currencyCharCodeTo + '\'' +
                ", amountOfMoneyFrom=" + amountOfMoneyFrom +
                ", amountOfMoneyTo=" + amountOfMoneyTo +
                ", dateOfConversion=" + dateOfConversion +
                ", currencyRate=" + currencyRate +
                ", user=" + user +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ConversionHistory that = (ConversionHistory) o;
        return Objects.equals(recordId, that.recordId) &&
                Objects.equals(currencyFromId, that.currencyFromId) &&
                Objects.equals(currencyToId, that.currencyToId) &&
                Objects.equals(amountOfMoneyFrom, that.amountOfMoneyFrom) &&
                Objects.equals(amountOfMoneyTo, that.amountOfMoneyTo) &&
                Objects.equals(dateOfConversion, that.dateOfConversion) &&
                Objects.equals(currencyRate, that.currencyRate) &&
                Objects.equals(user.getUserId(), that.user.getUserId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(recordId, currencyFromId, currencyToId, amountOfMoneyFrom, amountOfMoneyTo, dateOfConversion, currencyRate, user.getUserId());
    }
}
