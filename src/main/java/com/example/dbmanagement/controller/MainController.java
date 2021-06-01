package com.example.dbmanagement.controller;

import com.example.dbmanagement.entity.Client;
import com.example.dbmanagement.service.ClientService;
import com.example.dbmanagement.service.SortingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class MainController {

    private final SortingService sortingService;

    @Autowired
    public MainController(SortingService sortingService) {
        this.sortingService = sortingService;
    }

    @GetMapping("/")
    public String home() {
        return "index";
    }

    @GetMapping("/change-sorting-mode")
    public String changeSortingMode() {
        sortingService.nextSortingMode();
        return "redirect:/";
    }
}
