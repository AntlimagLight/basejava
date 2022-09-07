package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class MapStorage extends AbstractStorage{

    protected final Map<String, Resume> storage = new HashMap<>();

    public void clear() {
        storage.clear();
    }

    @Override
    protected void doUpdate(Resume resume, Object key) {
        storage.replace((String) key, resume);
    }

    @Override
    protected void doSave(Resume resume, Object key) {
        storage.put((String) key, resume);
    }

    @Override
    protected Resume doGet(Object key) {
        return storage.get(key);
    }

    @Override
    protected void doDelete(Object key) {
        storage.remove(key);
    }

    public Resume[] getAll() {
        Resume[] outputArray = storage.values().toArray(new Resume[0]);
        Arrays.sort(outputArray);
        return outputArray;
    }

    public int size() {
        return storage.size();
    }

    @Override
    protected String findSearchKey(String uuid) {
        return uuid;
    }

    @Override
    protected boolean isExist(Object searchKey) {
        return storage.containsKey(searchKey);
    }
}
