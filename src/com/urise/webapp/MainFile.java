package com.urise.webapp;

import java.io.File;
import java.util.Objects;

public class MainFile {
    public static void main(String[] args) {
        File mainFile = new File("./src/com/urise/webapp");
        File[] files = mainFile.listFiles();

        assert files != null;
        for (File file : files) {
            recursiveSearchFileInDirectory(file, 0);
        }
    }

    private static void recursiveSearchFileInDirectory(File file, int level) {
        String whitespace = " ".repeat(level * 2);
        if (file.isDirectory()) {
            System.out.println(whitespace + file.getName());
            File[] files = file.listFiles();
            assert files != null;
            for (File fileInDirectory : files)
                recursiveSearchFileInDirectory(Objects.requireNonNull(fileInDirectory), level + 1);
        } else {
            System.out.println(whitespace + file.getPath());
        }
    }
}