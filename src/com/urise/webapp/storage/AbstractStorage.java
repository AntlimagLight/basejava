package com.urise.webapp.storage;


import com.urise.webapp.exception.ExistStorageException;
import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.model.Resume;

import java.util.*;

public abstract class AbstractStorage implements Storage {

    protected static final Comparator<Resume> RESUME_COMPARATOR = (r1, r2) -> {
        int compare = r1.getFullName().compareTo(r2.getFullName());
        return compare == 0 ? r1.getUuid().compareTo(r2.getUuid()): compare;
    };

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

    public List<Resume> getAllSorted() {
        List<Resume> resumes = getCopyAll();
        resumes.sort(RESUME_COMPARATOR);
        return resumes;
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

    protected abstract List<Resume> getCopyAll();
    protected abstract void doUpdate(Resume resume, Object key);

    protected abstract void doSave(Resume resume, Object key);

    protected abstract Resume doGet(Object key);

    protected abstract void doDelete(Object key);

    protected abstract Object findSearchKey(String uuid);

    protected abstract boolean isExist(Object searchKey);

}
