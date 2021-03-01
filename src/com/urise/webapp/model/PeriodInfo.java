package com.urise.webapp.model;

import java.time.LocalDate;
import java.util.Objects;

class PeriodInfo {

    private String title;
    private String description;
    private LocalDate start;
    private LocalDate finish;

    PeriodInfo(String title, String description, LocalDate start, LocalDate finish) {
        Objects.requireNonNull(title, "title must be not null");
        Objects.requireNonNull(start, "start must be not null");
        Objects.requireNonNull(finish, "finish must be not null");
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PeriodInfo that = (PeriodInfo) o;
        return title.equals(that.title) &&
                Objects.equals(description, that.description) &&
                start.equals(that.start) &&
                finish.equals(that.finish);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, description, start, finish);
    }
}