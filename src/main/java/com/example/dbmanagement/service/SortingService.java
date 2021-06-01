package com.example.dbmanagement.service;

import com.example.dbmanagement.util.SortingMode;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class SortingService {

    private final SortingMode[] allModes = SortingMode.values();
    private int currentMode = 0;
    private SortingMode sortingMode = allModes[currentMode];

    public Sort getSort() {
        return sortingMode.getSort();
    }

    public String getSortingModeString() {
        return sortingMode.toString();
    }

    public void nextSortingMode() {
        currentMode = (currentMode + 1) % allModes.length;
        sortingMode = allModes[currentMode];
    }
}
