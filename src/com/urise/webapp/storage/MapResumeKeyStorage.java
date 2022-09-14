package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MapResumeKeyStorage extends AbstractStorage<Resume> {

    protected final Map<String, Resume> storage = new HashMap<>();

    public void clear() {
        storage.clear();
    }

    public int size() {
        return storage.size();
    }

    @Override
    protected void doUpdate(Resume resume, Resume key) {
        storage.replace(key.getUuid(), resume);
    }

    @Override
    protected void doSave(Resume resume, Resume key) {
        storage.put(resume.getUuid(), resume);
    }

    @Override
    protected Resume doGet(Resume key) {
        return key;
    }

    @Override
    protected void doDelete(Resume key) {
        storage.remove(key.getUuid());
    }

    @Override
    protected List<Resume> getCopyAll() {
        return new ArrayList<>(storage.values());
    }

    @Override
    protected Resume findSearchKey(String uuid) {
        return storage.get(uuid);
    }

    @Override
    protected boolean isExist(Resume searchKey) {
        return searchKey != null;
    }
}
