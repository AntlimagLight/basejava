package com.urise.webapp.storage;


import com.urise.webapp.exception.ExistStorageException;
import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.model.Resume;

public abstract class AbstractStorage implements Storage {

    public void update(Resume resume) {
        doUpdate(resume, getExistingSearchKey(resume.getUuid()));
    }

    public void save(Resume resume) {
        doSave(resume, getNotExistingSearchKey(resume.getUuid()));
    }

    public Resume get(String uuid) {
        return doGet(getExistingSearchKey(uuid));
    }

    public void delete(String uuid) {
        doDelete(getExistingSearchKey(uuid));
    }

    protected Object getExistingSearchKey(String uuid) {
        Object key = findSearchKey(uuid);
        if (!(isExist(key))) {
            throw new NotExistStorageException(uuid);
        } else {
            return key;
        }
    }

    protected Object getNotExistingSearchKey(String uuid) {
        Object key = findSearchKey(uuid);
        if ((isExist(key))) {
            throw new ExistStorageException(uuid);
        } else {
            return key;
        }
    }

    protected abstract void doUpdate(Resume resume, Object key);

    protected abstract void doSave(Resume resume, Object key);

    protected abstract Resume doGet(Object key);

    protected abstract void doDelete(Object key);

    protected abstract Object findSearchKey(String uuid);

    protected abstract boolean isExist(Object searchKey);

}
