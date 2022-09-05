package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.ArrayList;
import java.util.List;

public class ListStorage extends AbstractStorage {

    List<Resume> storage = new ArrayList<>();

    public void clear() {
        storage.clear();
    }

    @Override
    public void doUpdate(Resume resume, int key) {
        storage.set(key, resume);
    }

    @Override
    public void doSave(Resume resume, int key) {
        storage.add(resume);
    }

    @Override
    public Resume doGet(int key) {
        return storage.get(key);
    }

    @Override
    public void doDelete(int key) {
        storage.remove(key);
    }

    public Resume[] getAll() {
        Resume[] outputArray = new Resume[storage.size()];
        return storage.toArray(outputArray);
    }

    public int size() {
        return storage.size();
    }

    protected int findSearchKey(String uuid) {
        for (int i = 0; i < storage.size(); i++) {
            if (storage.get(i).getUuid().equals(uuid)) {
                return i;
            }
        }
        return -1;
    }
}
