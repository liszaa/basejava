package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Iterator;

public class ArrayStorage extends AbstractArrayStorage {

    public int size() {
        return size;
    }

    @Override
    public Object getKeyFor(String uuid) {
        for (int i = 0; i < size; i++) {
            if (uuid.equals(storage[i].getUuid())) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public void saveResume(Resume r, Object k) {
        storage[size] = r;
    }

    @Override
    public void deleteResume(int key) {
        storage[key] = storage[size - 1];
    }

}