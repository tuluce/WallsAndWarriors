package com.oops.wallsandwarriors.screens;


import com.oops.wallsandwarriors.util.DebugUtils;
import com.oops.wallsandwarriors.Game;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.paint.Color;


public class SettingsScreen extends GeneralScreen {
    
    private class ColorTheme {
        public final Color allyColor;
        public final Color enemyColor;
        public ColorTheme(Color allyColor, Color enemyColor) {
            this.allyColor = allyColor;
            this.enemyColor = enemyColor;
        }
        @Override
        public String toString() {
            return getColorName(allyColor) + " - " + getColorName(enemyColor);
        }
    }
    
    @Override
    public Scene getScene() {
        Group root = new Group();
        Scene scene = new Scene(root);
        
        ColorTheme[] themes = initThemes();

        DebugUtils.initClickDebugger(scene);
        ChoiceBox cb = new ChoiceBox(FXCollections.observableArrayList(getThemeNames(themes)));
        showOldValue(cb);
        cb.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                int index = newValue.intValue();
                Color allyColor = themes[index].allyColor;
                Color enemyColor = themes[index].enemyColor;
                Game.getInstance().settingsManager.setAllyColor(allyColor);
                Game.getInstance().settingsManager.setEnemyColor(enemyColor);
            }
        });

        cb.setLayoutX(300);
        cb.setLayoutY(300);
        Label colorLabel = new Label("Knight colors");
        colorLabel.setLayoutX(200);
        colorLabel.setLayoutY(300);
        Label musicLabel = new Label("Music");
        musicLabel.setLayoutY(250);
        musicLabel.setLayoutX(200);
        Label soundLabel = new Label("Sound");
        soundLabel.setLayoutX(200);
        soundLabel.setLayoutY(200);

        Slider sl = new Slider();
        sl.valueProperty().addListener(new ChangeListener<Number>() {
            public void changed(ObservableValue<? extends Number> ov,
                                Number old_val, Number new_val) {
                Game.getInstance().settingsManager.setVolume(new_val.doubleValue());
            }
        });
        sl.setMax(1);
        sl.setMin(0);
        sl.setLayoutX(300);
        sl.setLayoutY(200);

        Slider slmusic = new Slider();
        slmusic.valueProperty().addListener(new ChangeListener<Number>() {
            public void changed(ObservableValue<? extends Number> ov,
                                Number old_val, Number new_val) {
                Game.getInstance().settingsManager.setVolume(new_val.doubleValue());
            }
        });
        slmusic.setMax(1);
        slmusic.setMin(0);
        slmusic.setLayoutX(300);
        slmusic.setLayoutY(250);

        Game.getInstance().settingsManager.setVolume(sl.getValue());
        Game.getInstance().settingsManager.setMusicVolume(slmusic.getValue());

        addBackgroundCanvas(root, "resources/images/background2.png", "Settings");
        addTransactionButton(root, "Back", 700, 550, Game.getInstance().screenManager.mainMenu);
        root.getChildren().addAll(cb,sl,slmusic,colorLabel,musicLabel,soundLabel);
        return scene;
    }
    
    private ColorTheme[] initThemes() {
        ColorTheme[] themes = {
            new ColorTheme(Color.BLUE, Color.RED),
            new ColorTheme(Color.YELLOW, Color.PURPLE),
            new ColorTheme(Color.GREEN, Color.ORANGE),
            new ColorTheme(Color.CORNFLOWERBLUE, Color.BROWN)
        };
        return themes;
    }
    
    private String[] getThemeNames(ColorTheme[] themes) {
        String[] themeNames = new String[themes.length];
        for (int i = 0; i < themes.length; i++) {
            themeNames[i] = themes[i].toString();
        }
        return themeNames;
    }
    
    private void showOldValue(ChoiceBox cb) {
        Color allyColor = Game.getInstance().settingsManager.getAllyColor();
        Color enemyColor = Game.getInstance().settingsManager.getEnemyColor();

        cb.setValue(getColorName(allyColor) + " - " + getColorName(enemyColor));
    }
    
    final Color colors[] = {Color.BLUE, Color.RED, Color.GREEN, Color.YELLOW, Color.PURPLE, Color.BROWN, Color.ORANGE, Color.CORNFLOWERBLUE};
    final String colorNames[] = {"Blue", "Red", "Green", "Yellow", "Purple", "Brown", "Orange", "Baby Blue"};
    
    private String getColorName(Color color) {
        for (int i = 0; i < colors.length; i++) {
            if (colors[i].equals(color)) {
                return colorNames[i];
            }
        }
        return null;
    }

}
