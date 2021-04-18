package com.urise.webapp.model;

import com.urise.webapp.util.LocalDateAdapter;

import javax.xml.bind.annotation.*;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@XmlAccessorType(XmlAccessType.FIELD)
public class Organization implements Serializable {

    private static final long serialVersionUID = -2466447498750409674L;
    private Link homePage;
    private List<Position> positions = new ArrayList<>();

    public Organization() {
    }

    public Organization(String name, String url, Position... positions) {
        this(new Link(name, url), Arrays.asList(positions));
    }

    public Organization(String name, String url, List<Position> positions) {
        homePage = new Link(name, url);
        this.positions = positions;
    }

    public Organization(Link homePage, List<Position> positions) {
        this.homePage = homePage;
        this.positions = positions;
    }

    public Link getHomePage() {
        return homePage;
    }

    public List<Position> getPositions() {
        return positions;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Organization that = (Organization) o;
        return Objects.equals(homePage, that.homePage) &&
                Objects.equals(positions, that.positions);
    }

    @Override
    public int hashCode() {
        return Objects.hash(homePage, positions);
    }

    @Override
    public String toString() {
        StringBuilder string = new StringBuilder();
        string.append(homePage.getName()).append("\n");
        for (Position period : positions) {
            string.append(period.start.getMonthValue()).append("/").append(period.start.getYear()).append(" - ").append(period.finish.getMonthValue()).append("/").append(period.finish.getYear());
            string.append("        ").append(period.title);
            if (period.description != null) {
                string.append(period.description);
            }
            string.append("\n\n");
        }
        return string.toString();
    }

    @XmlAccessorType(XmlAccessType.FIELD)
    public static class Position implements Serializable {

        private static final long serialVersionUID = 264061032491292813L;
        private String title;
        private String description;

        @XmlJavaTypeAdapter(LocalDateAdapter.class)
        private LocalDate start;

        @XmlJavaTypeAdapter(LocalDateAdapter.class)
        private LocalDate finish;

        public Position(String title, String description, LocalDate start, LocalDate finish) {
            Objects.requireNonNull(title, "title must be not null");
            Objects.requireNonNull(description, "description must be not null");
            Objects.requireNonNull(start, "start must be not null");
            Objects.requireNonNull(finish, "finish must be not null");
            this.title = title;
            this.description = description;
            this.start = start;
            this.finish = finish;
        }

        public Position() {
        }

        public String getTitle() {
            return title;
        }

        public String getDescription() {
            return description;
        }

        public LocalDate getStart() {
            return start;
        }

        public LocalDate getFinish() {
            return finish;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Position position = (Position) o;
            return Objects.equals(title, position.title) &&
                    Objects.equals(description, position.description) &&
                    Objects.equals(start, position.start) &&
                    Objects.equals(finish, position.finish);
        }

        @Override
        public int hashCode() {
            return Objects.hash(title, description, start, finish);
        }
    }
}
