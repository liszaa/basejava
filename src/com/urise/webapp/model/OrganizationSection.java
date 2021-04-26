package com.urise.webapp.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@XmlAccessorType(XmlAccessType.FIELD)
public class OrganizationSection extends Section {

    private static final long serialVersionUID = -720145632929468169L;
    private List<Organization> experiences;

    public OrganizationSection() {
    }

    public List<Organization> getExperiences() {
        return experiences;
    }

    public void setExperiences(List<Organization> experiences) {
        this.experiences = experiences;
    }

    public OrganizationSection(List<Organization> content) {
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