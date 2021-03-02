package com.urise.webapp.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class Organization {

    private final String linkOfOrganization;
    private final String nameOfOrganization;
    private List<PeriodInfo> positions = new ArrayList<>();

    public Organization(String linkOfOrganization, String nameOfOrganization, List<PeriodInfo> positions) {
        this.linkOfOrganization = linkOfOrganization;
        this.nameOfOrganization = nameOfOrganization;
        this.positions = positions;
    }

    public Organization(String linkOfOrganization, String nameOfOrganization, PeriodInfo... positions) {
        this.linkOfOrganization = linkOfOrganization;
        this.nameOfOrganization = nameOfOrganization;
        this.positions = Arrays.asList(positions);
    }

    public String getLinkOfOrganization() {
        return linkOfOrganization;
    }

    public String getNameOfOrganization() {
        return nameOfOrganization;
    }

    public List<PeriodInfo> getPositions() {
        return positions;
    }


    @Override
    public String toString() {
        StringBuilder string = new StringBuilder();
        string.append(nameOfOrganization).append("\n");
        for (PeriodInfo period : positions) {
            string.append(period.start.getMonthValue()).append("/").append(period.start.getYear()).append(" - ").append(period.finish.getMonthValue()).append("/").append(period.finish.getYear());
            string.append("        ").append(period.title);
            if (period.description != null) {
                string.append(period.description);
            }
            string.append("\n\n");
        }
        return string.toString();
    }

    public static class PeriodInfo {

        private String title;
        private String description;
        private LocalDate start;
        private LocalDate finish;

        PeriodInfo(String title, String description, LocalDate start, LocalDate finish) {
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
            PeriodInfo that = (PeriodInfo) o;
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
