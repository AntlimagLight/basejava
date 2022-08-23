import java.util.Arrays;

public class ArrayStorage {
    Resume[] storage = new Resume[3];
    private int size = 0;

    void clear() {
        Arrays.fill(storage, 0, size , null);
        size = 0;
    }

    void save(Resume newResume) {
        storage[size] = newResume;
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
        for (int i = 0; i < size ; i++) {
            if (storage[i].uuid.equals(uuid)) {
                if (size - 1 - i >= 0) System.arraycopy(storage, i + 1, storage, i, size - 1 - i);
                size--;
                storage[size] = null;
            }
        }
    }

    Resume[] getAll() {
        return Arrays.copyOf(storage, size);
    }

    int size() {
        return size;
    }

}
