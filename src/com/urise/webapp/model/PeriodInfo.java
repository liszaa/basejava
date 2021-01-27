package com.urise.webapp.model;

import java.time.LocalDate;

public class PeriodInfo {

    String title;
    String description;
    LocalDate start;
    LocalDate finish;

    public PeriodInfo(String title, String description, LocalDate start, LocalDate finish) {
        this.title = title;
        this.description = description;
        this.start = start;
        this.finish = finish;
    }
}
