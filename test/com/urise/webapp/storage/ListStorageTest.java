package com.urise.webapp.storage;

public class ListStorageTest extends AbstractStorageTest {
    private Storage storage = new ListStorage();

    public ListStorageTest() {
        super(new ListStorage());
    }
}