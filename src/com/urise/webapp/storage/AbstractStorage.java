package com.urise.webapp.storage;

import com.urise.webapp.Exception.ExistStorageException;
import com.urise.webapp.Exception.NotExistStorageException;
import com.urise.webapp.Exception.StorageException;
import com.urise.webapp.model.Resume;

public abstract class AbstractStorage implements Storage {

    @Override
    public void update(Resume r) {
        Object key = getKeyFor(r.getUuid());
        if (objectAlreadyExistsFor(key)) {
            setObjectForKey(r, key);
        } else {
            throw new NotExistStorageException(r.getUuid());
        }
    }

    @Override
    public void save(Resume r) {
        Object key = getKeyFor(r.getUuid());
        if (objectAlreadyExistsFor(key)) {
            throw new ExistStorageException(r.getUuid());
        } else if (!hasMoreSpace()) {
            throw new StorageException("Storage overflow", r.getUuid());
        } else {
            insert(r, key);
        }
    }

    @Override
    public void delete(String uuid) {
        if (getResume(uuid) == null) {
            throw new NotExistStorageException(uuid);
        } else {
            Object key = getKeyFor(uuid);
            deleteElement(key);
        }
    }

    @Override
    public Resume get(String uuid) {
        Object key = getKeyFor(uuid);
        if (objectAlreadyExistsFor(key)) {
            return getResume(uuid);
        } else {
            throw new NotExistStorageException(uuid);
        }
    }

    public abstract void insert(Resume r, Object k); //+

    public abstract boolean hasMoreSpace(); //+

    public abstract Object getKeyFor(String uuid); //+

    public abstract boolean objectAlreadyExistsFor(Object key);

    public abstract void setObjectForKey(Resume r, Object k);

    public abstract void deleteElement(Object objectKey);

    public abstract Resume getResume(String uuid);



}
