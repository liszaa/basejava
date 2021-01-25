package com.urise.webapp.model;


import java.util.Objects;
import java.util.UUID;

public class Resume implements Comparable<Resume> {

    private final String uuid;
    private final String fullName;

    public Resume(String fullName) {
        this(UUID.randomUUID().toString(), fullName);
    }

//    private final Section<Map<String, String>> contacts;
//    private final Section<String> personalInfo;
//    private final Section<List<String>> achievementsInfo;
//    private final Section<List<String>> qualificationsInfo;
//    private final Section<List<PeriodInfo>> experienceInfo;
//    private final Section<List<PeriodInfo>> educationInfo;

    public Resume(String uuid, String fullName) {
        this.uuid = uuid;
        this.fullName = fullName;
//        this.contacts = null;
//        this.personalInfo = null;
//        this.achievementsInfo = null;
//        this.qualificationsInfo = null;
//        this.experienceInfo = null;
//        this.educationInfo = null;
    }

//    public Resume(String fullName,
//                  Section<Map<String, String>> contacts,
//                  Section<String> personalInfo,
//                  Section<List<String>> achievementsInfo,
//                  Section<List<String>> qualificationsInfo,
//                  Section<List<PeriodInfo>> experienceInfo,
//                  Section<List<PeriodInfo>> educationInfo) {
//        this.uuid = UUID.randomUUID().toString();
//        this.fullName = fullName;
//        this.contacts = contacts;
//        this.personalInfo = personalInfo;
//        this.achievementsInfo = achievementsInfo;
//        this.qualificationsInfo = qualificationsInfo;
//        this.experienceInfo = experienceInfo;
//        this.educationInfo = educationInfo;
//    }


    public String getUuid() {
        return uuid;
    }

    public String getFullName() {
        return fullName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Resume resume = (Resume) o;
        if (!uuid.equals(resume.uuid)) return false;
        return fullName.equals(resume.fullName);

    }

    @Override
    public int hashCode() {
        return Objects.hash(uuid, fullName);
    }

    @Override
    public String toString() {
        return uuid;
    }

    @Override
    public int compareTo(Resume o) {
        return (fullName.compareTo(o.fullName) == 0) ? uuid.compareTo(o.uuid) : fullName.compareTo(o.fullName);
    }

}




