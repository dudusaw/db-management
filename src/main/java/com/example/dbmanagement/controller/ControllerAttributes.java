package com.example.dbmanagement.controller;

import com.example.dbmanagement.entity.Client;
import com.example.dbmanagement.service.ClientService;
import com.example.dbmanagement.service.SortingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.List;

@ControllerAdvice
public class ControllerAttributes {

    private final ClientService clientService;
    private final SortingService sortingService;

    @Autowired
    public ControllerAttributes(ClientService clientService, SortingService sortingService) {
        this.clientService = clientService;
        this.sortingService = sortingService;
    }

    @ModelAttribute("clients")
    public List<Client> clients() {
        return clientService.findAll(sortingService.getSort());
    }

    @ModelAttribute("sortingMode")
    public String sortingMode() {
        return sortingService.getSortingModeString();
    }
}
