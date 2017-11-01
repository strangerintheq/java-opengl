package com.github.strangerintheq.java.opengl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.net.URL;

import com.jogamp.common.util.IOUtil;

public class Files {
    public static String read(String filename) {
        StringBuilder src = new StringBuilder();
        BufferedReader reader = null;
        try {
            ClassLoader classLoader = TwoTriangles.class.getClassLoader();
            URL resource = classLoader.getResource(filename);
            reader = new BufferedReader(new FileReader(new File(resource.getFile())));
            String line;
            while ((line = reader.readLine()) != null) {
                src.append(line).append("\n");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            IOUtil.close(reader, false);
        }
        return src.toString();
    }
}
