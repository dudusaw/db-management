package com.example.dbmanagement.controller;

import com.example.dbmanagement.entity.Client;
import com.example.dbmanagement.service.IClientService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;

@Controller
@Slf4j
public class CrudController {

    private final IClientService clientService;
    private final SharedAttributes sharedAttributes;

    @Autowired
    public CrudController(IClientService clientService,
                          SharedAttributes sharedAttributes) {
        this.clientService = clientService;
        this.sharedAttributes = sharedAttributes;
    }

    @PostMapping("/add")
    public String processAdd(@ModelAttribute Client client) {
        clientService.save(client);
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
        clientService.save(updatedClient);
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
        sharedAttributes.addSharedAttributesTo(ui);
        ui.addAttribute("updateClient", clientService.getById(id));
        return "index";
    }
}
