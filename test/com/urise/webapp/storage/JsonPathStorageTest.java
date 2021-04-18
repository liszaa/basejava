package com.urise.webapp.storage;

import com.urise.webapp.storage.serializer.JsonSerializer;

public class JsonPathStorageTest extends AbstractStorageTest{

    public JsonPathStorageTest() {
        super(new PathStorage(STORAGE_DIR, new JsonSerializer()));
    }
}
