package ru.smsoft.currencyconverter.service.email;

public interface EmailService {
    void sendSimpleMessage(String to, String subject, String text);
}
