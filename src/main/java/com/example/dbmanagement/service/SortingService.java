package com.example.dbmanagement.service;

import com.example.dbmanagement.util.SortingMode;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class SortingService {

    private final SortingMode[] allModes = SortingMode.values();
    private int currentMode = 0;
    private SortingMode sortingMode = allModes[currentMode];
    private Sort.Direction direction = Sort.Direction.ASC;

    public Sort getSort() {
        return Sort.by(direction, sortingMode.getProperties());
    }

    public void nextSortingMode() {
        currentMode = (currentMode + 1) % allModes.length;
        sortingMode = allModes[currentMode];
    }

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

    public String getSortingMode() {
        return sortingMode.toString();
    }
    public String getDirection() {
        return direction.toString();
    }
}
