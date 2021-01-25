package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;
import java.util.Comparator;

public class SortedArrayStorage extends AbstractArrayStorage {

    private static final Comparator<Resume> RESUME_COMPARATOR = Comparator.comparing(Resume::getUuid);

    @Override
    public Integer getKeyFor(String uuid) {
        Resume searchKey = new Resume(uuid, "searchKey");
        return Arrays.binarySearch(storage, 0, size, searchKey, RESUME_COMPARATOR);
    }

    @Override
    public void saveResume(Resume r, Integer k) {
        int insertKey = -k - 1;
        System.arraycopy(storage, insertKey, storage, insertKey + 1, size - insertKey);
        storage[insertKey] = r;
    }

    @Override
    public void deleteResume(int key) {
        System.arraycopy(storage, key + 1, storage, key, size - key - 1);
    }
}