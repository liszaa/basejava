package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;

public abstract class AbstractArrayStorage extends AbstractStorage {
    static final int STORAGE_LIMIT = 10_000;

    protected Resume[] storage = new Resume[STORAGE_LIMIT];
    int size = 0;

    public int size() {
        return size;
    }

    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }


    public Resume[] getAll() {
        return Arrays.copyOfRange(storage, 0, size);
    }

    @Override
    public Resume get(String uuid) {
        for (int i = 0; i < size; i++) {
            if (storage[i].getUuid().equals(uuid)) {
                return storage[i];
            }
        }
        return null;
    }

    public boolean objectAlreadyExistsFor(Object objectKey) {
        int key = (Integer) objectKey;
        return key >= 0;
    }

    public void setObjectForKey(Resume r, Object objectKey) {
        int key = (Integer) objectKey;
        storage[key] = r;
    }

    public boolean hasMoreSpace() {
        return size >= STORAGE_LIMIT;
    }

    public abstract void deleteElement(Object objectKey);

}

