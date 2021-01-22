package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MapRESUMEStorage extends AbstractStorage {

        private Map<String, Resume> map = new HashMap<>();

        @Override
        public Resume getKeyFor(String uuid) {
            return map.get(uuid);
        }

        @Override
        public void setObjectForKey(Resume r, Object resume) {
            map.put(r.getUuid(), r);
        }

        @Override
        public boolean objectAlreadyExistsFor(Object resume) {
            return resume != null;
        }

        @Override
        public void insert(Resume r, Object resume) {
            map.put(r.getUuid(), r);
        }

        @Override
        public Resume getResume(Object resume) {
            return (Resume) resume;
        }

        @Override
        public void deleteElement(Object resume) {
            map.remove(((Resume) resume).getUuid());
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
