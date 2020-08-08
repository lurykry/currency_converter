package ru.smsoft.currencyconverter.exception;

public class FailedToGetCurrencyXmlException extends Exception{

    public FailedToGetCurrencyXmlException() {
    }

    public FailedToGetCurrencyXmlException(String message) {
        super(message);
    }

    public FailedToGetCurrencyXmlException(String message, Throwable cause) {
        super(message, cause);
    }

    public FailedToGetCurrencyXmlException(Throwable cause) {
        super(cause);
    }

    public FailedToGetCurrencyXmlException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
