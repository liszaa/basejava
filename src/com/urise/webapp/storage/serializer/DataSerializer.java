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
                SectionType type = entry.getKey();
                dos.writeUTF(type.name());

                switch (type) {
                    case OBJECTIVE:
                    case PERSONAL:
                        String contentSingleLineSection = ((SingleLineSection) section).getContent();
                        System.out.println(contentSingleLineSection);
                        dos.writeUTF(contentSingleLineSection);
                        break;

                    case ACHIEVEMENT:
                    case QUALIFICATIONS:
                        BulletedListSection listSection = (BulletedListSection) section;
                        List<String> contentBulletedListSection = listSection.getContent();
                        dos.writeInt(contentBulletedListSection.size());
                        for (String line : contentBulletedListSection) {
                            dos.writeUTF(line);
                        }
                        break;
                    case EXPERIENCE:
                    case EDUCATION:
                        writeOrganizationSection((OrganizationSection) section, entry, dos);
                        break;
                }
            }
        }
    }

    @Override
    public Resume read(InputStream is) throws IOException {
        try (DataInputStream dis = new DataInputStream(is)) {

            Resume resume = readResumeWithGeneralInfo(dis);
            resume.setContacts(readContact(dis));

                do {
                    SectionType type = SectionType.valueOf(dis.readUTF());
                    switch (type) {
                        case PERSONAL:
                        case OBJECTIVE:
                            String content = dis.readUTF();
                            SingleLineSection section = new SingleLineSection(content);
                            resume.addSection(type, section);
                            break;

                        case ACHIEVEMENT:
                        case QUALIFICATIONS:
                            List<String> lines = new ArrayList<>();
                            int sizeOfBulletedListSection = dis.readInt();
                            System.out.println(sizeOfBulletedListSection);
                            for (int i = 0; i < sizeOfBulletedListSection; i++) {
                                String line = dis.readUTF();
                                System.out.println(line);
                                lines.add(line);
                            }
                            resume.addSection(type, new BulletedListSection(lines));
                            break;

                        case EXPERIENCE:
                        case EDUCATION:
                            setOrganizationSection(dis, resume, type);
                            break;
                    }
                }  while (dis.available() > 0);

                System.out.println(resume);
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
                writeLocalDate(dos, start);

                LocalDate finish = position.getFinish();
                writeLocalDate(dos, finish);
            }
        }
    }

    private void writeLocalDate(DataOutputStream dos, LocalDate finish) throws IOException {
        dos.writeInt(finish.getYear());
        dos.writeInt(finish.getMonthValue());
        dos.writeInt(finish.getDayOfMonth());
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

    private void setOrganizationSection(DataInputStream dis, Resume resume, SectionType type) throws IOException {

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
                LocalDate start = readLocalDate(dis);

                //конец
                LocalDate finish = readLocalDate(dis);
                positions.add(new Organization.Position(profession, description, start, finish));

            }
            organizations.add(new Organization(nameOfOrganization, url, positions));
        }
        resume.addSection(type, new OrganizationSection(organizations));
    }

    private LocalDate readLocalDate(DataInputStream dis) throws IOException {
        return LocalDate.of(dis.readInt(), dis.readInt(), dis.readInt());
    }
}