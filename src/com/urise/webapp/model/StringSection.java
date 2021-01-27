package com.urise.webapp.model;

public class StringSection implements Section {

    String content;

    public StringSection(String content) {
        this.content = content;
    }

    @Override
    public void asText() {
        System.out.println(content);
    }
}
