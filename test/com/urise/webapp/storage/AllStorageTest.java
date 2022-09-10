package com.urise.webapp.storage;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        ListStorageTest.class,
        MapStorageTest.class,
        ArrayStorageTest.class,
        SortedArrayStorageTest.class,
        MapResumeKeyStorageTest.class})
public class AllStorageTest {
}
