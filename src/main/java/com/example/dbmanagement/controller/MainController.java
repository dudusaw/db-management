package com.example.dbmanagement.controller;

import com.example.dbmanagement.entity.Client;
import com.example.dbmanagement.service.ClientService;
import com.example.dbmanagement.service.SortingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class MainController {

    private final ClientService clientService;
    private final SortingService sortingService;

    @Autowired
    public MainController(ClientService clientService, SortingService sortingService) {
        this.clientService = clientService;
        this.sortingService = sortingService;
    }

    @ModelAttribute("clients")
    public List<Client> clients() {
        return clientService.findAll(sortingService.getSort());
    }

    @ModelAttribute("sortingMode")
    public SortingService sortingMode() {
        return sortingService;
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
