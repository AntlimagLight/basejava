package com.urise.webapp.storage;

import com.urise.webapp.exception.ExistStorageException;
import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.exception.StorageException;
import com.urise.webapp.model.Resume;

import java.util.Arrays;

public abstract class AbstractArrayStorage implements Storage {

    protected static final int STORAGE_LIMIT = 10000;
    protected final Resume[] storage = new Resume[STORAGE_LIMIT];
    protected int size = 0;

    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    public void update(Resume resume) {
        int key = findSearchKey(resume.getUuid());
        if (key < 0) {
            throw new NotExistStorageException(resume.getUuid());
        } else {
            storage[key] = resume;
        }
    }

    public void save(Resume resume) {
        int key = findSearchKey(resume.getUuid());
        if (STORAGE_LIMIT <= size) {
            throw new StorageException("Хранилище переполнено!", resume.getUuid());
        } else if (!(key < 0)) {
            throw new ExistStorageException(resume.getUuid());
        } else {
            addElement(resume, key);
            size++;
        }
    }

    public Resume get(String uuid) {
        int key = findSearchKey(uuid);
        if (key < 0) {
            throw new NotExistStorageException(uuid);
        } else {
            return storage[key];
        }
    }

    public void delete(String uuid) {
        int key = findSearchKey(uuid);
        if (key < 0) {
            throw new NotExistStorageException(uuid);
        } else {
            fillRemovedElement(key);
            storage[size] = null;
            size--;
        }
    }

    public Resume[] getAll() {
        return Arrays.copyOf(storage, size);
    }

    public int size() {
        return size;
    }

    protected abstract void addElement(Resume resume, int index);

    protected abstract void fillRemovedElement(int index);

    protected abstract int findSearchKey(String uuid);
}
