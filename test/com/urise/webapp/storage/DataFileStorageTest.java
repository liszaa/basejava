package com.urise.webapp.storage;

import com.urise.webapp.storage.serializer.DataSerializer;

public class DataFileStorageTest extends AbstractStorageTest{

    public DataFileStorageTest() {
        super(new FileStorage(STORAGE_DIR, new DataSerializer()));
    }
}
