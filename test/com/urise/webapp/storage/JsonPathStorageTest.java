package com.urise.webapp.storage;

import com.urise.webapp.storage.serializestrategy.JsonStreamSerializer;

public class JsonPathStorageTest extends AbstractStorageTest {

    public JsonPathStorageTest() {
        super(new PathStorage(STORAGE_DIR.getName(), new JsonStreamSerializer()));
    }
}