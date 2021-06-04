package com.example.dbmanagement.entity;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

@Entity
@Table(name = "client")
@Data
@NoArgsConstructor
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", columnDefinition = "BIGSERIAL")
    private Long id;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private Integer age;

    @OneToOne(cascade = CascadeType.ALL)
    private UserInfo userInfo;

    @Setter(AccessLevel.NONE)
    private LocalDateTime registrationDate = LocalDateTime.now();

    @Column(nullable = false)
    private LocalDateTime lastUpdate = LocalDateTime.now();

    @Column(nullable = false, updatable = false)
    @Setter(AccessLevel.NONE)
    private String uuid = UUID.randomUUID().toString();

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm");

    public String registrationDateString() {
        return registrationDate.format(FORMATTER);
    }

    public String lastUpdateString() {
        return lastUpdate.format(FORMATTER);
    }

    public Client(String firstName, String lastName, String email, Integer age) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.age = age;
    }

    public void copyDataFrom(Client client) {
        this.firstName = client.getFirstName();
        this.lastName = client.getLastName();
        this.email = client.getEmail();
        this.age = client.getAge();
    }
}
