package com.urise.webapp.storage.serializer;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.urise.webapp.exception.StorageException;
import com.urise.webapp.model.*;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.*;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;

public class JsonSerializer implements Serializer {

    private static Gson GSON = new GsonBuilder()
            .registerTypeAdapter(Section.class, new JsonSectionAdapter())
            .setPrettyPrinting()
            .create();


    @Override
    public void write(Resume r, OutputStream os) throws IOException {
        try (Writer w = new OutputStreamWriter(os, StandardCharsets.UTF_8)) {
            GSON.toJson(r, w);
        }
    }

    @Override
    public Resume read(InputStream is) throws IOException {
        try (Reader r = new InputStreamReader(is, StandardCharsets.UTF_8)) {
            return GSON.fromJson(r, Resume.class);
        }
    }
}
