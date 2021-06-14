package com.example.dbmanagement.service.impl;

import com.example.dbmanagement.entity.Client;
import com.example.dbmanagement.entity.repo.ClientRepository;
import com.example.dbmanagement.entity.UserInfo;
import com.example.dbmanagement.service.IClientService;
import com.example.dbmanagement.util.IRandomGenerator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@Slf4j
public class ClientService implements IClientService {

    private final ClientRepository clientRepository;
    private final IRandomGenerator IRandomGenerator;

    @Autowired
    public ClientService(ClientRepository clientRepository, IRandomGenerator IRandomGenerator) {
        this.clientRepository = clientRepository;
        this.IRandomGenerator = IRandomGenerator;
    }

    @Override
    public List<Client> findAll() {
        return clientRepository.findAll();
    }

    @Override
    public List<Client> findAll(Sort sort) {
        return clientRepository.findAll(sort);
    }

    @Override
    public <S extends Client> S save(S s) {
        if (s.getUserInfo() == null) {
            s.setUserInfo(new UserInfo());
        }
        return clientRepository.save(s);
    }

    @Override
    public void deleteById(Long aLong) {
        clientRepository.deleteById(aLong);
    }

    @Override
    public void deleteAll() {
        clientRepository.truncateTables();
    }

    @Override
    public List<Client> searchFor(String pattern) {
        pattern = "%" + pattern + "%";
        return clientRepository.searchFor(pattern);
    }

    @Override
    public Page<Client> findAll(Pageable pageable) {
        return clientRepository.findAll(pageable);
    }

    @Override
    public Optional<Client> findById(Long aLong) {
        return clientRepository.findById(aLong);
    }

    @Override
    public void addRandomClients(int num) {
        List<Client> clientList = new ArrayList<>();
        for (int i = 0; i < num; i++) {
            Client randomClient = generateClient();
            randomClient.setUserInfo(generateUserInfo());
            clientList.add(randomClient);
        }
        clientRepository.saveAll(clientList);
    }

    private UserInfo generateUserInfo() {
        String login = IRandomGenerator.getRandomString(3, 10);
        String password = IRandomGenerator.generatePassword(7, 15);
        return new UserInfo(login, password);
    }

    private Client generateClient() {
        String firstName = IRandomGenerator.getRandomString(3, 8);
        String lastName = IRandomGenerator.getRandomString(3, 8);
        String email = IRandomGenerator.getRandomString(4, 10) + "@gmail.com";
        int age = IRandomGenerator.getInt(100) + 1;
        return new Client(firstName, lastName, email, age);
    }
}
