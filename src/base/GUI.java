package base;

import base.ModelStuff.Model;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import javax.xml.soap.Text;
import java.util.Observable;
import java.util.Observer;

public class GUI implements Observer {

    /**
     * Overall gui stuff
     *
     *
     */
    Stage primaryStage;
    Model model;
    Controller controller;
    private final String GAME_NAME="Operation Snow Storm" ;

    public GUI(Stage primaryStage,Model model,Controller controller){
        this.primaryStage=primaryStage;
        this.model=model;
        this.controller=controller;
        setupStartingMenuScene();
        setupNewGameScene();
    }

    public void show(){
        primaryStage.show();
    }

    public Stage getPrimaryStage(){
        return primaryStage;
    }





//startMenu stuff
    Scene startMenuScene;

    /**Josh
     *
     * css ids:
     *      menuPane
     *      subMenuPane
     *      gameTitle
     *      button
     *      loadButton
     *
     * draw menu background in here
     *
     */
    private void setupStartingMenuScene(){

        //place where gameName will go
        VBox menuPane=new VBox();
        menuPane.setId("menuPane");

        StackPane subMenuPane=new StackPane();
        subMenuPane.setId("subMenuPane");

        //draw background here
        Canvas canvas=new Canvas();
        subMenuPane.getChildren().add(canvas);


        subMenuPane.getChildren().add(menuPane);

        Label title=new Label("Operation Snow Storm");
        title.setId("gameTitle");
        menuPane.getChildren().add(title);

        setupStartMenuPane(menuPane);

        startMenuScene=new Scene(subMenuPane);
        startMenuScene.getStylesheets().add("CSS/StartMenu.css");

    }

    /**Josh
     *
     * setups the buttons in the start menu
     *
     * buttons dont connect to controler as the only inpact menu
     *
     * CSS in StartMenu.CSS
     *      button
     *
     *
     * @param buttonPane
     */
    private void setupStartMenuPane(Pane buttonPane){
        Button newGameButton=new Button("New Game");
        newGameButton.setId("button");
        newGameButton.setOnAction(e->showNewGameMenu());
        buttonPane.getChildren().add(newGameButton);

        Button loadGameButton=new Button("Load Game");
        loadGameButton.setId("button");
        loadGameButton.setOnAction(e->controller.loadGameButton());
        buttonPane.getChildren().add(loadGameButton);

        Button exitButton=new Button("Exit");
        exitButton.setId("button");
        exitButton.setOnAction(e->primaryStage.close());
        buttonPane.getChildren().add(exitButton);
    }

    /**Josh
     *
     * Sets up the starting menu
     *
     *
     * css ids:
     *      startMenuPane
     *      mainButton
     *      buttonPane
     *      gameTitle
     *
     * contains :
     *
     * canvas of back ground for game
     *
     *
     *
     */
    public void showStartMenu(){
        primaryStage.setScene(startMenuScene);
    }

//new Game menu
    Scene newGameMenuScene;

    /**
     *creates the menu shown when the load game button is used
     *
     * css in StartMenu.css
     *      text
     *      gameTitle
     *      textField
     *      enterButton
     *      button
     *      subMenuPane
     *
     *
     */
    private void setupNewGameScene(){
        VBox mainHolder=new VBox();
        mainHolder.setId("menuPane");
        Label title=new Label("New Game");
        title.setId("gameTitle");
        mainHolder.getChildren().add(title);

        Label lbl=new Label("Enter name:");
        lbl.setId("text");
        mainHolder.getChildren().add(lbl);

        HBox textLineHolder=new HBox();
        textLineHolder.setId("tempHolder");
        mainHolder.getChildren().add(textLineHolder);

            TextField newGameInput=new TextField();
            newGameInput.setId("textField");
            textLineHolder.getChildren().add(newGameInput);

            Button enterButton=new Button("Enter");
            enterButton.setId("enterButton");
            enterButton.setOnAction(e->{
                controller.loadGameEnter(newGameInput.getText());
            });
            textLineHolder.getChildren().add(enterButton);

        Button backButton=new Button("Back");
        backButton.setId("button");
        backButton.setOnAction(e->{
            newGameInput.setText("");
            showStartMenu();
        });
        mainHolder.getChildren().add(backButton);

        newGameMenuScene=new Scene(mainHolder);
        newGameMenuScene.getStylesheets().add("CSS/StartMenu.css");
    }

    public void showNewGameMenu(){
        primaryStage.setScene(newGameMenuScene);
    }

//load menu
    Scene loadGameScene;

    private void setupLoadGameMenu(){

    }

    public void showLoadGameMenu(){



    }

    public void newGameScene(TextField info,EventHandler<ActionEvent> startAction,EventHandler<ActionEvent> backAction){

    }



//main game stuff

    @Override
    public void update(Observable o, Object arg) {

    }



}
