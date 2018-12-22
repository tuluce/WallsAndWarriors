package com.oops.wallsandwarriors.screens;

import javafx.scene.Scene;

/**
 * An interface to define a generic function to get current sreen content
 */
public interface Screen {

    /**
     * A method to reach the current screen content.
     * @return the current conent of the screen as a Scene object.
     */
    Scene getScene();
    
}
