package com.urise.webapp.storage;

import com.urise.webapp.exception.StorageException;
import com.urise.webapp.model.Resume;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ObjectStreamFileStorage extends AbstractStorage<File> {

    protected File directory;
    private int size;
    private Serializer serializer;


    public ObjectStreamFileStorage(String path, Serializer serializer) {
        File directory = new File(path);
        Objects.requireNonNull(directory, "Directory must not be null");
        if (!directory.isDirectory()) {
            throw new IllegalArgumentException(directory.getAbsolutePath() + " is not a directory");
        }
        if (!directory.canRead() || !directory.canWrite()) {
            throw new IllegalArgumentException(directory.getAbsolutePath() + " is not readable/writable");
        }
        this.directory = directory;
        this.serializer = serializer;

    }

    @Override
    protected List<Resume> getAll() {
        List<Resume> resumes = new ArrayList<>();
        File[] files = directory.listFiles();
        Objects.requireNonNull(files);
        for (File file : files) {
            resumes.add(getResume(file));
        }
        return resumes;
    }

    @Override
    public void insert(Resume r, File file) {
        try {
            file.createNewFile();
            serializer.write(r, new BufferedOutputStream(new FileOutputStream(file)));
        } catch (IOException e) {
            throw new StorageException("IO error", file.getName(), e);
        }
        size++;
    }

    @Override
    public File getKeyFor(String uuid) {
        return new File(directory, uuid);
    }

    @Override
    public boolean isExist(File file) {
        return file.exists();
    }

    @Override
    public void updateResume(Resume r, File file) {
        try {
            serializer.write(r, new FileOutputStream(file));
        } catch (IOException e) {
            throw new StorageException("IO error", file.getName(), e);
        }
    }

    @Override
    public void deleteResume(File file) {
        file.delete();
        size--;
    }

    @Override
    public Resume getResume(File file) {
        try {
            return serializer.readResumeFrom(new BufferedInputStream(new FileInputStream(file)));
        } catch (IOException e) {
            throw new StorageException("File reading error", file.getName(), e);
        }
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void clear() {
        File[] files = directory.listFiles();
        Objects.requireNonNull(files);
        for (File file : files) {
            file.delete();
        }
        size = 0;
    }
}
