package com.urise.webapp.storage;

import com.urise.webapp.Exception.ExistStorageException;
import com.urise.webapp.Exception.NotExistStorageException;
import com.urise.webapp.Exception.StorageException;
import com.urise.webapp.model.Resume;

import java.util.Collections;
import java.util.List;

public abstract class AbstractStorage<K> implements Storage {

    @Override
    public void update(Resume r) {
        K key = getExistedKeyFor(r.getUuid());
        setObjectForKey(r, key);
    }

    @Override
    public void save(Resume r) {
        K key = getNotExistedKeyFor(r.getUuid());
        insert(r, key);
    }

    @Override
    public void delete(String uuid) {
        K key = getExistedKeyFor(uuid);
        deleteElement(key);
    }

    @Override
    public Resume get(String uuid) {
        K key = getExistedKeyFor(uuid);
        return getResume(key);
    }

    private K getExistedKeyFor(String uuid) {
        K key = getKeyFor(uuid);
        if (!objectAlreadyExistsFor(key)) {
            throw new NotExistStorageException(uuid);
        }
        return key;
    }

    private K getNotExistedKeyFor(String uuid) {
        K key = getKeyFor(uuid);//resume
        if (objectAlreadyExistsFor(key)) { //передаю resume
            throw new ExistStorageException(uuid);
        }
        return key;
    }

    public List<Resume> getAllSorted() {
        List<Resume> list = getAll();
        Collections.sort(list);
        return list;
    }

    protected abstract List<Resume> getAll();

    public abstract void insert(Resume r, K k); //+

    public abstract K getKeyFor(String uuid); //+

    public abstract boolean objectAlreadyExistsFor(K key);

    public abstract void setObjectForKey(Resume r, K k);

    public abstract void deleteElement(K objectKey);

    public abstract Resume getResume(K objectKey);





}
