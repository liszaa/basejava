package com.urise.webapp.model;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class OrganizationSection implements Section {

    private static final long serialVersionUID = -720145632929468169L;
    private List<Organization> experiences;

    OrganizationSection(List<Organization> content) {
        Objects.requireNonNull(content, "content must be not null");
        this.experiences = content;
    }

    public OrganizationSection(Organization... content) {
        Objects.requireNonNull(content, "content must be not null");
        this.experiences = Arrays.asList(content);
    }

    @Override
    public String toString() {
        StringBuilder text = new StringBuilder();
        for (Organization content : experiences) {
            text.append(content);
        }
        return text.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrganizationSection that = (OrganizationSection) o;
        return experiences.equals(that.experiences);
    }

    @Override
    public int hashCode() {
        return Objects.hash(experiences);
    }
}