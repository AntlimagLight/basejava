package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.*;

public class MapStorage extends AbstractStorage<String> {

    protected final Map<String, Resume> storage = new HashMap<>();

    public void clear() {
        storage.clear();
    }

    public int size() {
        return storage.size();
    }

    @Override
    protected void doUpdate(Resume resume, String key) {
        storage.replace(key, resume);
    }

    @Override
    protected void doSave(Resume resume, String key) {
        storage.put(key, resume);
    }

    @Override
    protected Resume doGet(String key) {
        return storage.get(key);
    }

    @Override
    protected void doDelete(String key) {
        storage.remove(key);
    }

    @Override
    protected List<Resume> getCopyAll() {
        return new ArrayList<>(storage.values());
    }

    @Override
    protected String findSearchKey(String uuid) {
        return uuid;
    }

    @Override
    protected boolean isExist(String searchKey) {
        return storage.containsKey(searchKey);
    }
}
