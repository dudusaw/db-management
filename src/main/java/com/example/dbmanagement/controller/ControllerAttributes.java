package com.example.dbmanagement.controller;

import com.example.dbmanagement.entity.Client;
import com.example.dbmanagement.service.IClientService;
import com.example.dbmanagement.service.ISortingService;
import com.example.dbmanagement.service.IPagingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

@ControllerAdvice
public class ControllerAttributes {

    private final IClientService clientService;
    private final ISortingService sortingService;
    private final IPagingService pagingService;

    @Autowired
    public ControllerAttributes(IClientService clientService, ISortingService sortingService, IPagingService pagingService) {
        this.clientService = clientService;
        this.sortingService = sortingService;
        this.pagingService = pagingService;
    }

    @ModelAttribute("clients")
    public Page<Client> clients() {
        Pageable pageable = PageRequest.of(0, 30, sortingService.getSort());
        return clientService.findAll(pageable);
    }

    @ModelAttribute("sortingMode")
    public ISortingService sortingMode() {
        return sortingService;
    }
}
