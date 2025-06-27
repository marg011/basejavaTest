import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    Resume[] storage = new Resume[10000];
    int pointer = 0;

    void clear() {
        Arrays.fill(storage, null);
        pointer = 0;
    }

    void save(Resume r) {
        storage[pointer] = r;
        pointer++;
    }

    Resume get(String uuid) {
        return null;
    }

    void delete(String uuid) {
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    Resume[] getAll() {
        return new Resume[0];
    }

    int size() {

        return pointer;
    }
}
