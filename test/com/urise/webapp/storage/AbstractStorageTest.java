package com.urise.webapp.storage;

import com.urise.webapp.exception.ExistStorageException;
import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.model.Resume;
import com.urise.webapp.ResumeTestData;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.*;

public abstract class AbstractStorageTest {

    static final String STORAGE_DIR = "/Users/lisa/Desktop/basejava/storage";

    private static Resume resume1;
    private static Resume resume2;
    private static Resume resume3;
    private static Resume resume4;

    static {
        resume1 = ResumeTestData.initResume("UUID_1", "Илья");
        resume2 = ResumeTestData.initResume("UUID_2", "Илья");
        resume3 = ResumeTestData.initResume("UUID_3", "Максим");
        resume4 = ResumeTestData.initResume("UUID_4", "Лиза");
    }

    Storage storage;


    AbstractStorageTest(Storage storage) {
        this.storage = storage;
    }

    @Before
    public void setup() {
        storage.clear();
        storage.save(resume1);
        storage.save(resume2);
        storage.save(resume3);
    }

    @Test
    public void getSizeTest() {
        assertEquals(3, storage.size());
    }

    @Test
    public void clearTest() {
        storage.clear();
        assertEquals(0, storage.size());
    }

    @Test
    public void updateResumeTest() {
        Resume resume4 = resume2;
        storage.update(resume4);
        assertEquals(resume4, storage.get(resume2.getUuid()));
    }

    @Test(expected = NotExistStorageException.class)
    public void updateNotExistResumeTest() {
        storage.update(resume4);
    }

    @Test
    public void saveResumeTest() {
        storage.save(resume4);
        try {
            storage.get(resume4.getUuid());
        } catch (NotExistStorageException e) {
            Assert.fail("Resume haven`t saved");
        }
        assertEquals(4, storage.size());
    }

    @Test(expected = ExistStorageException.class)
    public void saveExistResumeTest() {
        Resume resume4 = new Resume(resume1.getUuid(), resume1.getFullName());
        storage.save(resume4);
    }

    @Test(expected = NotExistStorageException.class)
    public void deleteResumeTest() {
        storage.delete(resume2.getUuid());
        assertEquals(2, storage.size());
        storage.get(resume2.getUuid());
    }

    @Test(expected = NotExistStorageException.class)
    public void deleteNotExistResumeTest() {
        storage.delete(resume4.getUuid());
    }

    @Test
    public void getResumeTest() {
        assertEquals(resume2, storage.get(resume2.getUuid()));
    }

    @Test(expected = NotExistStorageException.class)
    public void getNotExistResumeTest() {
        storage.get(resume4.getUuid());
    }

    @Test
    public void getAllTest() {
        List<Resume> expected = Arrays.asList(resume1, resume2, resume3);
        Collections.sort(expected);
        Assert.assertEquals(expected, storage.getAllSorted());
    }
}