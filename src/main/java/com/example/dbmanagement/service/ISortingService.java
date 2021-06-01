package com.example.dbmanagement.service;

import org.springframework.data.domain.Sort;

public interface ISortingService {
    Sort getSort();

    void nextSortingMode();

    void changeSortingDirection();

    String getSortingMode();

    String getDirection();
}
