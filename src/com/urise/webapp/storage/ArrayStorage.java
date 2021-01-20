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
    public void insert(Resume r, Object k) {
        int insertIndex = size;
        storage[insertIndex] = r;
        size++;
    }

    @Override
    public void deleteElement(Object objectKey) {
        int key = (Integer) objectKey;
        storage[key] = storage[size - 1];
        size--;
    }

}