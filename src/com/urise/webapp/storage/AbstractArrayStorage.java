package com.urise.webapp.storage;

import com.urise.webapp.exception.StorageException;
import com.urise.webapp.model.Resume;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public abstract class AbstractArrayStorage extends AbstractStorage<Integer> {

    protected static final int STORAGE_LIMIT = 10000;
    protected final Resume[] storage = new Resume[STORAGE_LIMIT];
    protected int size = 0;

    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    public int size() {
        return size;
    }

    @Override
    public void doUpdate(Resume resume, Integer key) {
        storage[key] = resume;
    }

    @Override
    public void doSave(Resume resume, Integer key) {
        if (STORAGE_LIMIT <= size) {
            throw new StorageException("Хранилище переполнено!", resume.getUuid());
        } else {
            addElement(resume, key);
            size++;
        }
    }

    @Override
    public Resume doGet(Integer key) {
        return storage[key];
    }

    @Override
    public void doDelete(Integer key) {
        fillRemovedElement(key);
        storage[size] = null;
        size--;
    }

    @Override
    protected boolean isExist(Integer key) {
        return key >= 0;
    }

    @Override
    protected List<Resume> getCopyAll() {
        List<Resume> resumes = new ArrayList<>();
        Collections.addAll(resumes, Arrays.copyOf(storage, size));
        return resumes;
    }

    protected abstract void addElement(Resume resume, int index);

    protected abstract void fillRemovedElement(int index);

}
