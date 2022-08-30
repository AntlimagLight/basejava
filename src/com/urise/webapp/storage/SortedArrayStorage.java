package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;

public class SortedArrayStorage extends AbstractArrayStorage {

    @Override
    protected void addElement(Resume resume, int index) {
        int newElementIdx = Math.abs(index) - 1;
        System.arraycopy(storage,newElementIdx,storage,newElementIdx + 1,size - newElementIdx);
        storage[newElementIdx] = resume;
        size++;
    }

    @Override
    protected void removeElement(int index) {
        int numMoved = size - index - 1;
        if (numMoved > 0) {
            System.arraycopy(storage, index + 1, storage, index, numMoved);
        }
        storage[size] = null;
        size--;
    }

    @Override
    protected int findSearchKey(String uuid) {
        Resume searchKey = new Resume();
        searchKey.setUuid(uuid);
        return Arrays.binarySearch(storage,0, size, searchKey);
    }
}

