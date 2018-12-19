package com.oops.wallsandwarriors.screens;

import com.oops.wallsandwarriors.util.DebugUtils;
import com.oops.wallsandwarriors.Game;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ListView;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class CreditsScreen extends GeneralScreen {
    
    @Override
    public Scene getScene() {
        Group root = new Group();
        Scene scene = new Scene(root);
        
        DebugUtils.initClickDebugger(scene);
        GraphicsContext graphics = addBackgroundCanvas(root, "/com/oops/wallsandwarriors/resources/images/background2.png", "Credits");
        addTransitionScreen(root, "Back", 700, 550, Game.getInstance().screenManager.mainMenu);
        drawContent(graphics);

        return scene;
    }
    
    private void drawContent(GraphicsContext graphics) {
        String contentText =
              "Walls & Warriors is a term project done for 'Object-Oriented\n"
            + "Software Engineering' course in Bilkent University.\n\n"
            + "-- Contributors --\n"
            + " Ali Babayev\n"
            + " Tunar Mahmudov\n"
            + " Merve Sagyatanlar\n"
            + " Cagla Sozen\n"
            + " Emin Bahadir Tuluce\n";
        graphics.setFill(Color.BEIGE);
        graphics.fillRoundRect(60, 160, 670, 300, 50, 50);
        graphics.setFont(Font.font("Arial", FontWeight.NORMAL, 24));
        graphics.setFill(Color.BLACK);
        graphics.fillText(contentText, 80, 200);
    }

    public void addNameList(Group root){
        ListView<String> nameList = new ListView<String>();
        ObservableList<String> names = FXCollections.observableArrayList(
                "Ali Babayev                 ali.babayev@ug.bilkent.edu.tr",
                    "Tunar Mahmudov       tunar.mahmudov@ug.bilkent.edu.tr",
                    "Merve Sagyatanlar     merve.sagyatanlar@ug.bilkent.edu.tr",
                    "Cagla Sozen               cagla.sozen@ug.bilkent.edu.tr",
                    "Bahadir Tuluce           bahadir.tuluce@ug.bilkent.edu.tr"
                 );
        nameList.setItems(names);
        nameList.setLayoutX(100);
        nameList.setLayoutY(250);

        nameList.setPrefSize(600,130);

        root.getChildren().add(nameList);

    }
    
}
