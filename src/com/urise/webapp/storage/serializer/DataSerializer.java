package com.urise.webapp.storage.serializer;

import com.urise.webapp.model.*;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DataSerializer implements Serializer {

    @Override
    public void write(Resume r, OutputStream os) throws IOException {
        try (DataOutputStream dos = new DataOutputStream(os)) {
            writeGeneralInfo(r, dos);
            writeContacts(r.getContacts(), dos);

            for (Map.Entry<SectionType, Section> entry : r.getSections().entrySet()) {
                Section section = entry.getValue();
                dos.writeUTF(section.getClass().getName());
                dos.writeUTF(entry.getKey().name());

                if (section instanceof SingleLineSection) {
                    String content = ((SingleLineSection) section).getContent();
                    dos.writeUTF(content);

                } else if (section instanceof BulletedListSection) {
                    BulletedListSection listSection = (BulletedListSection) section;
                    List<String> content = listSection.getContent();
                    dos.writeInt(content.size());
                    for (String line : content) {
                        dos.writeUTF(line);
                    }

                } else if (section instanceof OrganizationSection) {
                    writeOrganizationSection((OrganizationSection) section, entry, dos);
                }
            }
        }
    }

    @Override
    public Resume read(InputStream is) throws IOException {
        try (DataInputStream dis = new DataInputStream(is)) {

            Resume resume = readResumeWithGeneralInfo(dis);
            resume.setContacts(readContact(dis));

            try {
                do {
                    String className = dis.readUTF();
                    Class sectionClass = Class.forName(className);

                    if (sectionClass.equals(SingleLineSection.class)) {
                        SectionType type = SectionType.valueOf(dis.readUTF());
                        String content = dis.readUTF();
                        SingleLineSection section = new SingleLineSection(content);
                        resume.addSection(type, section);
                    }

                    if (sectionClass.equals(BulletedListSection.class)) {
                        SectionType type = SectionType.valueOf(dis.readUTF());
                        List<String> lines = new ArrayList<>();
                        int sizeOfBulletedListSection = dis.readInt();
                        for (int i = 0; i < sizeOfBulletedListSection; i ++) {
                            lines.add(dis.readUTF());
                        }
                        resume.addSection(type, new BulletedListSection(lines));
                    }

                    if (sectionClass.equals(OrganizationSection.class)) {
                        setOrganizationSection(dis, resume);
                    }

                } while (dis.available() > 0);

            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

            return resume;
        }
    }

    private void writeGeneralInfo(Resume r, DataOutputStream dos) throws IOException {
        dos.writeUTF(r.getUuid());
        dos.writeUTF(r.getFullName());
    }

    private void writeContacts(Map<ContactType, String> contacts, DataOutputStream dos) throws  IOException {
        dos.writeInt(contacts.size());
        for (Map.Entry<ContactType, String> entry : contacts.entrySet()) {
            dos.writeUTF(entry.getKey().name());
            dos.writeUTF(entry.getValue());
        }
    }

    private void writeOrganizationSection(OrganizationSection section,
                                          Map.Entry<SectionType, Section> entry,
                                          DataOutputStream dos) throws IOException {

        List<Organization> experiences = section.getExperiences();
        dos.writeInt(experiences.size()); // кол-во организаций в секции

        for (Organization org : experiences) {
            dos.writeUTF(org.getHomePage().getName());// имя организации
            dos.writeUTF(org.getHomePage().getUrl()); // урл организации
            dos.writeInt(org.getPositions().size()); //кол-во позиций в организации

            for (Organization.Position position : org.getPositions()) {
                dos.writeUTF(position.getTitle()); //профессия
                dos.writeUTF(position.getDescription()); //что делал

                LocalDate start = position.getStart();
                dos.writeInt(start.getYear());
                dos.writeInt(start.getMonthValue());
                dos.writeInt(start.getDayOfMonth());

                LocalDate finish = position.getFinish();
                dos.writeInt(finish.getYear());
                dos.writeInt(finish.getMonthValue());
                dos.writeInt(finish.getDayOfMonth());
            }
        }
    }

    private Resume readResumeWithGeneralInfo(DataInputStream dis) throws IOException {
        String uuid = dis.readUTF();
        String fullName = dis.readUTF();
        return new Resume(uuid, fullName);
    }

    private Map<ContactType, String> readContact(DataInputStream dis) throws IOException {
        int count = dis.readInt();
        Map<ContactType, String> contacts = new HashMap<>();

        for (int i = 0; i < count; i ++) {
            String type = dis.readUTF();
            String value = dis.readUTF();
            contacts.put(ContactType.valueOf(type), value);
        }
        return contacts;
    }

    private void setOrganizationSection(DataInputStream dis, Resume resume) throws IOException {
        SectionType type = SectionType.valueOf(dis.readUTF());
        List<Organization> organizations = new ArrayList<>();

        // кол-во организаций в секции
        int sizeOfOrganizationSection = dis.readInt();

        // цикл на считывание всех организаций
        for (int i = 0; i < sizeOfOrganizationSection; i++) {
            // имя организации
            String nameOfOrganization = dis.readUTF();

            // урл организации
            String url = dis.readUTF();

            //кол-во позиций в организации
            int sizeOfOrganization = dis.readInt();
            List<Organization.Position> positions = new ArrayList<>();
            for (int j = 0; j < sizeOfOrganization; j++) {
                //профессия
                String profession = dis.readUTF();

                //что делал
                String description = dis.readUTF();

                //старт
                LocalDate start = LocalDate.of(dis.readInt(), dis.readInt(), dis.readInt());

                //конец
                LocalDate finish = LocalDate.of(dis.readInt(), dis.readInt(), dis.readInt());
                positions.add(new Organization.Position(profession, description, start, finish));

            }
            organizations.add(new Organization(nameOfOrganization, url, positions));
        }
        resume.addSection(type, new OrganizationSection(organizations));
    }
}