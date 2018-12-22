package com.oops.wallsandwarriors.screens;


import com.oops.wallsandwarriors.util.DebugUtils;
import com.oops.wallsandwarriors.Game;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

/**
 * A class to implement high tower view
 * @author Merve Sagyatanlar
 * @author Emin Bahadir Tuluce
 */
public class SettingsScreen extends GeneralScreen {

    /**
     * A inner class for color theme
     */
    private class ColorTheme {
        public final Color allyColor;
        public final Color enemyColor;

        /**
         * A constructor for color theme inner class with given parameters
         */
        public ColorTheme(Color allyColor, Color enemyColor) {
            this.allyColor = allyColor;
            this.enemyColor = enemyColor;
        }
        @Override
        public String toString() {
            return getColorName(allyColor) +
                    " - " + getColorName(enemyColor);
        }
    }
    
    @Override
    public Scene getScene() {
        Group root = new Group();
        Scene scene = new Scene(root);
        GraphicsContext g = addBackgroundCanvas(root,
                "/com/oops/wallsandwarriors/resources/images/background2.png",
                "How To Play");
        g.setFill(Color.BEIGE);
        g.fillRoundRect(120,160,480,240,30,30);
        
        ColorTheme[] themes = initThemes();

        DebugUtils.initClickDebugger(scene);
        ChoiceBox cb = new ChoiceBox(FXCollections.observableArrayList(getThemeNames(themes)));
        showOldValue(cb);
        cb.getSelectionModel().selectedIndexProperty().
                addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable,
                                Number oldValue, Number newValue) {
                int index = newValue.intValue();
                Color allyColor = themes[index].allyColor;
                Color enemyColor = themes[index].enemyColor;
                Game.getInstance().settingsManager.setAllyColor(allyColor);
                Game.getInstance().settingsManager.setEnemyColor(enemyColor);
            }
        });

        cb.setLayoutX(330);
        cb.setLayoutY(330);
        cb.setPrefWidth(230);
        
        addLabel(root, "Sound Volume", 140, 190);
        addLabel(root, "Music Volume", 140, 260);
        addLabel(root, "Knight Colors", 140, 330);

        Slider sl = new Slider();
        sl.valueProperty().addListener(new ChangeListener<Number>() {
            public void changed(ObservableValue<? extends Number> ov,
                                Number old_val, Number new_val) {
                Game.getInstance().settingsManager.setVolume(new_val.doubleValue());
                Game.getInstance().soundManager.updateSoundVolume();
            }
        });
        sl.setMax(1);
        sl.setMin(0);
        sl.setLayoutX(330);
        sl.setLayoutY(200);
        sl.setPrefWidth(230);

        Slider slmusic = new Slider();
        slmusic.valueProperty().addListener(new ChangeListener<Number>() {
            public void changed(ObservableValue<? extends Number> ov,
                                Number old_val, Number new_val) {
                Game.getInstance().settingsManager.setMusicVolume(new_val.doubleValue());
                Game.getInstance().soundManager.updateMusicVolume();
            }
        });
        slmusic.setMax(1);
        slmusic.setMin(0);
        slmusic.setLayoutX(330);
        slmusic.setLayoutY(270);
        slmusic.setPrefWidth(230);
        
        showOldValueSlider(sl, slmusic);

        Game.getInstance().settingsManager.setVolume(sl.getValue());
        Game.getInstance().settingsManager.setMusicVolume(slmusic.getValue());

        addButton(root, "Back", 700, 550, new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Game.getInstance().storageManager.
                        writeSettings(sl.getValue(),slmusic.getValue());
                Game.getInstance().setScreen(Game.getInstance().screenManager.mainMenu);
            }
        });

        root.getChildren().addAll(cb,sl,slmusic);
        return scene;
    }

    /**
     * A method to get initial themes
     * @return themes array
     */
    private ColorTheme[] initThemes() {
        ColorTheme[] themes = {
            new ColorTheme(Color.BLUE, Color.RED),
            new ColorTheme(Color.YELLOW, Color.PURPLE),
            new ColorTheme(Color.GREEN, Color.ORANGE),
            new ColorTheme(Color.CORNFLOWERBLUE, Color.BROWN)
        };
        return themes;
    }

    /**
     * A method to get theme names
     * @return theme names array
     */
    private String[] getThemeNames(ColorTheme[] themes) {
        String[] themeNames = new String[themes.length];
        for (int i = 0; i < themes.length; i++) {
            themeNames[i] = themes[i].toString();
        }
        return themeNames;
    }

    /**
     * A method to show old value of the sliders
     * @param sl slider for sound
     * @param slmusic slider for music
     */
    private void showOldValueSlider(Slider sl, Slider slmusic) {
        double slvalue = Game.getInstance().settingsManager.getVolume();
        sl.setValue(slvalue);
        double slval2= Game.getInstance().settingsManager.getMusicVolume();
        slmusic.setValue(slval2);

    }

    /**
     * A method to show old value of the choice box
     * @param cb choice boz for color
     */
    private void showOldValue(ChoiceBox cb) {
        Color allyColor = Game.getInstance().settingsManager.getAllyColor();
        Color enemyColor = Game.getInstance().settingsManager.getEnemyColor();

        cb.setValue(getColorName(allyColor) + " - " + getColorName(enemyColor));
    }


    final Color colors[] = {Color.BLUE, Color.RED, Color.GREEN, Color.YELLOW,
            Color.PURPLE, Color.BROWN, Color.ORANGE, Color.CORNFLOWERBLUE};
    final String colorNames[] = {"Blue", "Red", "Green", "Yellow", "Purple",
            "Brown", "Orange", "Baby Blue"};

    /**
     * A method to get color name
     * @param color theme color
     * @return color name
     */
    private String getColorName(Color color) {
        for (int i = 0; i < colors.length; i++) {
            if (colors[i].equals(color)) {
                return colorNames[i];
            }
        }
        return null;
    }

    /**
     * A method to add label
     * @param text label text
     * @param root Group object
     * @param x layout coordinate
     * @param y layout coordinate
     */
    private void addLabel(Group root, String text, double x, double y) {
        Label label = new Label(text);
        label.setFont(Font.font("Arial", FontWeight.SEMI_BOLD, 24));
        label.setLayoutX(x);
        label.setLayoutY(y);
        root.getChildren().add(label);
    }

}
