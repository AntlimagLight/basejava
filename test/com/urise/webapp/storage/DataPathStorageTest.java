package com.urise.webapp.storage;

import com.urise.webapp.storage.serializestrategy.DataStreamSerializer;

public class DataPathStorageTest extends AbstractStorageTest {

    public DataPathStorageTest() {
        super(new PathStorage(STORAGE_DIR.getName(), new DataStreamSerializer()));
    }
}