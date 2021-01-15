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

    private Storage storage;

    private Resume resume1 = new Resume();
    private Resume resume2 = new Resume();
    private Resume resume3 = new Resume();


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
        testAssertSize(3);
    }

    @Test
    public void testClear() {
        storage.clear();
        testAssertSize(0);
    }

    @Test
    public void testUpdateResume() {
        //нужно создать новое резюме с уже существеющим uuid
        //обновить на него резюме 2
        // проверить по ссылке, что теперь на месте резюме 1 резюме 4
        Resume resume4 = new Resume(resume2.getUuid());
        storage.update(resume4);
        assertEquals(storage.get(resume2.getUuid()), resume4);
    }

    @Test(expected = NotExistStorageException.class)
    //всё верно
    public void testUpdateNotExistResume() {
        Resume resume4 = new Resume();
        storage.update(resume4);
    }

    @Test
    public void testSaveResume() {
        //все верно
        Resume resume4 = new Resume();
        storage.save(resume4);
        try {
            storage.get(resume4.getUuid());
        } catch (NotExistStorageException e) {
            Assert.fail();
        }
        testAssertSize(4);
    }

    @Test(expected = ExistStorageException.class)
    //готово
    public void testSaveExistResume() {
        Resume resume4 = new Resume(resume1.getUuid());
        storage.save(resume4);
    }

    @Test(expected = NotExistStorageException.class)
    //готово
    public void testDeleteResumeTest() {
        storage.delete(resume2.getUuid());
        testAssertSize(2);
        storage.get(resume2.getUuid());
    }

    @Test(expected = NotExistStorageException.class)
    //готово
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
    public void testGetAll() {
        Resume[] expectedResumes = new Resume[]{resume1, resume2, resume3};
        Assert.assertArrayEquals(expectedResumes, storage.getAll());
    }

    @Test
    private void testAssertSize(int expectedSize) {
        assertEquals(expectedSize, storage.size());
    }
}