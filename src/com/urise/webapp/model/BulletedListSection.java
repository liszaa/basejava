package com.urise.webapp.model;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class BulletedListSection implements Section {
    private static final long serialVersionUID = 6406750252899917034L;
    private List<String> content;

    public BulletedListSection(List<String> content) {
        Objects.requireNonNull(content, "content must be not null");
        this.content = content;
    }

    public BulletedListSection(String... content) {
        Objects.requireNonNull(content, "content must be not null");
        this.content = Arrays.asList(content);
    }

    public String toString() {
        StringBuilder text = new StringBuilder();
        for (String content : content) {
            text.append(" • ").append(content).append("\n");
        }
        return text.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BulletedListSection that = (BulletedListSection) o;
        return content.equals(that.content);
    }

    @Override
    public int hashCode() {
        return Objects.hash(content);
    }
}