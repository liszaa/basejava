package com.urise.webapp.model;

import java.util.List;
import java.util.Objects;

public class PeriodSection implements Section {

    private List<PeriodInfo> experiences;

    PeriodSection(List<PeriodInfo> content) {
        Objects.requireNonNull(content, "content must be not null");
        this.experiences = content;
    }

    @Override
    public String toString() {
        StringBuilder text = new StringBuilder();
        for (PeriodInfo content : experiences) {
            text.append(content.getTitle()).append("\n");
            text.append(content.getStart()).append(" / ").append(content.getFinish()).append("          ").append(content.getDescription()).append("\n\n");
        }
        return text.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PeriodSection that = (PeriodSection) o;
        return experiences.equals(that.experiences);
    }

    @Override
    public int hashCode() {
        return Objects.hash(experiences);
    }
}