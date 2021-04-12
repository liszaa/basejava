package com.urise.webapp.storage;

import com.urise.webapp.storage.serializer.ObjectSerializer;

public class ObjectPathStorageTest extends AbstractStorageTest{

    public ObjectPathStorageTest() {
        super(new PathStorage(STORAGE_DIR, new ObjectSerializer()));
    }
}