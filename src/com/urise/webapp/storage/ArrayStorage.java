package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */

public class ArrayStorage {
    private Resume[] storage = new Resume[10_000];

    //do not forget to change this variable in new methods that change amount of real resumes!
    private int amountOfResumes = 0;
    private final static int RESUME_NOT_FOUND = -1;

    public void clear() {
        Arrays.fill(storage, 0, amountOfResumes, null);
        amountOfResumes = 0;
    }

    public void save(Resume resume) {
        if (amountOfResumes >= storage.length) {
            System.out.println("Method save: Резюме(" + resume.getUuid() + ") не может быть добавлено в список (нет места в storage)\n");
        } else if (findResumeIndex(resume.getUuid()) != RESUME_NOT_FOUND) {
            System.out.println("Method save: Резюме(" + resume.getUuid() + ") не может быть добавлено в список (резюме уже есть в списке\n");
        } else {
            storage[amountOfResumes] = resume;
            amountOfResumes++;
        }
    }

    public void update(Resume resume) {
        int index = findResumeIndex(resume.getUuid());
        if (index != RESUME_NOT_FOUND) {
            storage[index] = resume;
        } else {
            System.out.println("Method update: " + "Резюме(" + resume.getUuid() + ") отсутствует в списке!\n");
        }
    }

    public Resume get(String uuid) {
        int index = findResumeIndex(uuid);
        if (index != RESUME_NOT_FOUND) {
            return storage[index];
        }
        System.out.println("Method get: " + "Резюме(" + uuid + ") отсутствует в списке!\n");
        return null;
    }

    public void delete(String uuid) {
        int index = findResumeIndex(uuid);
        if (index != RESUME_NOT_FOUND) {
            System.arraycopy(storage, index + 1, storage, index, amountOfResumes - index);
            amountOfResumes--;
        } else {
            System.out.println("Method delete:  + Резюме(" + uuid + ") отсутствует в списке!\n");
        }
    }

    /**
     * @return array, findResume only Resumes in storage (without null)
     */
    public Resume[] getAll() {
        if (amountOfResumes != 0) {
            return Arrays.copyOfRange(storage, 0, amountOfResumes - 1);
        }
        System.out.println("Method getAll: Массив пуст!\n");
        return null;

    }

    public int size() {
        return amountOfResumes;
    }

    private int findResumeIndex(String uuid) {
        for (int i = 0; i < size(); i++) {
            if ((storage[i].getUuid().equals(uuid))) {
                return i;
            }
        }
        return RESUME_NOT_FOUND;
    }
}