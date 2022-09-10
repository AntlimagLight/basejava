package com.urise.webapp;

import com.urise.webapp.model.Resume;
import com.urise.webapp.storage.ListStorage;
import com.urise.webapp.storage.Storage;

public class MainTestCollectionStorage {

    static final Storage COLLECTION_STORAGE = new ListStorage();

    public static void main(String[] args) {
        Resume r1 = new Resume("uuid1", "Igor");
        Resume r2 = new Resume("uuid2", "Alise");
        Resume r3 = new Resume("uuid3", "Vasiliy");

        COLLECTION_STORAGE.save(r1);
        COLLECTION_STORAGE.save(r2);
        COLLECTION_STORAGE.save(r3);
        System.out.println("Size:" + COLLECTION_STORAGE.size());
        printAll();
        System.out.println();

        COLLECTION_STORAGE.update(r2);
        printAll();
        System.out.println();

        System.out.println(COLLECTION_STORAGE.get("uuid2").getUuid());
        System.out.println();

        COLLECTION_STORAGE.delete("uuid2");
        System.out.println("Size:" + COLLECTION_STORAGE.size());
        printAll();
        System.out.println();

        COLLECTION_STORAGE.clear();
        System.out.println("___");
        printAll();

    }

    public static void printAll() {
        for (Resume r : COLLECTION_STORAGE.getAllSorted()) {
            System.out.println(r.getUuid() + " : " + r.getFullName());
        }
    }

}
