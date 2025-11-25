package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.util.Arrays;
import java.util.stream.IntStream;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    private final int STORAGE_LIMIT = 10000;
    private final Resume[] storage = new Resume[STORAGE_LIMIT];
    private int size = 0;

    public void update(Resume r) {
        int index = findIndex(r.getUuid());
        if (index != -1) {
            storage[index] = r;
        } else {
            System.out.println("No resume with id = " + r.getUuid() + " in storage");
        }
    }

    public void save(Resume r) {
        if (isPresent(r) && size <= storage.length) {
            storage[size++] = r;
        } else {
            System.out.println("The resume with id = " + r.getUuid() + " is already in storage or storage is full");
        }
    }

    public Resume get(String uuid) {
        int index = findIndex(uuid);
        if (index != -1) {
            return storage[index];
        }
        System.out.println("No resume with id = " + uuid + " in storage");
        return null;
    }

    public void delete(String uuid) {
        int index = findIndex(uuid);
        if (index != -1) {
            size--;
            storage[index] = storage[size];
            storage[size] = null;
        } else {
            System.out.println("No resume with id = " + uuid + " in storage");
        }
    }

    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    public Resume[] getAll() {
        return Arrays.copyOf(storage, size);
    }

    public int size() {
        return size;
    }

    private int findIndex(String uuid) {
        return IntStream.range(0, size).filter(index -> storage[index].getUuid().equals(uuid))
                .findFirst()
                .orElse(-1);
    }

    private boolean isPresent(Resume r) {
        return Arrays.stream(Arrays.copyOfRange(storage, 0, size))
                .noneMatch(resume -> resume.getUuid().equals(r.getUuid()));
    }
}
