package com.example.dbmanagement.service;

import com.example.dbmanagement.entity.Client;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.Optional;

public interface IClientService {
    List<Client> findAll();

    List<Client> findAll(Sort sort);

    <S extends Client> S save(S s);

    void deleteById(Long aLong);

    void deleteAll();

    List<Client> searchFor(String pattern);

    Page<Client> findAll(Pageable pageable);

    Optional<Client> findById(Long aLong);

    void addRandomClients(int num);
}
