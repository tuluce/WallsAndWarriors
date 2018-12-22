/**
 * A class to utilize  in the game to copy Base64 text to clipboard by a single button.
 * @author OOPs
 * @version 21.12.19
 */

package com.oops.wallsandwarriors.util;

import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;


public class CopyUtils {

    /**
     * Method to copy a String to system clipboard.
     * @param  str  String to copy into clipboard.
     */
    public static void copyToClipboard(String str) {
        final Clipboard clipboard = Clipboard.getSystemClipboard();
        final ClipboardContent content = new ClipboardContent();

        content.putString(str);
        clipboard.setContent(content);
    }
}
