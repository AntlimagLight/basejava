package com.urise.webapp.storage;

public class MapResumeKeyStorageTest extends AbstractStorageTest {
    private Storage storage = new MapStorage();

    public MapResumeKeyStorageTest() {
        super(new MapResumeKeyStorage());
    }
}