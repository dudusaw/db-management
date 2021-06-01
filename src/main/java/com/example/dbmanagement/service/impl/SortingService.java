package com.example.dbmanagement.service.impl;

import com.example.dbmanagement.service.ISortingService;
import com.example.dbmanagement.util.SortingMode;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class SortingService implements ISortingService {

    private final SortingMode[] allModes = SortingMode.values();
    private int currentMode = 0;
    private SortingMode sortingMode = allModes[currentMode];
    private Sort.Direction direction = Sort.Direction.ASC;

    @Override
    public Sort getSort() {
        return Sort.by(direction, sortingMode.getProperties());
    }

    @Override
    public void nextSortingMode() {
        currentMode = (currentMode + 1) % allModes.length;
        sortingMode = allModes[currentMode];
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
