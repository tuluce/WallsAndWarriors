package com.oops.wallsandwarriors.screens;

import javafx.scene.Scene;

/**
 * An interface to define a generic function to get current screen
 */
public interface Screen {

    /**
     * A method to return the current Screen.
     * @return the current screen as a Screen object.
     */
    Scene getScene();
}
