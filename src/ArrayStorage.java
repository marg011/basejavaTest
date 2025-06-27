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
        if (pointer > 0) {
            Resume resume = new Resume();
            resume.setUuid(uuid);
            int foundIndex = Arrays.binarySearch(storage, 0, pointer - 1, resume);
            if (foundIndex >= 0) {
                return storage[foundIndex];
            }
            return null;
        }
        return null;
    }

    void delete(String uuid) {
        if (pointer > 0) {
            Resume resume = new Resume();
            resume.setUuid(uuid);
            int foundIndex = Arrays.binarySearch(storage, 0, pointer - 1, resume);
            if (foundIndex >= 0) {
                System.arraycopy(storage, foundIndex + 1, storage, foundIndex, pointer - foundIndex - 1);
                pointer--;
            }
        }

    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    Resume[] getAll() {
        if (pointer > 0) {
            return Arrays.copyOf(storage, pointer);
        }
        return new Resume[0];
    }

    int size() {

        return pointer;
    }
}
