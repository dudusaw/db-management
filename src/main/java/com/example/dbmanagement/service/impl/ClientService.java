package com.example.dbmanagement.service.impl;

import com.example.dbmanagement.entity.Client;
import com.example.dbmanagement.entity.ClientRepository;
import com.example.dbmanagement.service.IClientService;
import com.example.dbmanagement.util.RandomGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class ClientService implements IClientService {

    private final ClientRepository clientRepository;
    private final RandomGenerator randomGenerator;

    @Autowired
    public ClientService(ClientRepository clientRepository, RandomGenerator randomGenerator) {
        this.clientRepository = clientRepository;
        this.randomGenerator = randomGenerator;
    }

    @Override
    public List<Client> findAll() {
        return clientRepository.findAll();
    }

    @Override
    public Client getById(Long aLong) {
        return clientRepository.getById(aLong);
    }

    @Override
    public List<Client> findAll(Sort sort) {
        return clientRepository.findAll(sort);
    }

    @Override
    public <S extends Client> S saveAndFlush(S s) {
        return clientRepository.saveAndFlush(s);
    }

    @Override
    public void deleteById(Long aLong) {
        clientRepository.deleteById(aLong);
    }

    @Override
    public void deleteAll() {
        clientRepository.deleteAll();
    }

    @Override
    public List<Client> searchFor(String pattern) {
        return clientRepository.searchFor(pattern);
    }

    @Override
    public void addRandomClient() {
        String firstName = randomGenerator.getRandomString(3, 8);
        String lastName = randomGenerator.getRandomString(3, 8);
        String email = randomGenerator.getRandomString(4, 10) + "@gmail.com";
        int age = randomGenerator.getInt(100) + 1;
        Client randomClient = new Client(firstName, lastName, email, age);
        clientRepository.saveAndFlush(randomClient);
    }
}
