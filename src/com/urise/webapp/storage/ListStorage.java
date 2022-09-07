package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.ArrayList;
import java.util.List;

public class ListStorage extends AbstractStorage {

    protected final List<Resume> storage = new ArrayList<>();

    public void clear() {
        storage.clear();
    }

    @Override
    public void doUpdate(Resume resume, Object key) {
        storage.set((Integer) key, resume);
    }

    @Override
    public void doSave(Resume resume, Object key) {
        storage.add(resume);
    }

    @Override
    public Resume doGet(Object key) {
        return storage.get((Integer) key);
    }

    @Override
    public void doDelete(Object key) {
        int tmpKey = (Integer) key;
        storage.remove(tmpKey);
    }

    public Resume[] getAll() {
        Resume[] outputArray = new Resume[storage.size()];
        return storage.toArray(outputArray);
    }

    public int size() {
        return storage.size();
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
        return (Integer) key >= 0;
    }
}
