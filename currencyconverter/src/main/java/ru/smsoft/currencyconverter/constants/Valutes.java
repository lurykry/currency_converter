package ru.smsoft.currencyconverter.constants;

public enum Valutes {

    АВСТРАЛИЙСКИЙ_ДОЛЛАР("AUD", "R01010"),
    АЗЕРБАЙДЖАНСКИЙ_МАНАТ("AZN", "R01020A"),
    ФУНТ_СТЕРЛИНГОВ_СОЕДИНЕННОГО_КОРОЛЕВСТВА("GBP", "R01035"),
    АРМЯНСКИХ_ДРАМОВ("AMD", "R01060"),
    БЕЛОРУССКИЙ_РУБЛЬ("BYN", "R01090B"),
    БОЛГАРСКИЙ_ЛЕВ("BGN", "R01100"),
    БРАЗИЛЬСКИЙ_РЕАЛ("BRL", "R01115"),
    ВЕНГЕРСКИХ_ФОРИНТОВ("HUF", "R01135"),
    ГОНКОНГСКИХ_ДОЛЛАРОВ("HKD", "R01200"),
    ДАТСКАЯ_КРОНА("DKK", "R01215"),
    ДОЛЛАР_США("USD", "R01235"),
    ЕВРО("EUR", "R01239"),
    ИНДИЙСКИХ_РУПИЙ("INR", "R01270"),
    КАЗАХСТАНСКИХ_ТЕНГЕ("KZT", "R01335"),
    КАНАДСКИЙ_ДОЛЛАР("CAD", "R01350"),
    КИРГИЗСКИХ_СОМОВ("KGS", "R01370"),
    КИТАЙСКИЙ_ЮАНЬ("CNY", "R01375"),
    МОЛДАВСКИХ_ЛЕЕВ("MDL", "R01500"),
    НОРВЕЖСКИХ_КРОН("NOK", "R01535"),
    ПОЛЬСКИЙ_ЗЛОТЫЙ("PLN", "R01565"),
    РУМЫНСКИЙ_ЛЕЙ("RON", "R01585F"),
    СДР("XDR", "R01589"),
    СИНГАПУРСКИЙ_ДОЛЛАР("SGD", "R01625"),
    ТАДЖИКСКИХ_СОМОНИ("TJS", "R01670"),
    ТУРЕЦКАЯ_ЛИРА("TRY", "R01700J"),
    НОВЫЙ_ТУРКМЕНСКИЙ_МАНАТ("TMT", "R01710A"),
    УЗБЕКСКИХ_СУМОВ("UZS", "R01717"),
    УКРАИНСКИХ_ГРИВЕН("UAH", "R01720"),
    ЧЕШСКИХ_КРОН("CZK", "R01760"),
    ШВЕДСКИХ_КРОН("SEK", "R01770"),
    ШВЕЙЦАРСКИЙ_ФРАНК("CHF", "R01775"),
    ЮЖНОАФРИКАНСКИХ_РЭНДОВ("ZAR", "R01810"),
    ВОН_РЕСПУБЛИКИ_КОРЕЯ("KRW", "R01815"),
    ЯПОНСКИХ_ИЕН("JPY", "R01820");

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
