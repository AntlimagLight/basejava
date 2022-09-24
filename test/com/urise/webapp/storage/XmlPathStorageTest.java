package com.urise.webapp.storage;

import com.urise.webapp.storage.serializestrategy.XmlStreamSerializer;

public class XmlPathStorageTest extends AbstractStorageTest {

    public XmlPathStorageTest() {
        super(new PathStorage(STORAGE_DIR.getName(), new XmlStreamSerializer()));
    }
}