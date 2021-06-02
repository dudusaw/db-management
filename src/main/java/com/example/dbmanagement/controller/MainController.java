package com.example.dbmanagement.controller;

import com.example.dbmanagement.entity.Client;
import com.example.dbmanagement.service.IClientService;
import com.example.dbmanagement.service.IPagingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class MainController {

    private final IClientService clientService;
    private final IPagingService pagingService;
    private final SharedAttributes sharedAttributes;

    @Autowired
    public MainController(IClientService clientService,
                          IPagingService pagingService,
                          SharedAttributes sharedAttributes) {
        this.clientService = clientService;
        this.pagingService = pagingService;
        this.sharedAttributes = sharedAttributes;
    }

    @GetMapping("/")
    public String home(Model ui) {
        sharedAttributes.addSharedAttributesTo(ui);
        return "index";
    }

    @GetMapping("/change-sorting-mode")
    public String changeSortingMode() {
        pagingService.nextSortingMode();
        return "redirect:/";
    }

    @GetMapping("/search")
    public String search(Model ui, @RequestParam String inputText) {
        sharedAttributes.addSharedAttributesTo(ui);
        String pattern = "%" + inputText + "%";
        List<Client> clients = clientService.searchFor(pattern);
        ui.addAttribute("clients", clients);
        return "index";
    }

    @GetMapping("/change-sorting-direction")
    public String changeSortingDirection() {
        pagingService.changeSortingDirection();
        return "redirect:/";
    }

    @GetMapping("/next-page")
    public String nextPage() {
        pagingService.nextPage();
        return "redirect:/";
    }

    @GetMapping("/prev-page")
    public String prevPage() {
        pagingService.previousPage();
        return "redirect:/";
    }

    @GetMapping("/first-page")
    public String firstPage() {
        pagingService.firstPage();
        return "redirect:/";
    }
}
