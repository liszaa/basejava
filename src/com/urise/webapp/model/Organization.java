package com.urise.webapp.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class Organization {

    private final String link;
    private final String name;
    private List<Position> positions = new ArrayList<>();

    public Organization(String link, String name, List<Position> positions) {
        this.link = link;
        this.name = name;
        this.positions = positions;
    }

    public Organization(String link, String name, Position... positions) {
        this.link = link;
        this.name = name;
        this.positions = Arrays.asList(positions);
    }

    public String getLink() {
        return link;
    }

    public String getName() {
        return name;
    }

    public List<Position> getPositions() {
        return positions;
    }


    @Override
    public String toString() {
        StringBuilder string = new StringBuilder();
        string.append(name).append("\n");
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

    public static class Position {

        private String title;
        private String description;
        private LocalDate start;
        private LocalDate finish;

        public Position(String title, String description, LocalDate start, LocalDate finish) {
            Objects.requireNonNull(title, "title must be not null");
            Objects.requireNonNull(start, "start must be not null");
            Objects.requireNonNull(finish, "finish must be not null");
            this.title = title;
            this.description = description;
            this.start = start;
            this.finish = finish;
        }

        String getTitle() {
            return title;
        }

        String getDescription() {
            return description;
        }

        LocalDate getStart() {
            return start;
        }

        LocalDate getFinish() {
            return finish;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Position that = (Position) o;
            return title.equals(that.title) &&
                    description.equals(that.description) &&
                    start.equals(that.start) &&
                    finish.equals(that.finish);
        }

        @Override
        public int hashCode() {
            return Objects.hash(title, description, start, finish);
        }
    }
}
