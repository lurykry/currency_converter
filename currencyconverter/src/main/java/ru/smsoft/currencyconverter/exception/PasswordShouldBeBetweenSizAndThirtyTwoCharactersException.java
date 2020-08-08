package ru.smsoft.currencyconverter.exception;

public class PasswordShouldBeBetweenSizAndThirtyTwoCharactersException extends Exception{

    public PasswordShouldBeBetweenSizAndThirtyTwoCharactersException() {
    }

    public PasswordShouldBeBetweenSizAndThirtyTwoCharactersException(String message) {
        super(message);
    }

    public PasswordShouldBeBetweenSizAndThirtyTwoCharactersException(String message, Throwable cause) {
        super(message, cause);
    }

    public PasswordShouldBeBetweenSizAndThirtyTwoCharactersException(Throwable cause) {
        super(cause);
    }

    public PasswordShouldBeBetweenSizAndThirtyTwoCharactersException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
