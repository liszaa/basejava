package com.urise.webapp.model;

public class SingleLineSection implements Section {

    private String content;

    SingleLineSection(String content) {
        this.content = content;
    }

    @Override
    public void asText() {
        System.out.println(content);
    }
}
