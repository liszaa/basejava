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
                            dos.writeUTF(homePage.getUrl() == null ? "" : homePage.getUrl());

                            // список позиций
                            writeCollection(dos, org.getPositions(), position -> {
                                //профессия
                                dos.writeUTF(position.getTitle());

                                //что делал
                                String description = position.getDescription();
                                dos.writeUTF(description == null ? "" : description);

                                LocalDate start = position.getStart();
                                writeLocalDate(dos, start);

                                LocalDate finish = position.getFinish();
                                writeLocalDate(dos, finish);

                            });
                                });
                        break;
                }
                    });
            System.out.println("Contacts before serialization".toUpperCase());
            System.out.println(r.getContacts());
        }
    }


    @Override
    public Resume read(InputStream is) throws IOException {
        try (DataInputStream dis = new DataInputStream(is)) {

            Resume resume = readResumeWithGeneralInfo(dis);

            resume.setContacts(readContact(dis));
            int sectionSize = dis.readInt();

                for (int i = 0; i < sectionSize; i++) {

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
                            List<String> lines = readList(dis, dis::readUTF);
                            resume.addSection(type, new BulletedListSection(lines));
                            break;

                        case EXPERIENCE:
                        case EDUCATION:
                            List<Organization> organizations = readList(dis, () -> {
                                // имя организации
                                String nameOfOrganization = dis.readUTF();

                                // урл организации
                                String string = dis.readUTF();
                                String url = string.equals("") ? null : string;

                                List<Organization.Position> positions = readList(dis, () -> {

                                    String profession = dis.readUTF();
                                    //что делал
                                    String line = dis.readUTF();
                                    String description = line.equals("") ? null : line;
                                    //старт
                                    LocalDate start = readLocalDate(dis);
                                    //конец
                                    LocalDate finish = readLocalDate(dis);

                                    return new Organization.Position(profession, description, start, finish);
                                });

                                return new Organization(nameOfOrganization, url, positions);
                            });
                            resume.addSection(type, new OrganizationSection(organizations));
                            break;
                    }
                }
                System.out.println("Contacts after serialization".toUpperCase());
                System.out.println(resume.getContacts());
                return resume;
        }
    }


    private void writeGeneralInfo(Resume r, DataOutputStream dos) throws IOException {
        dos.writeUTF(r.getUuid());
        dos.writeUTF(r.getFullName());
    }

    private interface CustomWriter<T> {
        void write(T t) throws IOException;
    }

    private <T> void writeCollection(DataOutputStream dos, Collection<T> collection, CustomWriter<T> writer) throws IOException {
        dos.writeInt(collection.size());
        for (T t : collection) {
            writer.write(t);
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
        Map<ContactType, String> contacts = new LinkedHashMap<>();

        for (int i = 0; i < count; i ++) {
            String type = dis.readUTF();
            String value = dis.readUTF();
            contacts.put(ContactType.valueOf(type), value);
        }
        return contacts;
    }

    private interface CustomElementReader<T> {
        T read() throws IOException;
    }

    private <T> List<T> readList(DataInputStream dis, CustomElementReader<T> reader) throws IOException {
        int listSize = dis.readInt();
        List<T> list = new ArrayList<>();
        for (int i = 0; i < listSize; i++) {
            list.add(reader.read());
        }
        return list;
    }

    private LocalDate readLocalDate(DataInputStream dis) throws IOException {
        return LocalDate.of(dis.readInt(), dis.readInt(), dis.readInt());
    }
}