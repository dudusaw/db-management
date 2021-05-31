package com.example.dbmanagement.service;

import com.example.dbmanagement.entity.Client;
import com.example.dbmanagement.entity.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientService {

    private final ClientRepository clientRepository;

    @Autowired
    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
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
}
