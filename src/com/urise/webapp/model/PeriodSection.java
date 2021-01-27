package com.urise.webapp.model;

import java.util.ArrayList;

public class PeriodSection implements Section {

    ArrayList<PeriodInfo> content;

    public PeriodSection(ArrayList<PeriodInfo> content) {
        this.content = content;
    }

    @Override
    public void asText() {
        for (PeriodInfo content : content) {
            System.out.println(content.title);
            System.out.print(content.start + " / " + content.finish + "          " + content.description);
            System.out.println(" ");
            System.out.println(" ");
        }
    }
}
