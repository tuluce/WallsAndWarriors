package com.oops.wallsandwarriors.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class FileUtils {

    public static InputStream getInputStream(String filePath) {
        try {
            return new FileInputStream(filePath);
        } catch (FileNotFoundException exc) {
            exc.printStackTrace();
        }
        return null;
    }
    
}
