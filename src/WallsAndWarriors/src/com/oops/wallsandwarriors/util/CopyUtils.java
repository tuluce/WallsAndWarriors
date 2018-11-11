package com.oops.wallsandwarriors.util;

import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;

public class CopyUtils {

    public static void copyToClipboard(String str) {
        final Clipboard clipboard = Clipboard.getSystemClipboard();
        final ClipboardContent content = new ClipboardContent();

        content.putString(str);
        clipboard.setContent(content);
    }

}
