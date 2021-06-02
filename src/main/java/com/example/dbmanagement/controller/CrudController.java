package com.example.dbmanagement.controller;

import com.example.dbmanagement.entity.Client;
import com.example.dbmanagement.service.impl.ClientService;
import com.example.dbmanagement.service.impl.SortingService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;
import java.util.List;

@Controller
@Slf4j
public class CrudController {

    private final ClientService clientService;
    private final SortingService sortingService;

    @Autowired
    public CrudController(ClientService clientService, SortingService sortingService) {
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

    @PostMapping("/add")
    public String processAdd(@ModelAttribute Client client) {
        clientService.saveAndFlush(client);
        return "redirect:/";
    }

    @GetMapping("/add-random")
    public String addRandom(@RequestParam(defaultValue = "1") int num) {
        clientService.addRandomClients(num);
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

    @GetMapping("/delete-all")
    public String deleteAll() {
        clientService.deleteAll();
        return "redirect:/";
    }

    @GetMapping("/update-request")
    public String updateRequest(Model ui, @RequestParam Long id) {
        ui.addAttribute("updateClient", clientService.getById(id));
        return "index";
    }
}
