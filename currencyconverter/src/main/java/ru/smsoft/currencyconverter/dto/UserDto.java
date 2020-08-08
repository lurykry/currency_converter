package ru.smsoft.currencyconverter.dto;

import ru.smsoft.currencyconverter.validator.ValidEmail;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class UserDto {

    @NotNull
    @NotEmpty
    @ValidEmail
    private String userEmail;

    @NotNull
    @NotEmpty
    private String userLogin;

    @NotNull
    @NotEmpty
    private String userPassword;

    @NotNull
    @NotEmpty
    private String matchingPassword;

    public UserDto() {
    }

    public UserDto(@NotNull @NotEmpty String userEmail,
                   @NotNull @NotEmpty String userLogin,
                   @NotNull @NotEmpty String userPassword,
                   @NotNull @NotEmpty String matchingPassword) {
        this.userEmail = userEmail;
        this.userLogin = userLogin;
        this.userPassword = userPassword;
        this.matchingPassword = matchingPassword;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserLogin() {
        return userLogin;
    }

    public void setUserLogin(String userLogin) {
        this.userLogin = userLogin;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getMatchingPassword() {
        return matchingPassword;
    }

    public void setMatchingPassword(String matchingPassword) {
        this.matchingPassword = matchingPassword;
    }
}
