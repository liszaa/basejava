package com.urise.webapp.storage;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.urise.webapp.Exception.ExistStorageException;
import com.urise.webapp.Exception.NotExistStorageException;
import com.urise.webapp.Exception.StorageException;
import com.urise.webapp.model.Resume;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public abstract class AbstractStorageTest {

    Storage storage;

    Resume resume1 = new Resume("Илья");
    Resume resume2 = new Resume("Илья");
    Resume resume3 = new Resume("Максим");


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
        Resume resume4 = new Resume("Лиза");
        storage.update(resume4);
    }

    @Test
    public void testSaveResume() {
        Resume resume4 = new Resume("Лиза");
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
        storage.delete(new Resume("Лиза").getUuid());
    }

    @Test
    public void testGetResume() {
        assertSame(resume2, storage.get(resume2.getUuid()));
    }

    @Test(expected = NotExistStorageException.class)
    public void testGetNotExistResume() {
        storage.get(new Resume("Лиза").getUuid());
    }
    @Test
    public void testGetAll() {
        List<Resume> expected = Arrays.asList(resume1, resume2, resume3);
        Collections.sort(expected);
        Assert.assertEquals(expected, storage.getAllSorted());
    }
}