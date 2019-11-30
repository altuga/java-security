package com.kodcu.question36;

import java.io.*;


/**
 * Altug Bilgin Altintas
 * <p>
 * Effective Java Workshop
 * <p>
 * Email : altug@kodcu.com
 * Twitter : @altugaltintas
 */


/*
TODO: 
 1 - What are the alternatives for copy() method
*/
public class ReadFile {

    private static final int BUFFER_SIZE = 8 * 1024;


    static boolean copy(String src, String dst) {
        try (InputStream in = new FileInputStream(src);
             OutputStream out = new FileOutputStream(dst)) {

            byte[] buf = new byte[BUFFER_SIZE];
            int n;
            while ((n = in.read(buf)) >= 0) {
                out.write(buf, 0, n);
            }
            return true;

        } catch (IOException ex) {
            System.err.println(ex);
            return false;
        }


    }

    public static void main(String[] args) {
        String src = "a.txt"; // always gives error
        String dst = "b.txt"; // always gives error
        copy(src, dst);

    }
}
