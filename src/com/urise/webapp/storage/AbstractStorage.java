package com.urise.webapp.storage;


import com.urise.webapp.exception.ExistStorageException;
import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.model.Resume;

public abstract class AbstractStorage implements Storage {

    public void update(Resume resume) {
        int key = findSearchKey(resume.getUuid());
        if (key < 0) {
            throw new NotExistStorageException(resume.getUuid());
        } else {
            doUpdate(resume, key);
        }
    }

    public void save(Resume resume) {
        int key = findSearchKey(resume.getUuid());
        if (!(key < 0)) {
            throw new ExistStorageException(resume.getUuid());
        } else {
            doSave(resume, key);
        }
    }

    public Resume get(String uuid) {
        int key = findSearchKey(uuid);
        if (key < 0) {
            throw new NotExistStorageException(uuid);
        } else {
            return doGet(key);
        }
    }

    public void delete(String uuid) {
        int key = findSearchKey(uuid);
        if (key < 0) {
            throw new NotExistStorageException(uuid);
        } else {
            doDelete(key);
        }
    }

    protected abstract void doUpdate(Resume resume, int key);

    protected abstract void doSave(Resume resume, int key);

    protected abstract Resume doGet(int key);

    protected abstract void doDelete(int key);

    protected abstract int findSearchKey(String uuid);

}
