package com.urise.webapp.storage;

import com.urise.webapp.Exception.StorageException;
import org.junit.Ignore;
import org.junit.Test;

public class ListStorageTest extends AbstractStorageTest{

    public ListStorageTest() {
        super(new ListStorage());
    }

    @Test
    @Override
    public void testStorageOverflow() {}
}