package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.ArrayList;
import java.util.List;

public class ListStorage extends AbstractStorage {

    protected final List<Resume> storage = new ArrayList<>();

    public void clear() {
        storage.clear();
    }

    public int size() {
        return storage.size();
    }

    @Override
    public void doUpdate(Resume resume, Object key) {
        storage.set((int) key, resume);
    }

    @Override
    public void doSave(Resume resume, Object key) {
        storage.add(resume);
    }

    @Override
    public Resume doGet(Object key) {
        return storage.get((int) key);
    }

    @Override
    public void doDelete(Object key) {
        storage.remove((int) key);
    }

    @Override
    protected List<Resume> getCopyAll() {
        return new ArrayList<>(storage);
    }

    protected Object findSearchKey(String uuid) {
        for (int i = 0; i < storage.size(); i++) {
            if (storage.get(i).getUuid().equals(uuid)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    protected boolean isExist(Object key) {
        return (int) key >= 0;
    }
}
