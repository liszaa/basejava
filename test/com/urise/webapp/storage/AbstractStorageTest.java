package com.urise.webapp.storage;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.urise.webapp.Exception.ExistStorageException;
import com.urise.webapp.Exception.NotExistStorageException;
import com.urise.webapp.Exception.StorageException;
import com.urise.webapp.model.Resume;

public abstract class AbstractStorageTest {

    Storage storage;

    Resume resume1 = new Resume();
    Resume resume2 = new Resume();
    Resume resume3 = new Resume();


    AbstractStorageTest(Storage storage) {
        this.storage = storage;
    }

    @Before
    public void fillStorage() {
        storage.clear();
        storage.save(resume1);
        storage.save(resume2);
        storage.save(resume3);
    }

    @Test
    public void testGetSizeTest() {
        assertEquals(3, storage.size());
    }

    @Test
    public void testClear() {
        storage.clear();
        assertEquals(0, storage.size());
    }

    @Test
    public void testUpdateResume() {
        Resume resume4 = new Resume(resume2.getUuid());
        storage.update(resume4);
        assertEquals(storage.get(resume2.getUuid()), resume4);
    }

    @Test(expected = NotExistStorageException.class)
    public void testUpdateNotExistResume() {
        Resume resume4 = new Resume();
        storage.update(resume4);
    }

    @Test
    public void testSaveResume() {
        Resume resume4 = new Resume();
        storage.save(resume4);
        try {
            storage.get(resume4.getUuid());
        } catch (NotExistStorageException e) {
            Assert.fail();
        }
        assertEquals(4, storage.size());
    }

    @Test(expected = ExistStorageException.class)
    public void testSaveExistResume() {
        Resume resume4 = new Resume(resume1.getUuid());
        storage.save(resume4);
    }

    @Test(expected = NotExistStorageException.class)
    public void testDeleteResumeTest() {
        storage.delete(resume2.getUuid());
        assertEquals(2, storage.size());
        storage.get(resume2.getUuid());
    }

    @Test(expected = NotExistStorageException.class)
    public void testDeleteNotExistResume() {
        storage.delete(new Resume().getUuid());
    }

    @Test
    public void testGetResume() {
        assertSame(resume2, storage.get(resume2.getUuid()));
    }

    @Test(expected = NotExistStorageException.class)
    public void testGetNotExistResume() {
        storage.get(new Resume().getUuid());
    }

    @Test(expected = StorageException.class)
    public void testStorageOverflow() {
        for (int i = storage.size() + 1; i < AbstractArrayStorage.STORAGE_LIMIT + 1; i++) {
            try {
                storage.save(new Resume());
            } catch (StorageException e) {
                Assert.fail("The array overflowed ahead of time");
            }
        }
        storage.save(new Resume());
    }

    @Test
    public void testGetAll() {
        Resume[] expected = new Resume[]{resume1, resume2, resume3};
        Assert.assertArrayEquals(expected, storage.getAll());
    }

}