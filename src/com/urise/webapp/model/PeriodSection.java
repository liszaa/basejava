package com.urise.webapp.model;

import java.util.List;

public class PeriodSection implements Section {

    private List<PeriodInfo> experiences;

    PeriodSection(List<PeriodInfo> content) {
        this.experiences = content;
    }


    public void asText() {
        for (PeriodInfo content : experiences) {
            System.out.println(content.getTitle());
            System.out.print(content.getStart() + " / " + content.getFinish() + "          " + content.getDescription());
            System.out.println(" ");
            System.out.println(" ");
        }
    }
}
