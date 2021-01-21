package com.urise.webapp.storage;

import com.urise.webapp.Exception.ExistStorageException;
import com.urise.webapp.Exception.NotExistStorageException;
import com.urise.webapp.Exception.StorageException;
import com.urise.webapp.model.Resume;

public abstract class AbstractStorage implements Storage {

    @Override
    public void update(Resume r) {
        Object key = getExistedKeyFor(r.getUuid());
        setObjectForKey(r, key);
    }

    @Override
    public void save(Resume r) {
        Object key = getNotExistedKeyFor(r.getUuid());
        if (!hasMoreSpace()) {
            throw new StorageException("Storage overflow", r.getUuid());
        } else {
            insert(r, key);
        }
    }

    @Override
    public void delete(String uuid) {
        Object key = getExistedKeyFor(uuid);
        deleteElement(key);
    }

    @Override
    public Resume get(String uuid) {
        Object key = getExistedKeyFor(uuid);
        return getResume(key);
    }

    private Object getExistedKeyFor(String uuid) {
        Object key = getKeyFor(uuid);
        if (!objectAlreadyExistsFor(key)) {
            throw new NotExistStorageException(uuid);
        }
        return key;
    }

    private Object getNotExistedKeyFor(String uuid) {
        Object key = getKeyFor(uuid);
        if (objectAlreadyExistsFor(key)) {
            throw new ExistStorageException(uuid);
        }
        return key;
    }

    public abstract void insert(Resume r, Object k); //+

    public abstract boolean hasMoreSpace(); //+

    public abstract Object getKeyFor(String uuid); //+

    public abstract boolean objectAlreadyExistsFor(Object key);

    public abstract void setObjectForKey(Resume r, Object k);

    public abstract void deleteElement(Object objectKey);

    public abstract Resume getResume(Object objectKey);





}
