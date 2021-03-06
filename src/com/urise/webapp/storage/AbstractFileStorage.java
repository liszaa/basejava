package com.urise.webapp.storage;

import com.urise.webapp.Exception.StorageException;
import com.urise.webapp.model.Resume;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public abstract class AbstractFileStorage extends AbstractStorage<File> {

    protected File directory;
    private int size;

    public AbstractFileStorage(File directory) {
        Objects.requireNonNull(directory, "Directory must not be null");
        if (!directory.isDirectory()) {
            throw new IllegalArgumentException(directory.getAbsolutePath() + " is not a directory");
        }
        if (!directory.canRead() || !directory.canWrite()) {
            throw new IllegalArgumentException(directory.getAbsolutePath() + " is not readable/writable");
        }
        this.directory = directory;
    }

    @Override
    protected List<Resume> getAll() {
        List<Resume> resumes = new ArrayList<>();
        File[] files = directory.listFiles();
        assert files != null;
        for (File file : files) {
            resumes.add(getResumeFromFile(file));
        }
        return resumes;
    }

    @Override
    public void insert(Resume r, File file) {
        try {
            file.createNewFile();
            write(r, file);
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
    public boolean objectAlreadyExistsFor(File file) {
        return file.exists();
    }

    @Override
    public void updateResume(Resume r, File file) {
        try {
            write(r, file);
        } catch (IOException e) {
            throw new StorageException("IO error", file.getName(), e);
        }
    }

    @Override
    public void deleteElement(File file) {
        file.delete();
        size--;
    }

    @Override
    public Resume getResume(File file) {
        return getResumeFromFile();
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void clear() {
        File[] files = directory.listFiles();
        assert files != null;
        for (File file : files) {
            file.delete();
        }
        size = 0;
    }

    abstract void write(Resume r, File file) throws IOException;

    abstract Resume getResumeFromFile(File file);

}
