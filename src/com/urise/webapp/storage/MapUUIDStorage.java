package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.*;

public class MapUUIDStorage extends AbstractStorage<String> {

    private Map<String, Resume> storage = new HashMap<>();

    @Override
    public void insert(Resume r, String k) {
        storage.put(k, r);
    }

    @Override
    public String getKeyFor(String uuid) {
        return uuid;
    }

    @Override
    public boolean objectAlreadyExistsFor(String key) {
        return storage.get(key) != null;
    }

    @Override
    public void setObjectForKey(Resume r, String k) {
        storage.put(k, r);
    }

    @Override
    public void deleteElement(String objectKey) {
        storage.remove(objectKey);
    }

    @Override
    public Resume getResume(String objectKey) {
       return storage.get(objectKey);
    }

    @Override
    public int size() {
        return storage.size();
    }

    @Override
    public void clear() {
        storage.clear();
    }

    @Override
    protected List<Resume> getAll() {
        return new ArrayList<>(storage.values());
    }

}
