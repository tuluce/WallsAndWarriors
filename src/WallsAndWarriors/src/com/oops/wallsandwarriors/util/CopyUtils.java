package com.oops.wallsandwarriors.util;

import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;

/**
 * A class for copy-paste functionalities
 * @author Cagla Sozen
 */
public class CopyUtils {

    /**
     * Method to copy a String to system clipboard.
     * @param str String to copy into clipboard.
     */
    public static void copyToClipboard(String str) {
        final Clipboard clipboard = Clipboard.getSystemClipboard();
        final ClipboardContent content = new ClipboardContent();

        content.putString(str);
        clipboard.setContent(content);
    }
}
