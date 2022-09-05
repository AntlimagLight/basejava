package com.urise.webapp.storage;

import com.urise.webapp.exception.StorageException;
import com.urise.webapp.model.Resume;

import java.util.Arrays;

public abstract class AbstractArrayStorage extends AbstractStorage {

    protected static final int STORAGE_LIMIT = 10000;
    protected final Resume[] storage = new Resume[STORAGE_LIMIT];
    protected int size = 0;

    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    @Override
    public void doUpdate(Resume resume, int key) {
        storage[key] = resume;
    }

    @Override
    public void doSave(Resume resume, int key) {
        if (STORAGE_LIMIT <= size) {
            throw new StorageException("Хранилище переполнено!", resume.getUuid());
        } else {
            addElement(resume, key);
            size++;
        }
    }

    @Override
    public Resume doGet(int key) {
        return storage[key];
    }

    @Override
    public void doDelete(int key) {
        fillRemovedElement(key);
        storage[size] = null;
        size--;
    }

    public Resume[] getAll() {
        return Arrays.copyOf(storage, size);
    }

    public int size() {
        return size;
    }

    protected abstract void addElement(Resume resume, int index);

    protected abstract void fillRemovedElement(int index);

}
