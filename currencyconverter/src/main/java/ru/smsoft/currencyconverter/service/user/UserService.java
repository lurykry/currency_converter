package ru.smsoft.currencyconverter.service.user;

import ru.smsoft.currencyconverter.dto.UserDto;
import ru.smsoft.currencyconverter.exception.AccountAlreadyExistException;
import ru.smsoft.currencyconverter.exception.PasswordShouldBeBetweenSizAndThirtyTwoCharactersException;
import ru.smsoft.currencyconverter.exception.PasswordsDoNotMatchException;
import ru.smsoft.currencyconverter.model.User;

public interface UserService {

    void saveUser(User user);
    User findUserByLogin(String login);
    User findUserByEmail(String email);
    User registerUser(UserDto user) throws AccountAlreadyExistException, PasswordShouldBeBetweenSizAndThirtyTwoCharactersException, PasswordsDoNotMatchException;

}
