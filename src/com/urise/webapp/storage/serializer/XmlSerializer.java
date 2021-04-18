package com.urise.webapp.storage.serializer;

import com.urise.webapp.exception.StorageException;
import com.urise.webapp.model.*;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.*;
import java.nio.charset.StandardCharsets;

public class XmlSerializer implements Serializer {

    private JAXBContext context;
    private Marshaller marshaller;
    private Unmarshaller unmarshaller;

    public XmlSerializer() {
        try {
            context = JAXBContext.newInstance(Resume.class, Organization.class, Link.class, OrganizationSection.class,
                    SingleLineSection.class, BulletedListSection.class, Organization.Position.class);
            marshaller = context.createMarshaller();
            unmarshaller = context.createUnmarshaller();
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void write(Resume r, OutputStream os) throws IOException {
        try (Writer w = new OutputStreamWriter(os, StandardCharsets.UTF_8)) {
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshaller.marshal(r, w);
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }



        @Override
    public Resume read(InputStream is) throws IOException {
        try (Reader r = new InputStreamReader(is, StandardCharsets.UTF_8)) {
            return (Resume) unmarshaller.unmarshal(r);
        } catch (JAXBException e) {
            throw new StorageException("Error read resume", null, e);
        }
    }
}
