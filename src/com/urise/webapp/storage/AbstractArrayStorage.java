package com.urise.webapp.storage;

import com.urise.webapp.Exception.StorageException;
import com.urise.webapp.model.Resume;

import java.util.Arrays;
import java.util.List;

public abstract class AbstractArrayStorage extends AbstractStorage<Integer> {
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
    public Resume getResume(Integer objectKey) {
        return storage[objectKey];
    }

    @Override
    public boolean objectAlreadyExistsFor(Integer objectKey) {
//        int key = objectKey;
        return objectKey >= 0;
    }

    @Override
    public void setObjectForKey(Resume r, Integer objectKey) {
        storage[objectKey] = r;
    }

    @Override
    public void insert(Resume r, Integer k) {
        if (size() == STORAGE_LIMIT) {
            throw new StorageException("Storage overflow", r.getUuid());
        } else {
            saveResume(r, k);
            size ++;
        }
    }

    public void deleteElement(Integer objectKey) {
        int key = objectKey;
        deleteResume(key);
        size--;
    }

    protected abstract void deleteResume(int objectKey);

    public abstract void saveResume(Resume r, Integer k);
}

