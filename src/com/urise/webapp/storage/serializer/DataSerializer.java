package com.urise.webapp.storage.serializer;

import com.urise.webapp.model.*;

import java.io.*;
import java.time.LocalDate;
import java.util.*;

public class DataSerializer implements Serializer {

    @Override
    public void write(Resume r, OutputStream os) throws IOException {
        try (DataOutputStream dos = new DataOutputStream(os)) {
            writeGeneralInfo(r, dos);
            Map<ContactType, String> contacts = r.getContacts();
            writeCollection(dos, contacts.entrySet(), entry -> {
                        dos.writeUTF(entry.getKey().name());
                        dos.writeUTF(entry.getValue());
                    });

            writeCollection(dos, r.getSections().entrySet(), entry -> {
                Section section = entry.getValue();
                SectionType type = entry.getKey();
                dos.writeUTF(type.name());

                switch (type) {

                    case OBJECTIVE:
                    case PERSONAL:
                        dos.writeUTF(((SingleLineSection) section).getContent());
                        break;

                    case ACHIEVEMENT:
                    case QUALIFICATIONS:
                        writeCollection(dos, ((BulletedListSection) section).getContent(), dos::writeUTF);
                        break;

                    case EXPERIENCE:
                    case EDUCATION:
                        writeCollection(dos, ((OrganizationSection) section).getExperiences(), org -> {
                            Link homePage = org.getHomePage();

                            // имя организации
                            dos.writeUTF(homePage.getName());

                            // урл организации
                            dos.writeUTF(homePage.getUrl());

                            // список позиций
                            writeCollection(dos, org.getPositions(), position -> {
                                //профессия
                                dos.writeUTF(position.getTitle());

                                //что делал
                                dos.writeUTF(position.getDescription());

                                LocalDate start = position.getStart();
                                writeLocalDate(dos, start);

                                LocalDate finish = position.getFinish();
                                writeLocalDate(dos, finish);
                            });
                                });
                        break;
                }
                    });
        }
    }


    @Override
    public Resume read(InputStream is) throws IOException {
        try (DataInputStream dis = new DataInputStream(is)) {

            Resume resume = readResumeWithGeneralInfo(dis);
            resume.setContacts(readContact(dis));
            int sectionSize = dis.readInt();

                for (int i = 0; i < sectionSize; i++ ) {
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
                            for (int j = 0; j < sizeOfBulletedListSection; j++) {
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
                }
                System.out.println(resume);
                return resume;
        }
    }

    private <T> void writeCollection(DataOutputStream dos, Collection<T> collection, CustomWriter<T> writer) throws IOException {
        dos.writeInt(collection.size());
        for (T t : collection) {
            writer.write(t);
        }
    }

    private interface CustomWriter<T> {
        void write(T t) throws IOException;
    }


    private void writeGeneralInfo(Resume r, DataOutputStream dos) throws IOException {
        dos.writeUTF(r.getUuid());
        dos.writeUTF(r.getFullName());
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