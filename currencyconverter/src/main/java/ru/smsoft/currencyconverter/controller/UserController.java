package ru.smsoft.currencyconverter.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.smsoft.currencyconverter.model.ConversionHistory;
import ru.smsoft.currencyconverter.model.User;
import ru.smsoft.currencyconverter.security.UserDecorator;
import ru.smsoft.currencyconverter.service.convhistory.ConversionHistoryService;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping(path = "/user")
public class UserController {

    private ConversionHistoryService conversionHistoryService;

    @GetMapping
    public String showUserPage(Model model){
        UserDecorator userDecorator = (UserDecorator) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userDecorator.getUser();
        List<ConversionHistory> records = conversionHistoryService.findConversionHistoryByUser(user);
        model.addAttribute("records", records);
        return "user/user";
    }


    @GetMapping(path = "/findRecord", produces = "application/json")
    @ResponseBody
    public List<ConversionHistory> findRecordByDate(@RequestParam("date")
                                                            String date){
        UserDecorator userDecorator = (UserDecorator) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userDecorator.getUser();
        List<ConversionHistory> records =  user.getConversionHistory()
                .stream()
                .filter(record -> record.getDateOfConversion().toString().equals(date))
                .collect(Collectors.toList());
        return records;
    }

    @Autowired
    public void setConversionHistoryService(ConversionHistoryService conversionHistoryService) {
        this.conversionHistoryService = conversionHistoryService;
    }
}
