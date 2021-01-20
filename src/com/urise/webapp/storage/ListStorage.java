package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class ListStorage extends AbstractStorage {

    private List<Resume> storage = new LinkedList<>();

    @Override
    public int size() {
        return storage.size();
    }

    @Override
    public void clear() {
        storage.clear();
    }

    @Override
    public Integer getKeyFor(String uuid) {
        int index = 0;
        for (Resume resume : storage) {
            if (resume.getUuid().equals(uuid)) {
                return index;
            } else {
                index ++;
            }
        }
        return -1;
    }

    @Override
    public  boolean objectAlreadyExistsFor(Object objectKey) {
        int key = (Integer) objectKey;
        return key >= 0;
    }

    @Override
    public void setObjectForKey(Resume r, Object objectKey) {
        int key = (Integer) objectKey;
        storage.set(key, r);
    }

    @Override
    public  boolean hasMoreSpace() {
        return true;
    }

    @Override
    public  void insert(Resume r, Object objectKey) {
        storage.add(r);
    }

    public Resume getResume(String uuid) {
        for (Resume r : storage) {
            if (r.getUuid().equals(uuid)) {
                return r;
            }
        }
        return null;
    }

    @Override
    public void deleteElement(Object objectKey) {
        int key = (Integer) objectKey;
        Resume resume = storage.get(key);
        storage.remove(resume);
    }

    @Override
    public Resume[] getAll() {
        return  storage.toArray(new Resume[storage.size()]);
    }
}
