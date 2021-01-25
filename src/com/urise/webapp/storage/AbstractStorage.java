package com.urise.webapp.storage;

import com.urise.webapp.Exception.ExistStorageException;
import com.urise.webapp.Exception.NotExistStorageException;
import com.urise.webapp.model.Resume;

import java.util.Collections;
import java.util.List;
import java.util.logging.Logger;


public abstract class AbstractStorage<K> implements Storage {

    private static final Logger LOG = Logger.getLogger(AbstractStorage.class.getName());

    @Override
    public void update(Resume r) {
        LOG.info("Update " + r);
        K key = getExistedKeyFor(r.getUuid());
        updateResume(r, key);
    }

    @Override
    public void save(Resume r) {
        LOG.info("save " + r);
        K key = getNotExistedKeyFor(r.getUuid());
        insert(r, key);
    }

    @Override
    public void delete(String uuid) {
        LOG.info("delete " + uuid);
        K key = getExistedKeyFor(uuid);
        deleteElement(key);
    }

    @Override
    public Resume get(String uuid) {
        LOG.info("get " + uuid);
        K key = getExistedKeyFor(uuid);
        return getResume(key);
    }

    private K getExistedKeyFor(String uuid) {
        K key = getKeyFor(uuid);
        if (!objectAlreadyExistsFor(key)) {
            LOG.warning("Resume " + uuid + " not exist");
            throw new NotExistStorageException(uuid);
        }
        return key;
    }

    private K getNotExistedKeyFor(String uuid) {
        K key = getKeyFor(uuid);
        if (objectAlreadyExistsFor(key)) {
            LOG.warning("Resume " + uuid + " already exist");
            throw new ExistStorageException(uuid);
        }
        return key;
    }

    public List<Resume> getAllSorted() {
        LOG.info("getAllSorted");
        List<Resume> list = getAll();
        Collections.sort(list);
        return list;
    }

    protected abstract List<Resume> getAll();

    public abstract void insert(Resume r, K k); //+

    public abstract K getKeyFor(String uuid); //+

    public abstract boolean objectAlreadyExistsFor(K key);

    public abstract void updateResume(Resume r, K k);

    public abstract void deleteElement(K objectKey);

    public abstract Resume getResume(K objectKey);


}
