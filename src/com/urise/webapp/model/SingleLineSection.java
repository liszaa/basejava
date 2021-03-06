package com.urise.webapp.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import java.util.Objects;


public class SingleLineSection extends Section {

    private static final long serialVersionUID = 4746336089527833352L;
    private String content;

    public SingleLineSection() {
    }

    public SingleLineSection(String content) {
        Objects.requireNonNull(content, "content must be not null");
        this.content = content;
    }

    public String getContent() {
        return content;
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