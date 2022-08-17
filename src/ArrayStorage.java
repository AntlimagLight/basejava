import java.util.Arrays;

public class ArrayStorage {
    Resume[] storage = new Resume[10000];

    void clear() {
        Arrays.fill(storage, null);
    }

    void save(Resume newResume) {
        storage[findFirstEmptyIdx()] = newResume;
    }

    Resume get(String uuid) {
        for (int i = 0; i < findFirstEmptyIdx(); i++) {
            if (storage[i].uuid.equals(uuid)) {
                return storage[i];
            }
        }
        System.out.println("Запрошенное резюме не найдено");
        return null;
    }

    void delete(String uuid) {
        for (int i = 0; i < findFirstEmptyIdx() - 1; i++) {
            if (storage[i].uuid.equals(uuid)) {
                for (int j = i; j <= findFirstEmptyIdx(); j++) {
                    if ((j + 1) > storage.length) {
                        storage[j] = null;
                        break;
                    }
                    storage[j] = storage[j + 1];
                }
            }
        }

    }

    Resume[] getAll() {
        return Arrays.copyOf(storage, findFirstEmptyIdx() >= 1 ? findFirstEmptyIdx() - 1 : findFirstEmptyIdx());
    }

    int size() {
        return findFirstEmptyIdx();
    }

    private int findFirstEmptyIdx() {
        int count = 0;
        for (Resume resume : storage) {
            if (resume == null) {
                return count;
            } else {
                count++;
            }
        }
        // Свободного индекса нет весь массив заполнен, вот такой вот пока сигнал к этому.
        return storage.length + 1;
    }
}
