package ru.smsoft.currencyconverter.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.smsoft.currencyconverter.model.ConversionHistory;
import ru.smsoft.currencyconverter.service.convhistory.ConversionHistoryService;

import java.util.List;

@Controller
@RequestMapping(path = "/admin")
public class AdminController {

    private ConversionHistoryService conversionHistoryService;

    @GetMapping
    public String showAdminPage(Model model){
        List<ConversionHistory> records = conversionHistoryService.findAll();
        model.addAttribute("records", records);
        return "admin/admin";
    }

    @GetMapping(path = "/findRecord", produces = "application/json")
    @ResponseBody
    public List<ConversionHistory> findRecordByDate(@RequestParam("date")
                                       String date){

        List<ConversionHistory> records = conversionHistoryService.findRecordsByDate(date);

        return records;
    }

    @Autowired
    public void setConversionHistoryService(ConversionHistoryService conversionHistoryService) {
        this.conversionHistoryService = conversionHistoryService;
    }
}
