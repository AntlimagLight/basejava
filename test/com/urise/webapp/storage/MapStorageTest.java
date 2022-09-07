package com.urise.webapp.storage;

import static org.junit.Assert.*;

public class MapStorageTest extends AbstractStorageTest {
    private Storage storage = new MapStorage();

    public MapStorageTest() {
        super(new MapStorage());
    }
}