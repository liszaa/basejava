package com.urise.webapp.storage;

import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.exception.StorageException;
import com.urise.webapp.model.Resume;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ObjectStreamPathStorage extends AbstractStorage<Path> {

    protected Path directory;
    private int size;
    private Serializier serializier;

    public ObjectStreamPathStorage(String path, Serializier serializier) {
        Path directory = Paths.get(path);
        Objects.requireNonNull(directory, "Directory must not be null");
        if (!Files.isDirectory(directory)) {
            throw new IllegalArgumentException(directory.toString() + " is not a directory");
        }
        if (!Files.isReadable(directory) || !Files.isWritable(directory)) {
            throw new IllegalArgumentException(directory.toString() + " is not readable/writable");
        }
        this.directory = directory;
        this.serializier = serializier;
    }

    @Override
    protected List<Resume> getAll() {
        try {
            List<Path> paths = Files.walk(directory).skip(1).collect(Collectors.toList());
            List<Resume> result = new ArrayList<>();
            System.out.println("getAll");
            for (Path path : paths) {
                result.add(getResume(path));
            }
            return result;
        } catch (IOException e) {
            throw new StorageException("IO error", "", e);
        }
    }

    @Override
    public void insert(Resume r, Path path) {
        try {
            System.out.println("going to save resume " + r.getUuid() + " to file: " + path.toString());
            Files.createFile(path);
            serializier.write(r, new BufferedOutputStream(Files.newOutputStream(path)));
        } catch (IOException e) {
            throw new StorageException("IO error", path.toString(), e);
        }
        size++;
    }

    @Override
    public Path getKeyFor(String uuid) {
        System.out.println("input = " + uuid);
        System.out.println("result = " + Paths.get(directory.toAbsolutePath().toString(), uuid));
        return Paths.get(directory.toAbsolutePath().toString(), uuid);
    }

    @Override
    public boolean isExist(Path path) {
        System.out.println("path = " + path.toString());
        System.out.println("exists = " + Files.exists(path));
        return Files.exists(path);
    }

    @Override
    public void updateResume(Resume r, Path path) {
        try {
            serializier.write(r, Files.newOutputStream(path));
        } catch (IOException e) {
            throw new StorageException("IO error", path.toString(), e);
        }
    }

    @Override
    public void deleteResume(Path path) {
        try {
            Files.delete(path);
        } catch (IOException e) {
            System.out.println(e);
            throw new NotExistStorageException(path.toString());
        }
        size--;
    }

    @Override
    public Resume getResume(Path path) {
        try {
            return serializier.readResumeFrom(new BufferedInputStream(Files.newInputStream(path)));
        } catch (IOException e) {
            throw new StorageException("File reading error", path.toString(), e);
        }
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void clear() {
        try {
            Stream<Path> stream = Files.walk(directory);
            stream.skip(1).forEach(this::deleteResume);
            size = 0;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
