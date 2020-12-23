package com.urise.webapp;

import com.urise.webapp.model.Resume;
import com.urise.webapp.storage.AbstractArrayStorage;
import com.urise.webapp.storage.ArrayStorage;
import com.urise.webapp.storage.SortedArrayStorage;

/**
 * Test for your com.urise.webapp.storage.ArrayStorage implementation
 */
public class MainTestArrayStorage {
    private static final SortedArrayStorage SORTED_ARRAY_STORAGE = new SortedArrayStorage();
    private static final ArrayStorage ARRAY_STORAGE = new ArrayStorage();

    public static void main(String[] args) {

        Resume resumeFirst = new Resume("0");
        Resume resumeSecond = new Resume("1");
        Resume resumeThird = new Resume("2");
        Resume resumeFourth = new Resume("4");
        Resume resumeFifth = new Resume("3");

        SORTED_ARRAY_STORAGE.save(resumeFirst);
        SORTED_ARRAY_STORAGE.save(resumeSecond);
        SORTED_ARRAY_STORAGE.save(resumeThird);
        SORTED_ARRAY_STORAGE.save(resumeFourth);

        Resume resume1 = new Resume("4");
        Resume resume2 = new Resume("2");
        Resume resume3 = new Resume("5");
        Resume resume4 = new Resume("0");
        Resume resume5 = new Resume("1");

        ARRAY_STORAGE.save(resume1);
        ARRAY_STORAGE.save(resume2);
        ARRAY_STORAGE.save(resume3);
        ARRAY_STORAGE.save(resume4);

        System.out.println("Проверка на обновление существующих резюме");

        SORTED_ARRAY_STORAGE.update(resume2);
        ARRAY_STORAGE.update(resume2);

        System.out.println("Проверка на обновление несуществующих резюме");

        SORTED_ARRAY_STORAGE.update(resumeFifth);
        ARRAY_STORAGE.update(resume5);

        System.out.println("Проверка на удаление существующих резюме SORTED_ARRAY_STORAGE");
        SORTED_ARRAY_STORAGE.delete("2");
        System.out.println(SORTED_ARRAY_STORAGE.size());
        printAll(SORTED_ARRAY_STORAGE);

        System.out.println("Проверка на удаление существующих резюме ARRAY_STORAGE");
        ARRAY_STORAGE.delete("0");
        System.out.println(ARRAY_STORAGE.size());
        printAll(ARRAY_STORAGE);

        System.out.println("Проверка на удаление несуществующих резюме SORTED_ARRAY_STORAGE");
        SORTED_ARRAY_STORAGE.delete("3");
        System.out.println(SORTED_ARRAY_STORAGE.size());
        printAll(SORTED_ARRAY_STORAGE);

        System.out.println("Проверка на удаление неcуществующих резюме ARRAY_STORAGE");
        ARRAY_STORAGE.delete("1");
        System.out.println(ARRAY_STORAGE.size());
        printAll(ARRAY_STORAGE);

        System.out.println("Проверка на сохранение элемента в SORTED_ARRAY_STORAGE");
        SORTED_ARRAY_STORAGE.save(resumeFifth);
        System.out.println(SORTED_ARRAY_STORAGE.size());
        printAll(SORTED_ARRAY_STORAGE);

        System.out.println("Проверка на сохранение элемента в ARRAY_STORAGE");
        ARRAY_STORAGE.save(resume5);
        System.out.println(ARRAY_STORAGE.size());
        printAll(ARRAY_STORAGE);


//        Resume r1 = new Resume();
//        r1.setUuid("uuid1");
//        Resume r2 = new Resume();
//        r2.setUuid(null);
//        Resume r3 = new Resume();
//        r3.setUuid("uuid3");
//
//
//        ARRAY_STORAGE.save(r1);
//        ARRAY_STORAGE.save(r2);
//        ARRAY_STORAGE.save(r3);
//
//        System.out.println("Get r1: " + ARRAY_STORAGE.get(r1.getUuid()));
//        System.out.println("Size: " + ARRAY_STORAGE.size());
//
//        System.out.println("Get dummy: " + ARRAY_STORAGE.get("dummy"));
//
//        ARRAY_STORAGE.save(r1);
//        Resume r4 = new Resume();
//        System.out.println(r4.getUuid());
//        ARRAY_STORAGE.delete(r4.getUuid());
//        ARRAY_STORAGE.get(r4.getUuid());
//
//        printAll();
//        ARRAY_STORAGE.delete(r1.getUuid());
//        printAll();
//        ARRAY_STORAGE.clear();
//        printAll();
//        ARRAY_STORAGE.update(r4);
//
//
//        System.out.println("Size: " + ARRAY_STORAGE.size());
    }

    private static void printAll(AbstractArrayStorage storage) {
        System.out.println("Get All");

        try {
            for (Resume r : storage.getAll()) {
                System.out.println(r);
            }
        } catch (NullPointerException ignored) {
        }
    }
}
