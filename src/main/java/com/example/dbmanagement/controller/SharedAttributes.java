package com.example.dbmanagement.controller;

import com.example.dbmanagement.service.IClientService;
import com.example.dbmanagement.service.IPagingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;

@Component
public class SharedAttributes {

    private final IClientService clientService;
    private final IPagingService pagingService;

    @Autowired
    public SharedAttributes(IClientService clientService, IPagingService pagingService) {
        this.clientService = clientService;
        this.pagingService = pagingService;
    }

    public void addSharedAttributesTo(Model ui) {
        ui.addAttribute("pagingService", pagingService);
        ui.addAttribute("clients", clientService.findAll(pagingService.getPageable()));
    }
}
