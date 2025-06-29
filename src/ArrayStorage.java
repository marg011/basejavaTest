import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    Resume[] storage = new Resume[10000];
    int arraySize = 0;

    void clear() {
        Arrays.fill(storage, 0, arraySize, null);
        arraySize = 0;
    }

    void save(Resume r) {
        storage[arraySize] = r;
        arraySize++;
    }

    Resume get(String uuid) {
        if (arraySize > 0) {
            for (int i = 0; i < arraySize; i++) {
                if (storage[i].getUuid().equals(uuid)) {
                    return storage[i];
                }
            }
        }
        return null;
    }

    void delete(String uuid) {
        if (arraySize > 0) {
            for (int i = 0; i < arraySize; i++) {
                if (storage[i].getUuid().equals(uuid)) {
                    System.arraycopy(storage, i + 1, storage, i, arraySize - i);
                    arraySize--;
                    break;
                }
            }
        }
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    Resume[] getAll() {
        return Arrays.copyOf(storage, arraySize);
    }

    int size() {
        return arraySize;
    }
}
