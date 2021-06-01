package com.example.dbmanagement.util;

import org.springframework.data.domain.Sort;

public enum SortingMode {
    BY_NAME("firstName", "lastName"),
    BY_ID("id"),
    BY_AGE("age"),
    BY_EMAIL("email")
    ;

    public String[] getProperties() {
        return properties;
    }

    private final String[] properties;

    SortingMode(String... properties) {
        this.properties = properties;
    }
}
