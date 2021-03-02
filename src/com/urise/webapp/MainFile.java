package com.urise.webapp;

import java.io.File;
import java.util.Objects;

public class MainFile {
    public static void main(String[] args) {
        File mainFile = new File("/Users/lisa/Desktop/basejava/src/com/urise/webapp");
        File[] dir = mainFile.listFiles();

        assert dir != null;
        for (File file : dir) {
            recursiveSearchFileInDirectory(file);
        }
    }

    private static void recursiveSearchFileInDirectory(File file) {
        if (file.isDirectory()) {
            System.out.println(file.getName());
            File[] files = file.listFiles();
            assert files != null;
            for (File fileInDirectory : files)
                recursiveSearchFileInDirectory(Objects.requireNonNull(fileInDirectory));
        } else {
            System.out.println(file);
        }
    }
}