package com.urise.webapp.model;

import java.util.ArrayList;

public class ListSection implements Section {
    ArrayList<String> content;


    public ListSection(ArrayList<String> content) {
        this.content = content;
    }

    @Override
    public void asText() {
        for (String content : content) {
            System.out.println(" • " + content);
        }
    }
}
