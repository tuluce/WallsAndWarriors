package com.oops.wallsandwarriors.screens;


import com.oops.wallsandwarriors.util.DebugUtils;
import com.oops.wallsandwarriors.Game;
import com.oops.wallsandwarriors.GameConstants;
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
        GraphicsContext g = addBackgroundCanvas(root, "/com/oops/wallsandwarriors/resources/images/background2.png", "How To Play");
        g.setFill(Color.BEIGE);
        g.fillRoundRect(120,160,480,240,30,30);
        
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

        addButton(root, "Back", GameConstants.BACK_BUTTON_X, GameConstants.BACK_BUTTON_Y,
            new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Game.getInstance().storageManager.writeSettings(sl.getValue(),slmusic.getValue());
                Game.getInstance().setScreen(Game.getInstance().screenManager.mainMenu);
            }
        });

        root.getChildren().addAll(cb,sl,slmusic);
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
    private void showOldValueSlider(Slider sl, Slider slmusic) {
        double slvalue = Game.getInstance().settingsManager.getVolume();
        sl.setValue(slvalue);
        double slval2= Game.getInstance().settingsManager.getMusicVolume();
        slmusic.setValue(slval2);

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
    
    private void addLabel(Group root, String text, double x, double y) {
        Label label = new Label(text);
        label.setFont(Font.font("Arial", FontWeight.SEMI_BOLD, 24));
        label.setLayoutX(x);
        label.setLayoutY(y);
        root.getChildren().add(label);
    }

}
