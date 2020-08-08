package ru.smsoft.currencyconverter.exception;

public class FailedToUpdateCurrencyException extends Exception{
    public FailedToUpdateCurrencyException() {
    }

    public FailedToUpdateCurrencyException(String message) {
        super(message);
    }

    public FailedToUpdateCurrencyException(String message, Throwable cause) {
        super(message, cause);
    }

    public FailedToUpdateCurrencyException(Throwable cause) {
        super(cause);
    }

    public FailedToUpdateCurrencyException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
