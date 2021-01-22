package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.*;

public class MapStorage extends AbstractStorage {

    private Map<String, Resume> storage = new HashMap<>();

    @Override
    public void insert(Resume r, Object k) {
        storage.put((String) k, r);
    }

    @Override
    public Object getKeyFor(String uuid) {
        return uuid;
    }

    @Override
    public boolean objectAlreadyExistsFor(Object key) {
        return storage.get(key) != null;
    }

    @Override
    public void setObjectForKey(Resume r, Object k) {
        storage.put((String) k, r);
    }

    @Override
    public void deleteElement(Object objectKey) {
        storage.remove(objectKey);
    }

    @Override
    public Resume getResume(Object objectKey) {
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
