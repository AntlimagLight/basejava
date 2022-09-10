package com.urise.webapp.storage;

import com.urise.webapp.exception.ExistStorageException;
import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.model.Resume;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

public abstract class AbstractStorageTest {
    private Storage storage;

    private static final String UUID_1 = "uuid1";
    private static final String UUID_2 = "uuid2";
    private static final String UUID_3 = "uuid3";
    private static final String UUID_4 = "uuid4";
    private static final String UUID_NOT_EXIST = "dummy";

    private static final String NAME1 = "Evan Chaplin";
    private static final String NAME2 = "Alise Marlow";
    private static final String NAME3 = "Igor Raven";
    private static final String NAME4 = "Sam Robinson";

    private static final Resume RESUME1 = new Resume(UUID_1, NAME1);
    private static final Resume RESUME2 = new Resume(UUID_2, NAME2);
    private static final Resume RESUME3 = new Resume(UUID_3, NAME3);
    private static final Resume RESUME4 = new Resume(UUID_4, NAME4);

    protected AbstractStorageTest(Storage storage) {
        this.storage = storage;
    }

    @Before
    public void setUp() {
        storage.clear();
        storage.save(new Resume(UUID_1, NAME1));
        storage.save(new Resume(UUID_2, NAME2));
        storage.save(new Resume(UUID_3, NAME3));
    }

    @Test
    public void clear() {
        storage.clear();
        assertSize(0);
        Resume[] expected = new Resume[0];
        Assert.assertArrayEquals(expected, storage.getAllSorted().toArray());
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
    public void getAllSorted() {
        List<Resume> list = storage.getAllSorted();
        Assert.assertEquals(3, list.size());
        Assert.assertEquals(list, Arrays.asList(RESUME2, RESUME1, RESUME3));
    }

    @Test
    public void size() {
        assertSize(3);
    }

    protected void assertSize(int expectedSize) {
        Assert.assertEquals(expectedSize, storage.size());
    }

    protected void assertGet(Resume expectedResume) {
        Assert.assertEquals(expectedResume, storage.get(expectedResume.getUuid()));
    }
}