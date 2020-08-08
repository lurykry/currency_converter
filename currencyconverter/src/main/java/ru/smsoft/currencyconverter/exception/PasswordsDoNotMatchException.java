package ru.smsoft.currencyconverter.exception;

public class PasswordsDoNotMatchException extends Exception{
    public PasswordsDoNotMatchException() {
    }

    public PasswordsDoNotMatchException(String message) {
        super(message);
    }

    public PasswordsDoNotMatchException(String message, Throwable cause) {
        super(message, cause);
    }

    public PasswordsDoNotMatchException(Throwable cause) {
        super(cause);
    }

    public PasswordsDoNotMatchException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
