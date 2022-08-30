package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

public class ArrayStorage extends AbstractArrayStorage {

    @Override
    protected void addElement(Resume resume, int index) {
        storage[size] = resume;
        size++;
    }
    @Override
    protected void removeElement(int index) {
        storage[index] = storage[size - 1];
        storage[size] = null;
        size--;
    }

    @Override
    protected int findSearchKey(String uuid) {
        for (int i = 0; i < size; i++) {
            if (storage[i].getUuid().equals(uuid)) {
                return i;
            }
        }
        return -1;
    }
}
