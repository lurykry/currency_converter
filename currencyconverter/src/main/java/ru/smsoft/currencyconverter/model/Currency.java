package ru.smsoft.currencyconverter.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Entity
@Table(name = "currency")
public class Currency {

    @Id
    @Column(name = "currency_id")
    private String currencyId;

    @Column(name = "currency_num_code")
    @Size(min = 3,
    max = 3)
    private String currencyNumCode;

    @Column(name = "currency_char_code")
    @Size(min = 3,
            max = 3)
    private String currencyCharCode;

    @Column(name = "currency_name")
    private String currencyName;

    @Column(name = "current_currency_value")
    private Double currencyValue;

    @Column(name = "date_of_update")
    private LocalDate currencyDateOfUpdate;

    public Currency() {
    }

    public Currency(String currencyId, @Size(min = 3,
            max = 3) String currencyNumCode, @Size(min = 3,
            max = 3) String currencyCharCode, String currencyName, Double currentCurrencyValue, LocalDate currencyDateOfUpdate, Double currencyValue, LocalDate dateOfUpdate1) {
        this.currencyId = currencyId;
        this.currencyNumCode = currencyNumCode;
        this.currencyCharCode = currencyCharCode;
        this.currencyName = currencyName;
        this.currencyValue = currencyValue;
        this.currencyDateOfUpdate = dateOfUpdate1;
    }

    public String getCurrencyId() {
        return currencyId;
    }

    public void setCurrencyId(String currencyId) {
        this.currencyId = currencyId;
    }

    public String getCurrencyNumCode() {
        return currencyNumCode;
    }

    public void setCurrencyNumCode(String currencyNumCode) {
        this.currencyNumCode = currencyNumCode;
    }

    public String getCurrencyCharCode() {
        return currencyCharCode;
    }

    public void setCurrencyCharCode(String currencyCharCode) {
        this.currencyCharCode = currencyCharCode;
    }

    public String getCurrencyName() {
        return currencyName;
    }

    public void setCurrencyName(String currencyName) {
        this.currencyName = currencyName;
    }


    public Double getCurrencyValue() {
        return currencyValue;
    }

    public void setCurrencyValue(Double currencyValue) {
        this.currencyValue = currencyValue;
    }

    public LocalDate getCurrencyDateOfUpdate() {
        return currencyDateOfUpdate;
    }

    public void setCurrencyDateOfUpdate(LocalDate dateOfUpdate) {
        this.currencyDateOfUpdate = dateOfUpdate;
    }

    @Override
    public String toString() {
        return "Currency{" +
                "currencyId='" + currencyId + '\'' +
                ", currencyNumCode='" + currencyNumCode + '\'' +
                ", currencyCharCode='" + currencyCharCode + '\'' +
                ", currencyName='" + currencyName + '\'' +
                ", currencyValue=" + currencyValue +
                ", currencyDateOfUpdate=" + currencyDateOfUpdate +
                '}';
    }
}
