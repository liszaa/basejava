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
    public void fillStorage() {
        storage.clear();
        storage.save(resume1);
        storage.save(resume2);
        storage.save(resume3);
    }

    @Test
    public void getSizeTest() {
        int expectedSize = 3;
        Assert.assertEquals(expectedSize, storage.size());
    }

    @Test
    public void clearTest() {
        storage.clear();
        int expectedSize = 0;
        Assert.assertEquals(expectedSize, storage.size());
        assertSize(4);
    }

    @Test
    public void updateResumeTest() {
        Resume resume4 = new Resume("1");
        Resume resume1 = storage.get("1");
        assertSame(storage.get("1"), resume1);
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
        int expectedSize = 4;
        assertEquals(expectedSize, storage.size());
    }

    @Test(expected = ExistStorageException.class)
    public void saveNotExistResume() {
        Resume resume4 = new Resume("1");
        storage.save(resume4);
    }

    @Test
    public void deleteResumeTest() {
        storage.delete("2");
        int expectedSize = 2;
        Assert.assertEquals(expectedSize, storage.size());
    }

    @Test(expected = NotExistStorageException.class)
    public void deleteNotExistResumeTest() {
        storage.delete("4");
    }

    @Test
    public void getResumeTest() {
        Resume resume = storage.get("2");
        assertSame(resume, storage.get("2"));
    }

    @Test(expected = NotExistStorageException.class)
    public void getNotExistResumeTest() {
        storage.get("4");
    }

    @Test(expected = StorageException.class)
    public void StorageOverflowTest() {
        for (int i = 4; i <= 10_000; i++) {
            try {
                storage.save(new Resume());
            } catch (StorageException e) {
                Assert.fail("The array overflowed ahead of time");
            }
        }
        storage.save(new Resume());
    }

    private void assertSize(int expectedSize) {
        assertEquals(storage.size(), expectedSize);
    }

    @Test
    public void getAll() {
        Resume[] expectedArray = new Resume[]{resume1, resume2, resume3};
        Assert.assertArrayEquals(expectedArray, storage.getAll());
    }
}


