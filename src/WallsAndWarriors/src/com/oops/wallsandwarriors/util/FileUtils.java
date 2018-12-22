package com.oops.wallsandwarriors.util;

import java.io.InputStream;
import java.net.URISyntaxException;

public class FileUtils {
    
    private static FileUtils instance;

    /**
     * Constructor that initializes the FileUtils
     */
    private FileUtils() {
        instance = this;
    }

    /**
     * Method to get instance of FileUtils
     * @return instance the instance of FileUtils
     */
    private static FileUtils getInstance() {
        if (instance == null) {
            instance = new FileUtils();
        }
        return instance;
    }

    /**
     * Method to get class input stream of the filepath
     * @param filePath input filepath
     * @return the class input stream of that filepath
     */
    private InputStream getClassInputStream(String filePath) {
        return getClass().getResourceAsStream(filePath);
    }

    /**
     * Method to get input stream of the filepath
     * @param filePath input filepath
     * @return the input stream of that filepath
     */
    public static InputStream getInputStream(String filePath) {
        return getInstance().getClassInputStream(filePath);
    }

    /**
     * Method to get class URI of the filepath
     * @param filePath the input filepath
     * @return the class URI of that filepath
     */
    private String getClassURI(String filePath) {
        try {
            return getClass().getResource(filePath).toURI().toString();
        } catch (URISyntaxException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    /**
     * Method to get URI of the filepath
     * @param filePath the input filepath
     * @return the URI of that filepath
     */
    public static String getURI(String filePath) {
        return getInstance().getClassURI(filePath);
    }
    
}
