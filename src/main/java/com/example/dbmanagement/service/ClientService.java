package com.example.dbmanagement.service;

import com.example.dbmanagement.entity.Client;
import com.example.dbmanagement.entity.ClientRepository;
import com.example.dbmanagement.util.SortingMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientService {

    private final ClientRepository clientRepository;
    private final RandomGenerator randomGenerator;

    @Autowired
    public ClientService(ClientRepository clientRepository, RandomGenerator randomGenerator) {
        this.clientRepository = clientRepository;
        this.randomGenerator = randomGenerator;
    }

    public List<Client> findAll() {
        return clientRepository.findAll();
    }

    public Client getById(Long aLong) {
        return clientRepository.getById(aLong);
    }

    public List<Client> findAll(Sort sort) {
        return clientRepository.findAll(sort);
    }

    public <S extends Client> S saveAndFlush(S s) {
        return clientRepository.saveAndFlush(s);
    }

    public void deleteById(Long aLong) {
        clientRepository.deleteById(aLong);
    }

    public void deleteAll() {
        clientRepository.deleteAll();
    }

    public List<Client> searchFor(String pattern) {
        return clientRepository.searchFor(pattern);
    }

    public void addRandomClient() {
        String firstName = randomGenerator.getRandomString(3, 8);
        String lastName = randomGenerator.getRandomString(3, 8);
        String email = randomGenerator.getRandomString(4, 10) + "@gmail.com";
        int age = randomGenerator.getInt(100) + 1;
        Client randomClient = new Client(firstName, lastName, email, age);
        clientRepository.saveAndFlush(randomClient);
    }
}
