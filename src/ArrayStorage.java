import java.util.Arrays;

public class ArrayStorage {
    Resume[] storage = new Resume[10000];
    private int size = 0;

    void clear() {
        Arrays.fill(storage, 0, size - 1, null);
        size = 0;
    }

    void save(Resume newResume) {
        storage[findIndex()] = newResume;
        size++;
    }

    Resume get(String uuid) {
        for (int i = 0; i < size; i++) {
            if (storage[i].uuid.equals(uuid)) {
                return storage[i];
            }
        }
        System.out.println("Запрошенное резюме не найдено");
        return null;
    }

    void delete(String uuid) {
        for (int i = 0; i < size - 1 ; i++) {
            if (storage[i].uuid.equals(uuid)) {
                for (int j = i; j <= size - 1; j++) {
                    if ((j + 1) >= storage.length) {
                        storage[j] = null;
                        break;
                    }
                    storage[j] = storage[j + 1];
                }
                size--;
            }
        }
    }

    Resume[] getAll() {
        return Arrays.copyOf(storage, size);
    }

    int size() {
        return size;
    }

    private int findIndex() {
        for (int i = 0; i < storage.length; i++) {
            Resume resume = storage[i];
            if (resume == null) {
                return i;
            }
        }
        // Свободного индекса нет весь массив заполнен, вот такой вот пока сигнал к этому.
        return -1;
    }
}
