package com.urise.webapp.model;

import java.util.List;

public class PeriodSection implements Section {

    private List<PeriodInfo> experiences;

    PeriodSection(List<PeriodInfo> content) {
        this.experiences = content;
    }

    @Override
    public String toString() {
        StringBuilder text = new StringBuilder();
        for (PeriodInfo content : experiences) {
            text.append(content.getTitle()).append("\n");
            text.append(content.getStart()).append(" / ").append(content.getFinish()).append( "          ").append(content.getDescription()).append("\n\n");
        }
        return text.toString();
    }
}