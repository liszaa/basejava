package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class ListStorage extends AbstractStorage {

    private List<Resume> storage = new LinkedList<>();

    public int size() {
        return storage.size();
    }

    public void clear() {
        storage.clear();
    }

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

    public  boolean objectAlreadyExistsFor(Object objectKey) {
        int key = (Integer) objectKey;
        return key >= 0;
    }

    public void setObjectForKey(Resume r, Object objectKey) {
        int key = (Integer) objectKey;
        storage.set(key, r);
    }

    public  boolean hasMoreSpace() {
        return true;
    }

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

    public void deleteElement(Object objectKey) {
        int key = (Integer) objectKey;
        Resume resume = storage.get(key);
        storage.remove(resume);
    }

    public Resume[] getAll() {
        return  storage.toArray(new Resume[storage.size()]);
    }


//
//    @Override
//    public int size() {
//        return 0;
//    }
//
//    @Override
//    public void clear() {
//
//    }
//
//    @Override
//    public Resume get(String uuid) {
//        return null;
//    }
//
//    @Override
//    public void delete(String uuid) {
//
//    }
//
//    @Override
//    public Resume[] getAll() {
//        return new Resume[0];
//    }
}
