package com.urise.webapp.model;

import java.util.List;

public class BulletedListSection implements Section {
    private List<String> content;

    BulletedListSection(List<String> content) {
        this.content = content;
    }

    public String toString() {
        StringBuilder text = new StringBuilder();
        for (String content : content) {
            text.append(" • ").append(content).append("\n");
        }
        return text.toString();
    }
}