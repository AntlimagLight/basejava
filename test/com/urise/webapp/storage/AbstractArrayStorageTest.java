package com.urise.webapp.storage;

import com.urise.webapp.exception.ExistStorageException;
import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.exception.StorageException;
import com.urise.webapp.model.Resume;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public abstract class AbstractArrayStorageTest {
    private Storage storage;

    private static final String UUID_1 = "uuid1";
    private static final String UUID_2 = "uuid2";
    private static final String UUID_3 = "uuid3";
    private static final String UUID_4 = "uuid4";
    private static final String UUID_NOT_EXIST = "dummy";

    private static final Resume RESUME1 = new Resume(UUID_1);
    private static final Resume RESUME2 = new Resume(UUID_2);
    private static final Resume RESUME3 = new Resume(UUID_3);
    private static final Resume RESUME4 = new Resume(UUID_4);


    protected AbstractArrayStorageTest(Storage storage) {
        this.storage = storage;
    }

    @Before
    public void setUp() {
        storage.clear();
        storage.save(new Resume(UUID_1));
        storage.save(new Resume(UUID_2));
        storage.save(new Resume(UUID_3));
    }

    @Test
    public void clear() {
        storage.clear();
        assertSize(0);
        Resume[] expected = new Resume[0];
        Assert.assertEquals(expected, storage.getAll());
    }

    @Test
    public void update() {
        Resume checkingResume = new Resume(UUID_3);
        storage.update(checkingResume);
        Assert.assertSame(checkingResume, storage.get(UUID_3));
    }

    @Test(expected = NotExistStorageException.class)
    public void updateNotExist() {
        Resume checkingResume = new Resume(UUID_NOT_EXIST);
        storage.update(checkingResume);
    }

    @Test
    public void save() {
        storage.save(RESUME4);
        assertGet(RESUME4);
        assertSize(4);
    }

    @Test(expected = ExistStorageException.class)
    public void saveExist() {
        storage.save(RESUME1);
    }

    @Test(expected = StorageException.class)
    public void saveOverflow() {
        storage.clear();
        try {
            for (int i = 0; i < AbstractArrayStorage.STORAGE_LIMIT; i++) {
                storage.save(new Resume());
            }
        } catch (StorageException e) {
            Assert.fail("Переполнение массива произошло преждевременно!");
        }
        storage.save(new Resume());
    }

    @Test
    public void get() {
        assertGet(RESUME1);
        assertGet(RESUME2);
        assertGet(RESUME3);
    }

    @Test(expected = NotExistStorageException.class)
    public void getNotExist() {
        storage.get(UUID_NOT_EXIST);
    }

    @Test(expected = NotExistStorageException.class)
    public void delete() {
        storage.delete(UUID_2);
        storage.get(UUID_2);
        assertSize(2);
    }

    @Test(expected = NotExistStorageException.class)
    public void deleteNotExist() {
        storage.delete(UUID_NOT_EXIST);
    }

    @Test
    public void getAll() {
        Resume[] expected = {RESUME1, RESUME2, RESUME3};
        Assert.assertEquals(expected, storage.getAll());
        assertSize(expected.length);
    }

    @Test
    public void size() {
        assertSize(3);
    }

    protected void assertSize (int expectedSize) {
        Assert.assertEquals(expectedSize, storage.size());
    }

    protected void assertGet (Resume expectedResume) {
        Assert.assertEquals(expectedResume, storage.get(expectedResume.getUuid()));
    }
}