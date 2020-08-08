package ru.smsoft.currencyconverter.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.smsoft.currencyconverter.dto.UserDto;
import ru.smsoft.currencyconverter.exception.AccountAlreadyExistException;
import ru.smsoft.currencyconverter.exception.PasswordShouldBeBetweenSizAndThirtyTwoCharactersException;
import ru.smsoft.currencyconverter.exception.PasswordsDoNotMatchException;
import ru.smsoft.currencyconverter.service.email.EmailSenderService;
import ru.smsoft.currencyconverter.service.user.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Controller
@RequestMapping("/")
public class MainController {

    private static final Logger LOGGER = LoggerFactory.getLogger(MainController.class);

    private UserService userService;
    private EmailSenderService emailSenderService;


    @GetMapping(path = {"home", ""})
    public String showHomePage(){
        return "home";
    }

    @GetMapping(path = "login")
    public String showLoginPage(){
        return "login";
    }

    @GetMapping(path = "registration")
    public String showRegistrationPage(Model model){
        UserDto user = new UserDto();
        model.addAttribute("user",user);
        return "registration";
    }
    @PostMapping(path = "registration")
    public String register(@ModelAttribute("user") @Valid UserDto userDto,
                           HttpServletRequest request, Model model){

        try {
             userService.registerUser(userDto);
        } catch (AccountAlreadyExistException e) {
            LOGGER.error("controller: registerUser(): " + e.getMessage(), e);
            model.addAttribute("message", "Account with that username/email already exists");

            return "registration";
        }catch (PasswordShouldBeBetweenSizAndThirtyTwoCharactersException e){
            LOGGER.error("controller: registerUser(): " + e.getMessage(),e);
            model.addAttribute("message", "Password should be between 6 and 32 characters long");

            return "registration";
        }catch (PasswordsDoNotMatchException e){
            LOGGER.error("controller: registerUser(): " + e.getMessage(), e);
            model.addAttribute("message", "Passwords do not match");

            return "registration";
        }
        try {
            request.login(userDto.getUserLogin(), userDto.getUserPassword());
        } catch (ServletException e) {
            LOGGER.error("controller: registerUser(): " + e.getMessage(), e);

        }

        //Тут должен быть небольшой участок кода с отправкой письма об успешной регистрации пользователю

        return "redirect:/home";
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    public void setEmailSenderService(EmailSenderService emailSenderService) {
        this.emailSenderService = emailSenderService;
    }
}
