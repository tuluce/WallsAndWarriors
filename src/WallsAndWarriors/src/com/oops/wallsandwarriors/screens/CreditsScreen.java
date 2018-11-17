package com.oops.wallsandwarriors.screens;

import com.oops.wallsandwarriors.util.DebugUtils;
import com.oops.wallsandwarriors.Game;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.ListView;

public class CreditsScreen extends GeneralScreen {
    
    @Override
    public Scene getScene() {
        Group root = new Group();
        Scene scene = new Scene(root);
        
        DebugUtils.initClickDebugger(scene);
        addBackgroundCanvas(root, "resources/images/background2.png", "Credits");
        addTransactionButton(root, "Back", 700, 550, Game.getInstance().screenManager.mainMenu);
        addNameList(root);

        return scene;
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
