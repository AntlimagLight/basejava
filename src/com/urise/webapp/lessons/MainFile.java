package com.urise.webapp.lessons;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class MainFile {
    public static void main(String[] args) {
        String filePath = ".\\.gitignore";

        File file = new File(filePath);
        try {
            System.out.println(file.getCanonicalPath());
        } catch (IOException e) {
            throw new RuntimeException("Error", e);
        }

        File dir = new File("./src");
        System.out.println(dir.isDirectory());
        System.out.println();
        String[] list = dir.list();
        if (list != null) {
            for (String name : list) {
                System.out.println(name);
            }
        }

        try (FileInputStream fis = new FileInputStream(filePath)) {
            System.out.println(fis.read());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println("_____ Домашнее задание к уроку 8_____");
        printDirectoryDeep(dir, "./");
        System.out.println("_____ Домашнее задание к уроку 9_____");
        printDirectoryDeepIndent(dir, "");
    }

    public static void printDirectoryDeep(File dir, String track) {
        File[] list = dir.listFiles();
        if (list != null) {
            for (File file : list) {
                if (file.isFile()) {
                    System.out.println(track + file.getName() + " ");
                } else if (file.isDirectory()) {
                    printDirectoryDeep(file, track + file.getName() + "/");
                }
            }
        } else {
            System.out.println("Директория пуста");
        }
    }

    public static void printDirectoryDeepIndent(File dir, String track) {
        File[] list = dir.listFiles();
        if (list != null) {
            for (File file : list) {
                if (file.isFile()) {
                    System.out.println(track + file.getName());
                } else if (file.isDirectory()) {
                    System.out.println(track + "DIR " + file.getName());
                    printDirectoryDeepIndent(file, track + "    ");
                }
            }
        } else {
            System.out.println("Директория пуста");
        }
    }
}
