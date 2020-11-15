package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    Resume[] storage = new Resume[10000];

    //do not forget to change this variable in new methods that change amount of real resumes!
    private int amountOfResumes = 0;


    void clear(){

        Arrays.fill(storage, 0, amountOfResumes - 1, null);
        amountOfResumes = 0;
    }

    public boolean contains(String uuid){
        for (int i = 0; i < size(); i++) {
            if ((storage[i].getUuid().equals(uuid))) {
                return true;
            }
        }
        return false;
    }

    public void save(Resume r){


        if ((contains(r.getUuid())) || amountOfResumes >= 10000) {
            System.out.println("Method save: Резюме(" + r.getUuid() + ") не может быть добавлено в список (уже есть или нет места)\n");
        } else {
            storage[amountOfResumes] = r;
            amountOfResumes++;
        }

    }

    public void update(Resume r){

        if (contains(r.getUuid())) {
            for (int i = 0; i < size(); i++) {
                if (r == storage[i]) {
                    storage[i] = r;
                }
            }
        } else {
            System.out.println("Method update: " + "Резюме(" + r.getUuid() + ") отсутствует в списке!\n");
        }
    }

    public Resume get(String uuid){

        if (contains(uuid)) {
            for (int i = 0; i < amountOfResumes; i++) {
                if (storage[i].getUuid().equals(uuid)) {
                    return storage[i];
                }
            }
        }
        System.out.println("Method get: " + "Резюме(" + uuid + ") отсутствует в списке!\n");
        return null;
    }


    public void delete(String uuid){

        if (contains(uuid)) {
            for (int i = 0; i < amountOfResumes; i++) {
                if (uuid.equals(storage[i].getUuid())) {
                    System.arraycopy(storage, i + 1, storage, i, amountOfResumes - i);
                    amountOfResumes--;
                }
            }
        } else {
            System.out.println("Method delete:  + Резюме(" + uuid + ") отсутствует в списке!\n");
        }

    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    public Resume[] getAll(){

        try {
            return Arrays.copyOfRange(storage, 0, amountOfResumes - 1);
        } catch (IllegalArgumentException e) {
            System.out.println("Method getAll: Массив пуст!\n");
        }
        return null;
    }

    public int size(){
        return amountOfResumes;
    }

}