package com.example.dbmanagement.controller;

import com.example.dbmanagement.entity.Client;
import com.example.dbmanagement.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Controller
public class MainController {

    private ClientService clientService;

    @Autowired
    public MainController(ClientService clientService) {
        this.clientService = clientService;
    }

    @ModelAttribute("clients")
    public List<Client> clients() {
        return clientService.findAll(Sort.by("firstName", "lastName"));
    }

    @GetMapping("/")
    public String home() {
        return "index";
    }

    @PostMapping("/add")
    public String processAdd(@ModelAttribute Client client) {
        clientService.saveAndFlush(client);
        return "redirect:/";
    }

    @PostMapping("/update")
    public String processUpdate(@ModelAttribute Client client, @RequestParam Long id) {
        Client updatedClient = clientService.getById(id);
        updatedClient.copyDataFrom(client);
        updatedClient.setLastUpdate(LocalDateTime.now());
        clientService.saveAndFlush(updatedClient);
        return "redirect:/";
    }

    @GetMapping("/delete")
    public String delete(@RequestParam Long id) {
        clientService.deleteById(id);
        return "redirect:/";
    }

    @GetMapping("/update-request")
    public String updateRequest(Model ui, @RequestParam Long id) {
        ui.addAttribute("updateClient", clientService.getById(id));
        return "index";
    }
}
