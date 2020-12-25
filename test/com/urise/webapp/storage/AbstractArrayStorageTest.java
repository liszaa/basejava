package com.urise.webapp.storage;

import com.urise.webapp.Exception.ExistStorageException;
import com.urise.webapp.Exception.NotExistStorageException;
import com.urise.webapp.Exception.StorageException;
import com.urise.webapp.model.Resume;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;

public abstract class AbstractArrayStorageTest {

    private Storage storage;
    private Resume resume1 = new Resume("1");
    private Resume resume2 = new Resume("2");
    private Resume resume3 = new Resume("3");

    AbstractArrayStorageTest(Storage anotherStorage) {
        this.storage = anotherStorage;
    }

    @Before
    private void fillStorage() {
        storage.clear();
        storage.save(resume1);
        storage.save(resume2);
        storage.save(resume3);
    }

    @Test
    public void getSizeTest() {
        assertSize(3);
    }

    @Test
    public void clearTest() {
        storage.clear();
        assertSize(0);
    }

    @Test
    public void updateResumeTest() {
        Resume resume4 = new Resume("1");
        storage.update(resume4);
        assertSame(storage.get("1"), resume4);
    }

    @Test(expected = NotExistStorageException.class)
    public void updateNotExistResume() {
        Resume resume4 = new Resume("4");
        storage.update(resume4);
    }

    @Test
    public void saveResumeTest() {
        Resume resume4 = new Resume("4");
        storage.save(resume4);
        try {
            storage.get("4");
        } catch (NotExistStorageException e) {
            Assert.fail();
        }
        assertSize(4);
    }

    @Test(expected = ExistStorageException.class)
    public void saveExistResume() {
        Resume resume4 = new Resume("1");
        storage.save(resume4);
    }

    @Test(expected = NotExistStorageException.class)
    public void deleteResumeTest() {
        storage.delete("2");
        assertSize(2);
        storage.get("2");
    }

    @Test(expected = NotExistStorageException.class)
    public void deleteNotExistResumeTest() {
        storage.delete("4");
    }

    @Test
    public void getResumeTest() {
        assertSame(resume2, storage.get("2"));
    }

    @Test(expected = NotExistStorageException.class)
    public void getNotExistResumeTest() {
        storage.get("4");
    }

    @Test(expected = StorageException.class)
    public void StorageOverflowTest() {
        for (int i = storage.size() + 1; i <= AbstractArrayStorage.STORAGE_LIMIT; i++) {
            try {
                storage.save(new Resume());
            } catch (StorageException e) {
                Assert.fail("The array overflowed ahead of time");
            }
        }
        storage.save(new Resume());
    }

    @Test
    public void getAll() {
        Resume[] expectedResumes = new Resume[]{resume1, resume2, resume3};
        Assert.assertArrayEquals(expectedResumes, storage.getAll());
    }

    private void assertSize(int expectedSize) {
        assertEquals(expectedSize, storage.size());
    }
}


