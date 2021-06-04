package com.example.dbmanagement.entity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {

    @Query("select c from Client c " +
            "where lower(c.firstName) like lower(?1) " +
            "or lower(c.lastName) like lower(?1) " +
            "or lower(c.email) like lower(?1)")
    List<Client> searchFor(String pattern);
}
