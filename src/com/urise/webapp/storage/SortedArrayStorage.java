package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;

public class SortedArrayStorage extends AbstractArrayStorage {

    public Object getKeyFor(Resume r) {
        Resume searchKey = new Resume(r.getUuid());
        return Arrays.binarySearch(storage, 0, size, searchKey);
    }

    public void insert(Resume r, Object k) {
        int key = (Integer) k;
        int insertKey = -key - 1;
        System.arraycopy(storage, insertKey, storage, insertKey + 1, size - insertKey);
        storage[insertKey] = r;
        size++;
    }

    public void deleteElement(Object objectKey) {
        int key = (Integer) objectKey;
        System.arraycopy(storage, key + 1, storage, key, size - key - 1);
        size--;
    }
}


