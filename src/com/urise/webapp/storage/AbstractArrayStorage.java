package com.urise.webapp.storage;

import com.urise.webapp.Exception.StorageException;
import com.urise.webapp.model.Resume;

import java.util.Arrays;
import java.util.List;

public abstract class AbstractArrayStorage extends AbstractStorage {
    static final int STORAGE_LIMIT = 10_000;

    protected Resume[] storage = new Resume[STORAGE_LIMIT];
    int size = 0;

    @Override
    public int size() {
        return size;
    }

    @Override
    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    @Override
    public List<Resume> getAll() {
        return Arrays.asList(Arrays.copyOfRange(storage, 0, size));
    }

    @Override
    public Resume getResume(Object objectKey) {
        return storage[(Integer) objectKey];
    }

    @Override
    public boolean objectAlreadyExistsFor(Object objectKey) {
        int key = (Integer) objectKey;
        return key >= 0;
    }

    @Override
    public void setObjectForKey(Resume r, Object objectKey) {
        int key = (Integer) objectKey;
        storage[key] = r;
    }

    @Override
    public void insert(Resume r, Object k) {
        if (size() == STORAGE_LIMIT) {
            throw new StorageException("Storage overflow", r.getUuid());
        } else {
            saveResume(r, k);
            size ++;
        }
    }

    public void deleteElement(Object objectKey) {
        int key = (Integer) objectKey;
        deleteResume(key);
        size--;
    }

    protected abstract void deleteResume(int objectKey);

    public abstract void saveResume(Resume r, Object k);
}

