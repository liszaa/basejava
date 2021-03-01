package com.urise.webapp.model;

import java.util.Objects;

public class SingleLineSection implements Section {

    private String content;

    SingleLineSection(String content) {
        Objects.requireNonNull(content, "content must be not null");
        this.content = content;
    }

    @Override
    public String toString() {
        return content;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SingleLineSection that = (SingleLineSection) o;
        return content.equals(that.content);
    }

    @Override
    public int hashCode() {
        return Objects.hash(content);
    }
}