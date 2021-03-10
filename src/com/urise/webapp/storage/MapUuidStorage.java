package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MapUuidStorage extends AbstractStorage<String> {

    private Map<String, Resume> storage = new HashMap<>();

    @Override
    public void insert(Resume r, String key) {
        storage.put(key, r);
    }

    @Override
    public String getKeyFor(String uuid) {
        return uuid;
    }

    @Override
    public boolean isExist(String key) {
        return storage.get(key) != null;
    }

    @Override
    public void updateResume(Resume r, String key) {
        insert(r, key);
    }

    @Override
    public void deleteResume(String key) {
        storage.remove(key);
    }

    @Override
    public Resume getResume(String key) {
        return storage.get(key);
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
