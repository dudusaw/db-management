package com.example.dbmanagement.controller;

import com.example.dbmanagement.entity.Client;
import com.example.dbmanagement.service.IClientService;
import com.example.dbmanagement.service.ISortingService;
import com.example.dbmanagement.service.impl.ClientService;
import com.example.dbmanagement.service.impl.SortingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class MainController {

    private final IClientService clientService;
    private final ISortingService sortingService;

    @Autowired
    public MainController(IClientService clientService, ISortingService sortingService) {
        this.clientService = clientService;
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

    @GetMapping("/search")
    public String search(Model ui, @RequestParam String inputText) {
        String pattern = "%" + inputText + "%";
        List<Client> clients = clientService.searchFor(pattern);
        ui.addAttribute("clients", clients);
        return "index";
    }

    @GetMapping("/change-sorting-direction")
    public String changeSortingDirection() {
        sortingService.changeSortingDirection();
        return "redirect:/";
    }
}
