package com.example.dbmanagement.controller;

import com.example.dbmanagement.entity.Client;
import com.example.dbmanagement.service.IClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/clients")
public class RestApiController {

    private final IClientService clientService;

    @Autowired
    public RestApiController(IClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping
    public ResponseEntity<List<Client>> getAllClients() {
        return ResponseEntity.ok(clientService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Client> getClientById(@PathVariable("id") Long id) {
        return ResponseEntity.of(clientService.findById(id));
    }

    @GetMapping("/search")
    public ResponseEntity<List<Client>> searchClients(@RequestParam("q") String q) {
        String pattern = "%" + q + "%";
        return ResponseEntity.ok(clientService.searchFor(pattern));
    }

    @PostMapping
    public ResponseEntity<Client> addClient(@RequestBody Client client) {
        return ResponseEntity.ok(clientService.save(client));
    }

    @PostMapping("/rand/{num}")
    public ResponseEntity<?> addRandomClient(@PathVariable("num") int num) {
        clientService.addRandomClients(num);
        return ResponseEntity.ok().build();
    }

    @PutMapping
    public ResponseEntity<?> updateClient(@RequestBody Client client) {
        clientService.save(client);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteClientById(@PathVariable("id") Long id) {
        clientService.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping
    public ResponseEntity<?> deleteAllClients() {
        clientService.deleteAll();
        return ResponseEntity.ok().build();
    }
}
