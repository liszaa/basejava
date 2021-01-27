package com.urise.webapp.model;

public class Contact implements Section {
    private ContactType type;
    private String value;
    private String link;

    public Contact(ContactType type, String value) {
        this.type = type;
        this.value = value;
    }

    public ContactType getType() {
        return type;
    }

    public String getValue() {
        return value;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    @Override
    public void asText() {
        System.out.println(type.getTitle() + " : " + value);
    }
}
