package com.example.dbmanagement.service;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

public interface IPagingService {
    void nextPage();

    void previousPage();

    Pageable getPageable();

    void nextSortingMode();

    void changeSortingDirection();

    String getSortingMode();

    String getDirection();

    void firstPage();
}
