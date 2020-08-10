package ru.smsoft.currencyconverter.constants;

public enum Valutes {

    AUD("AUD", "R01010"),
    AZN("AZN", "R01020A"),
    GBP("GBP", "R01035"),
    AMD("AMD", "R01060"),
    BYN("BYN", "R01090B"),
    BGN("BGN", "R01100"),
    BRL("BRL", "R01115"),
    HUF("HUF", "R01135"),
    HKD("HKD", "R01200"),
    DKK("DKK", "R01215"),
    USD("USD", "R01235"),
    EUR("EUR", "R01239"),
    INR("INR", "R01270"),
    KZT("KZT", "R01335"),
    CAD("CAD", "R01350"),
    KGS("KGS", "R01370"),
    CNY("CNY", "R01375"),
    MDL("MDL", "R01500"),
    NOK("NOK", "R01535"),
    PLN("PLN", "R01565"),
    RON("RON", "R01585F"),
    XDR("XDR", "R01589"),
    SGD("SGD", "R01625"),
    TJS("TJS", "R01670"),
    TRY("TRY", "R01700J"),
    TMT("TMT", "R01710A"),
    UZS("UZS", "R01717"),
    UAH("UAH", "R01720"),
    CZK("CZK", "R01760"),
    SEK("SEK", "R01770"),
    CHF("CHF", "R01775"),
    ZAR("ZAR", "R01810"),
    KRW("KRW", "R01815"),
    JPY("JPY", "R01820");


    private final String currencyId;
    private final String currencyCharCode;

    Valutes( String currencyCharCode, String currencyId) {
        this.currencyId = currencyId;
        this.currencyCharCode = currencyCharCode;
    }

    public String getCurrencyId() {
        return currencyId;
    }

    public String getCurrencyCharCode() {
        return currencyCharCode;
    }
}
