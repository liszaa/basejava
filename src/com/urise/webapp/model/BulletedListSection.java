package com.urise.webapp.model;

import java.util.ArrayList;
import java.util.List;

public class BulletedListSection implements Section {
    private List<String> content;


    BulletedListSection(List<String> content) {
        this.content = content;
    }

    public void asText() {
        for (String content : content) {
            System.out.println(" • " + content);
        }
    }
}
