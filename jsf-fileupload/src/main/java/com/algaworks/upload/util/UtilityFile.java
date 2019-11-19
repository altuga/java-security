package com.algaworks.upload.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Arrays;
import java.util.List;

public class UtilityFile {

    public static File write(String name, byte[] contents) throws IOException {
        File file = new File(directoryRootForArchives(), name);

        OutputStream out = new FileOutputStream(file);
        out.write(contents);
        out.close();

        return file;
    }

    public static List<File> toList() {
        File dir = directoryRootForArchives();

        return Arrays.asList(dir.listFiles());
    }

    public static java.io.File directoryRootForArchives() {
        File dir = new File(rootDirectory(), "files");

        if (!dir.exists()) {
            dir.mkdirs();
        }

        return dir;
    }
    
    public static File rootDirectory() {
        // We are using a directory within the temporary folder.
        // In your project, I imagine you want to change this to something like:
        // File dir = new File(System.getProperty("user.home"), "algaworks");
        File dir = new File(System.getProperty("java.io.tmpdir"), "kodcu");

        if (!dir.exists()) {
            dir.mkdirs();
        }

        return dir;
    }
}