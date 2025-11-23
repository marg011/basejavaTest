package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.util.Arrays;
import java.util.stream.IntStream;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    Resume[] storage = new Resume[10000];
    int size = 0;

    private int findResumeIndex(String uuid) {
        return IntStream.range(0, size).filter(index -> storage[index].getUuid().equals(uuid))
                .findFirst()
                .orElse(-1);
    }

    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    public void update(Resume r) {
        int index = findResumeIndex(r.getUuid());
        if (index != -1) {
            storage[index] = r;
        } else {
            System.out.println("No resume with id " + r.getUuid() + " in storage");
        }
    }

    public void save(Resume r) {
        if (Arrays.stream(Arrays.copyOfRange(storage, 0, size))
                .noneMatch(resume -> resume.getUuid().equals(r.getUuid()) && size <= storage.length)) {
            storage[size++] = r;
        } else {
            System.out.println("The resume " + r.getUuid() + " is already in storage or storage is full");
        }
    }

    public Resume get(String uuid) {
        int index = findResumeIndex(uuid);
        if (index != -1) {
            return storage[index];
        }
        System.out.println("No resume with id " + uuid + " in storage");
        return null;
    }

    public void delete(String uuid) {
        int index = findResumeIndex(uuid);
        if (index != -1) {
            if (index != size - 1) {
                storage[index] = storage[size - 1];
                storage[size - 1] = null;
                size--;
            } else {
                storage[index] = null;
            }
        } else {
            System.out.println("No resume with id " + uuid + " storage");
        }
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
}
