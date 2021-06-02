package com.example.dbmanagement.service.impl;

import com.example.dbmanagement.service.IPagingService;
import com.example.dbmanagement.util.SortingMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class PagingService implements IPagingService {

    private int pageSize = 50;
    private final SortingMode[] allModes = SortingMode.values();
    private int currentMode = 0;
    private SortingMode sortingMode = allModes[currentMode];
    private Sort.Direction direction = Sort.Direction.ASC;

    private Pageable currentPage;

    public PagingService() {
        currentPage = PageRequest.of(0, pageSize, getSort());
    }

    private Sort getSort() {
        return Sort.by(direction, sortingMode.getProperties());
    }

    private void updatePaging() {
        currentPage = PageRequest.of(currentPage.getPageNumber(), pageSize, getSort());
    }

    @Override
    public void nextPage() {
        currentPage = currentPage.next();
    }

    @Override
    public void previousPage() {
        currentPage = currentPage.previousOrFirst();
    }

    @Override
    public void firstPage() {
        currentPage = currentPage.first();
    }

    @Override
    public Pageable getPageable() {
        return currentPage;
    }

    @Override
    public void nextSortingMode() {
        currentMode = (currentMode + 1) % allModes.length;
        sortingMode = allModes[currentMode];
        updatePaging();
    }

    @Override
    public void changeSortingDirection() {
        switch (direction) {
            case ASC:
                direction = Sort.Direction.DESC;
                break;
            case DESC:
                direction = Sort.Direction.ASC;
                break;
        }
        updatePaging();
    }

    @Override
    public String getSortingMode() {
        return sortingMode.toString();
    }
    @Override
    public String getDirection() {
        return direction.toString();
    }
}
