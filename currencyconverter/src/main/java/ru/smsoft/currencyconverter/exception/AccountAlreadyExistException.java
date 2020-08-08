package ru.smsoft.currencyconverter.exception;

public class AccountAlreadyExistException extends Exception{

    public AccountAlreadyExistException() {
    }

    public AccountAlreadyExistException(String message) {
        super(message);
    }

    public AccountAlreadyExistException(String message, Throwable cause) {
        super(message, cause);
    }

    public AccountAlreadyExistException(Throwable cause) {
        super(cause);
    }

    public AccountAlreadyExistException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
