package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;

public class SortedArrayStorage extends AbstractArrayStorage {

    @Override
    protected void addElement(Resume resume, int index) {
        int insertionIndex = Math.abs(index) - 1;
        System.arraycopy(storage, insertionIndex, storage, insertionIndex + 1, size - insertionIndex);
        storage[insertionIndex] = resume;
    }

    @Override
    protected void fillRemovedElement(int index) {
        int numMoved = size - index - 1;
        System.arraycopy(storage, index + 1, storage, index, numMoved);
    }

    @Override
    protected int findSearchKey(String uuid) {
        Resume searchKey = new Resume();
        searchKey.setUuid(uuid);
        return Arrays.binarySearch(storage, 0, size, searchKey);
    }
}

