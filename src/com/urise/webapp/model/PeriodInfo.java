package com.urise.webapp.model;

import java.time.LocalDate;

class PeriodInfo {

    private String title;
    private String description;
    private LocalDate start;
    private LocalDate finish;

    PeriodInfo(String title, String description, LocalDate start, LocalDate finish) {
        this.title = title;
        this.description = description;
        this.start = start;
        this.finish = finish;
    }

    String getTitle() {
        return title;
    }

    String getDescription() {
        return description;
    }

    LocalDate getStart() {
        return start;
    }

    LocalDate getFinish() {
        return finish;
    }
}