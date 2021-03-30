package com.urise.webapp.storage;

import com.urise.webapp.exception.StorageException;
import com.urise.webapp.model.Resume;
import com.urise.webapp.storage.serializer.Serializer;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class PathStorage extends AbstractStorage<Path> {

    protected Path directory;
    private Serializer serializer;

    public PathStorage(String path, Serializer serializer) {
        Path directory = Paths.get(path);
        Objects.requireNonNull(directory, "Directory must not be null");
        if (!Files.isDirectory(directory)) {
            throw new IllegalArgumentException(directory.toString() + " is not a directory");
        }
        if (!Files.isReadable(directory) || !Files.isWritable(directory)) {
            throw new IllegalArgumentException(directory.toString() + " is not readable/writable");
        }
        this.directory = directory;
        this.serializer = serializer;
    }

    @Override
    protected List<Resume> getAll() {
        return getFiles().map(this::getResume).collect(Collectors.toList());
    }

    @Override
    public void insert(Resume r, Path path) {
        try {
            Files.createFile(path);
        } catch (IOException e) {
            throw new StorageException("IO error", path.toString(), e);
        }
        updateResume(r, path);
    }

    @Override
    public Path getKeyFor(String uuid) {
        return directory.resolve(uuid);
    }

    @Override
    public boolean isExist(Path path) {
        return Files.exists(path);
    }

    @Override
    public void updateResume(Resume r, Path path) {
        try {
            serializer.write(r, new BufferedOutputStream(Files.newOutputStream(path)));
        } catch (IOException e) {
            throw new StorageException("IO error", path.toString(), e);
        }
    }

    @Override
    public void deleteResume(Path path) {
        try {
            Files.delete(path);
        } catch (IOException e) {
            throw new StorageException("File cannot be deleted", path.toString());
        }
    }

    @Override
    public Resume getResume(Path path) {
        try {
            return serializer.read(new BufferedInputStream(Files.newInputStream(path)));
        } catch (IOException e) {
            throw new StorageException("File reading error", path.toString(), e);
        }
    }

    @Override
    public int size() {
        return (int) getFiles().count();
    }

    @Override
    public void clear() {
        getFiles().forEach(this::deleteResume);
    }

    private Stream<Path> getFiles() {
        try {
            return Files.list(directory);
        } catch (IOException e) {
            throw new StorageException("Directory must be not empty", directory.toString(), e);
        }
    }

}