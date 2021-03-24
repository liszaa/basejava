package com.urise.webapp.storage;

import static org.junit.Assert.*;

public class ObjectStreamFileStorageTest extends AbstractStorageTest {

    public ObjectStreamFileStorageTest() {
        super(new ObjectStreamFileStorage(STORAGE_DIR, new ObjectSerializier()));
    }

}