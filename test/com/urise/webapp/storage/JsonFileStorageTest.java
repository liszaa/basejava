package com.urise.webapp.storage;

import com.urise.webapp.storage.serializer.JsonSerializer;

public class JsonFileStorageTest extends AbstractStorageTest{

    public JsonFileStorageTest() {
        super(new FileStorage(STORAGE_DIR, new JsonSerializer()));
    }
}
