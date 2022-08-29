package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;
import java.util.Arrays;

public class ArrayStorage {
    protected static final int STORAGE_LIMIT = 10000;
    Resume[] storage = new Resume[STORAGE_LIMIT];
    private int size = 0;

    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    public void save(Resume newResume) {
        if (STORAGE_LIMIT <= size) {
            System.out.println("Ошибка! Хранилище переполнено!");
        } else if (!(findSearchKey(newResume.getUuid()) == -1)) {
            System.out.println("Ошибка! Резюме с uuid :" + newResume.getUuid() + " уже существует!");
        } else {
            storage[size] = newResume;
            size++;
        }
    }

    public void update(Resume updResume) {
        int key = findSearchKey(updResume.getUuid());
        if (key == -1){
            throwErrorResumeNotFound(updResume.getUuid());
        } else {
            storage[key] = updResume;
        }
    }

    public Resume get(String uuid) {
        int key = findSearchKey(uuid);
        if (key == -1) {
            throwErrorResumeNotFound(uuid);
            return null;
        } else {
            return storage[key];
        }

    }

    public void delete(String uuid) {
        int key = findSearchKey(uuid);
        if (key == -1) {
            throwErrorResumeNotFound(uuid);
        } else {
            System.arraycopy(storage, key + 1, storage, key, size - 1 - key);
            size--;
            storage[size] = null;
        }
    }

    public Resume[] getAll() {
        return Arrays.copyOf(storage, size);
    }

    public int size() {
        return size;
    }

    private int findSearchKey(String uuid) {
        for (int i = 0; i < size; i++) {
            if (storage[i].getUuid().equals(uuid)) {
                return i;
            }
        }
        return -1;
    }

    private void throwErrorResumeNotFound(String uuid) {
        System.out.println("Ошибка! Резюме c uuid : " + uuid + " не найдено!");
    }

}
