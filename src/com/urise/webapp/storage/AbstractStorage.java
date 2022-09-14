package com.urise.webapp.storage;


import com.urise.webapp.exception.ExistStorageException;
import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.model.Resume;

import java.util.*;
import java.util.logging.Logger;

public abstract class AbstractStorage<SK> implements Storage {
    private static final Logger LOG = Logger.getLogger(AbstractStorage.class.getName());

    protected static final Comparator<Resume> RESUME_COMPARATOR =
            Comparator.comparing(Resume::getFullName).thenComparing(Resume::getUuid);

    public void update(Resume resume) {
        LOG.info("Update " + resume);
        doUpdate(resume, getExistingSearchKey(resume.getUuid()));
    }

    public void save(Resume resume) {
        LOG.info("Save " + resume);
        doSave(resume, getNotExistingSearchKey(resume.getUuid()));
    }

    public Resume get(String uuid) {
        LOG.info("Get " + uuid);
        return doGet(getExistingSearchKey(uuid));
    }

    public void delete(String uuid) {
        LOG.info("Delete " + uuid);
        doDelete(getExistingSearchKey(uuid));
    }

    public List<Resume> getAllSorted() {
        LOG.info("Get All Sorted");
        List<Resume> resumes = getCopyAll();
        resumes.sort(RESUME_COMPARATOR);

        return resumes;
    }

    protected SK getExistingSearchKey(String uuid) {
        SK key = findSearchKey(uuid);
        if (!(isExist(key))) {
            LOG.warning("Резюме c uuid : " + uuid + " не найдено!");
            throw new NotExistStorageException(uuid);
        } else {
            return key;
        }
    }

    protected SK getNotExistingSearchKey(String uuid) {
        SK key = findSearchKey(uuid);
        if ((isExist(key))) {
            LOG.warning("Резюме c uuid : " + uuid + " уже существует в хранилище!");
            throw new ExistStorageException(uuid);
        } else {
            return key;
        }
    }

    protected abstract List<Resume> getCopyAll();

    protected abstract void doUpdate(Resume resume, SK key);

    protected abstract void doSave(Resume resume, SK key);

    protected abstract Resume doGet(SK key);

    protected abstract void doDelete(SK key);

    protected abstract SK findSearchKey(String uuid);

    protected abstract boolean isExist(SK searchKey);

}
