package com.urise.webapp.storage;

import com.urise.webapp.Exception.StorageException;
import com.urise.webapp.model.Resume;
import org.junit.Assert;
import org.junit.Test;

public abstract class AbstractArrayStorageTest extends AbstractStorageTest {

    AbstractArrayStorageTest(Storage storage) {
        super(storage);
    }

    @Test(expected = StorageException.class)
    public void testStorageOverflow() {
        for (int i = storage.size() + 1; i < AbstractArrayStorage.STORAGE_LIMIT + 1; i++) {
            try {
                storage.save(new Resume("Лиза"));
            } catch (StorageException e) {
                Assert.fail("The array overflowed ahead of time");
            }
        }
        storage.save(new Resume("Лиза"));
    }
}