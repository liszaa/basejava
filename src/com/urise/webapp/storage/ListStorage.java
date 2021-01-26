package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.LinkedList;
import java.util.List;

public class ListStorage extends AbstractStorage<Integer> {

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
        for (int i = 0; i < storage.size(); i++) {
            if (storage.get(i).getUuid().equals(uuid)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public boolean objectAlreadyExistsFor(Integer key) {
        return key >= 0;
    }

    @Override
    public void updateResume(Resume r, Integer objectKey) {
        storage.set(objectKey, r);
    }


    @Override
    public void insert(Resume r, Integer objectKey) {
        storage.add(r);
    }

    public Resume getResume(Integer objectKey) {
        return storage.get(objectKey);
    }

    @Override
    public void deleteElement(Integer objectKey) {
        storage.remove(objectKey.intValue());
    }

    @Override
    public List<Resume> getAll() {
        return storage;
    }
}
