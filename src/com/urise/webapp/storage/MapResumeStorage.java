package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MapResumeStorage extends AbstractStorage<Resume> {

    private Map<String, Resume> map = new HashMap<>();

    @Override
    public Resume getKeyFor(String uuid) {
        return map.get(uuid);
    }

    @Override
    public void updateResume(Resume r, Resume resume) {
        insert(r, resume);
    }

    @Override
    public boolean objectAlreadyExistsFor(Resume resume) {
        return resume != null;
    }

    @Override
    public void insert(Resume r, Resume resume) {
        map.put(r.getUuid(), r);
    }

    @Override
    public Resume getResume(Resume resume) {
        return resume;
    }

    @Override
    public void deleteElement(Resume resume) {
        map.remove(resume.getUuid());
    }

    @Override
    public void clear() {
        map.clear();
    }

    @Override
    public List<Resume> getAll() {
        return new ArrayList<>(map.values());
    }

    @Override
    public int size() {
        return map.size();
    }
}
