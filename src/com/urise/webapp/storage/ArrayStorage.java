package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

public class ArrayStorage extends AbstractArrayStorage {

    public int size() {
        return size;
    }

    public Object getKeyFor(Resume r) {
        for (int i = 0; i < size; i++) {
            if (r.getUuid().equals(storage[i].getUuid())) {
                return i;
            }
        }
        return -1;
    }

    public void insert(Resume r, Object k) {
        int insertIndex = size;
        storage[insertIndex] = r;
        size++;
    }

    public void deleteElement(Object objectKey) {
        int key = (Integer) objectKey;
        storage[key] = storage[size - 1];
        size--;
    }


}