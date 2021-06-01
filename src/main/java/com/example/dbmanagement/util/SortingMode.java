package com.example.dbmanagement.util;

import org.springframework.data.domain.Sort;

public enum SortingMode {
    BY_NAME("firstName", "lastName"),
    BY_ID("id"),
    BY_AGE("age"),
    BY_EMAIL("email")
    ;

    public Sort getSort() {
        return sort;
    }

    private final Sort sort;

    SortingMode(String... properties) {
        sort = Sort.by(properties);
    }
}
