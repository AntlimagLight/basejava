package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;

public abstract class AbstractArrayStorage implements Storage {

    protected static final int STORAGE_LIMIT = 10000;
    protected final Resume[] storage = new Resume[STORAGE_LIMIT];
    protected int size = 0;

    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    public void update(Resume updResume) {
        int key = findSearchKey(updResume.getUuid());
        if (key < 0){
            throwErrorResumeNotFound(updResume.getUuid());
        } else {
            storage[key] = updResume;
        }
    }

    public void save(Resume newResume) {
        int key = findSearchKey(newResume.getUuid());
        if (STORAGE_LIMIT <= size) {
            System.out.println("Ошибка! Хранилище переполнено!");
        } else if (!(key < 0)) {
            System.out.println("Ошибка! Резюме с uuid :" + newResume.getUuid() + " уже существует!");
        } else {
            addElement(newResume, key);
        }
    }

    protected abstract void addElement(Resume resume, int index);

    public Resume get(String uuid) {
        int key = findSearchKey(uuid);
        if (key < 0) {
            throwErrorResumeNotFound(uuid);
            return null;
        } else {
            return storage[key];
        }
    }

    public void delete(String uuid) {
        int key = findSearchKey(uuid);
        if (key < 0) {
            throwErrorResumeNotFound(uuid);
        } else {
            removeElement(key);
        }
    }

    protected abstract void removeElement(int index);

    public Resume[] getAll() {
        return Arrays.copyOf(storage, size);
    }

    public int size() {
        return size;
    }

    protected abstract int findSearchKey(String uuid);

    protected void throwErrorResumeNotFound(String uuid) {
        // Метод смиренно ждет Эксепшенов, чтобы умереть спокойно
        System.out.println("Ошибка! Резюме c uuid : " + uuid + " не найдено!");
    }
}
