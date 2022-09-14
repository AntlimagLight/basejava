package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.ArrayList;
import java.util.List;

public class ListStorage extends AbstractStorage<Integer> {

    protected final List<Resume> storage = new ArrayList<>();

    public void clear() {
        storage.clear();
    }

    public int size() {
        return storage.size();
    }

    @Override
    public void doUpdate(Resume resume, Integer key) {
        storage.set(key, resume);
    }

    @Override
    public void doSave(Resume resume, Integer key) {
        storage.add(resume);
    }

    @Override
    public Resume doGet(Integer key) {
        return storage.get(key);
    }

    @Override
    public void doDelete(Integer key) {
        storage.remove((int) key);
    }

    @Override
    protected List<Resume> getCopyAll() {
        return new ArrayList<>(storage);
    }

    protected Integer findSearchKey(String uuid) {
        for (int i = 0; i < storage.size(); i++) {
            if (storage.get(i).getUuid().equals(uuid)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    protected boolean isExist(Integer key) {
        return key >= 0;
    }
}
