package com.urise.webapp.storage;

import com.urise.webapp.storage.serializer.DataSerializer;

public class DataPathStorageTest extends AbstractStorageTest{

    public DataPathStorageTest() {
        super(new PathStorage(STORAGE_DIR, new DataSerializer()));
    }
}
