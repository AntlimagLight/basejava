package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;
import java.util.Arrays;

public class ArrayStorage {
    Resume[] storage = new Resume[3];
    private int size = 0;

    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    public void save(Resume newResume) {
        if (storage.length - 1 >= size) {
            if (!(checkAvailability(newResume.getUuid()))) {
                storage[size] = newResume;
                size++;
            } else {
                System.out.println("Ошибка! Резюме с uuid :" + newResume.getUuid() + " уже существует!");
            }
        } else {
            System.out.println("Ошибка! Хранилище переполнено!");
        }
    }

    public void update(Resume updResume) {
        if ((checkAvailability(updResume.getUuid()))) {
            for (int i = 0; i < size; i++) {
                if (storage[i].getUuid().equals(updResume.getUuid())) {
                    storage[i] = updResume;
                }
            }
        } else {
            throwErrorResumeNotFound(updResume.getUuid());
        }
    }

    public Resume get(String uuid) {
        if ((checkAvailability(uuid))) {
            for (int i = 0; i < size; i++) {
                if (storage[i].getUuid().equals(uuid)) {
                    return storage[i];
                }
            }
        } else {
            throwErrorResumeNotFound(uuid);
        }
        return null;
    }

    public void delete(String uuid) {
        if ((checkAvailability(uuid))) {
            for (int i = 0; i < size; i++) {
                if (storage[i].getUuid().equals(uuid)) {
                    if (size - 1 - i >= 0) System.arraycopy(storage, i + 1, storage, i, size - 1 - i);
                    size--;
                    storage[size] = null;
                }
            }
        } else {
            throwErrorResumeNotFound(uuid);
        }
    }

    public Resume[] getAll() {
        return Arrays.copyOf(storage, size);
    }

    public int size() {
        return size;
    }

    private boolean checkAvailability(String uuid) {
        for (int i = 0; i < size; i++) {
            if (storage[i].getUuid().equals(uuid)) {
                return true;
            }
        }
        return false;
    }

    private void throwErrorResumeNotFound(String uuid) {
        System.out.println("Ошибка! Резюме c uuid : " + uuid + " не найдено!");
    }

}
