package com.oops.wallsandwarriors.util;

import java.io.InputStream;

public class FileUtils {
    
    private static FileUtils instance;
    
    private FileUtils() {
        instance = this;
    }
    
    private static FileUtils getInstance() {
        if (instance == null) {
            instance = new FileUtils();
        }
        return instance;
    }
    
    private InputStream getClassInputStream(String filePath) {
        return getClass().getResourceAsStream(filePath);
    }
    
    public static InputStream getInputStream(String filePath) {
        return getInstance().getClassInputStream(filePath);
    }
    
}
