package com.oops.wallsandwarriors.screens;


import com.oops.wallsandwarriors.util.DebugUtils;
import com.oops.wallsandwarriors.Game;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.BorderPane;

import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

public class HowToPlayScreen extends GeneralScreen {

    public void addtab(Group root, Scene scene){

        TabPane tabPane = new TabPane();

        BorderPane borderPane = new BorderPane();

        Tab rules = new Tab();
        rules.setClosable(false);
        rules.setText("Rules");


        Tab gameModes = new Tab();
        gameModes.setClosable(false);
        gameModes.setText("Game Modes");

        Tab challengeEditor = new Tab();
        challengeEditor.setClosable(false);
        challengeEditor.setText("Challenge Editor");

        Tab controls = new Tab();
        controls.setClosable(false);
        controls.setText("Controls");

        //HBOX FOR RULES


//         HBox hboxRules = new HBox();
        Text rulesText = new Text( "\n\n\n- Wall pieces should properly align on the grid of the board. " +
                "Meaning that, walls cannot end on the bastions of another wall.\n" +
                "- Blue knights and the high tower should remain within the walls.\n" +
                "- Red knights should be kept outside of the walls.\n" +
                "- Walls should be enclosed by all sides.");
        Font theFont = Font.font("Arial", FontWeight.LIGHT, 12);
        rulesText.setFont(theFont);
        rulesText.setLineSpacing(10);
        rulesText.setWrappingWidth(350);


        Text caMode = new Text("\n\n\nCampaign Mode\n");
        Font labelFont = Font.font("Arial", FontWeight.BOLD, 15);
        caMode.setFont(labelFont);


        Text campaignMode = new Text("The player is given standard challenges " +
                "on a basis of increasing difficulties. Harder challenges are being" +
                " unlocked as the player progresses on easier challenges.\n");

        campaignMode.setFont(theFont);
        rulesText.setLineSpacing(10);
        rulesText.setWrappingWidth(350);

        Text cuMode = new Text("\n\n\nCustom Mode\n");
        cuMode.setFont(labelFont);

        Text customMode = new Text("The player can play challenges created by the game community." +
                " These challenges can be both standard and wild.");
        customMode.setFont(theFont);
        rulesText.setLineSpacing(10);
        rulesText.setWrappingWidth(350);

        TextFlow flow = new TextFlow(caMode,campaignMode, cuMode,customMode);

        Text cEditor = new Text("\n\n\nAll players in the W&W Community are able to " +
                "create their own challenges. In the challenge editor," +
                        "you can pick the size and specify the number of red knights," +
                        "blue knights and high towers\n\n\n" +
                        "After creating your challenge, you can share your challenge with t" +
                        "he game community to be played in the custom mode");


        Text control = new Text("\n\n\nFor moving the knights, high tower and" +
                " walls in the Challenge Editor you can grab the object by " +
                "clicking once again.\n\n\nIn the Challenge Screens, you can only " +
                "drag & drop the walls.\n\n\nYou can rotate the pieces 90 degree by " +
                "grabing and clicking the right mouse click."+ "To replace an object, left click on the object." +
                "For removing an object from the grid, right click on the object.  ");

        cEditor.setWrappingWidth(350);
        control.setWrappingWidth(350);
        rules.setContent(rulesText);
        gameModes.setContent(flow);
        challengeEditor.setContent(cEditor);
        controls.setContent(control);
        tabPane.getTabs().add(rules);
        tabPane.getTabs().add(gameModes);
        tabPane.getTabs().add(challengeEditor);
        tabPane.getTabs().add(controls);



        // bind to take available space
        borderPane.setPrefSize(400,300);
        borderPane.setLayoutX(200);
        borderPane.setLayoutY(200);


        borderPane.setCenter(tabPane);
        root.getChildren().add(borderPane);

    }
    @Override
    public Scene getScene() {
        Group root = new Group();
        Scene scene = new Scene(root);
        GraphicsContext g = addBackgroundCanvas(root, "/com/oops/wallsandwarriors/resources/images/background2.png", "How To Play");
        g.setFill(Color.BEIGE);
        g.fillRoundRect(180,180,440,340,30,30);
        DebugUtils.initClickDebugger(scene);
        addtab(root,scene);

        addTransactionButton(root, "Back", 700, 550, Game.getInstance().screenManager.mainMenu);

        return scene;
    }
    
}
