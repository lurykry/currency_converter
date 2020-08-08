package ru.smsoft.currencyconverter.exception;

public class FailedToParseCurrencyXmlException extends Exception{

    public FailedToParseCurrencyXmlException() {
    }

    public FailedToParseCurrencyXmlException(String message) {
        super(message);
    }

    public FailedToParseCurrencyXmlException(String message, Throwable cause) {
        super(message, cause);
    }

    public FailedToParseCurrencyXmlException(Throwable cause) {
        super(cause);
    }

    public FailedToParseCurrencyXmlException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
