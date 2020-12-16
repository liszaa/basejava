package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

public class ArrayStorage extends AbstractArrayStorage {


    protected int getIndex(String uuid) {
        for (int i = 0; i < size; i++) {
            if (uuid.equals(storage[i].getUuid())) {
                return i;
            }
        }
        return -1;
    }

    public void insert(Resume r, int index) {
        int insertIndex = size;
        storage[insertIndex] = r;
    }

    public void deleteElement(int index) {
        storage[index] = storage[size - 1];
    }


}