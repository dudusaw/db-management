package com.example.dbmanagement.service;

import com.example.dbmanagement.entity.Client;
import org.springframework.data.domain.Sort;

import java.util.List;

public interface IClientService {
    List<Client> findAll();

    Client getById(Long aLong);

    List<Client> findAll(Sort sort);

    <S extends Client> S saveAndFlush(S s);

    void deleteById(Long aLong);

    void deleteAll();

    List<Client> searchFor(String pattern);

    void addRandomClients(int num);
}
