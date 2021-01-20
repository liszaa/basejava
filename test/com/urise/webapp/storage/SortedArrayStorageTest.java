package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

public class SortedArrayStorageTest extends AbstractArrayStorageTest {

    public SortedArrayStorageTest() {
        super(new SortedArrayStorage());
    }

    @Test
    @Override
    public void testGetAll() {
        Resume[] expected = new Resume[]{resume1, resume2, resume3};
        Arrays.sort(expected);
        Assert.assertArrayEquals(expected, storage.getAll());
    }
}