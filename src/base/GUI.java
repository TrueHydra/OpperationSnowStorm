package base;

import base.ModelStuff.Storage.Monster;
import base.ModelStuff.Storage.Player;
import base.ModelStuff.Storage.Room;
import com.sun.prism.GraphicsPipeline;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.Arrays;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import java.util.zip.GZIPOutputStream;

public class GUI implements Observer {

    /**
     * Overall gui stuff
     *
     *
     */
    Stage primaryStage;
    Controller controller;
    private final String GAME_NAME="Operation Snow Storm" ;

    public GUI(Stage primaryStage,Controller controller){
        this.primaryStage=primaryStage;
        this.primaryStage.setResizable(false);
        this.controller=controller;
        setupStartingMenuScene();
        setupNewGameScene();
        setupLoadGameMenu();
        setupMainGameScene();
        setUpEscMenu();
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
    Label newGameError;

    /**
     *creates the menu shown when the new game button is used
     *
     * css in StartMenu.css
     *      text
     *      gameTitle
     *      textField
     *      enterButton
     *      button
     *      subMenuPane
     *      error
     *
     *
     */
    private void setupNewGameScene(){
        VBox newGameMenuHolder=new VBox();
        newGameMenuHolder.setId("menuPane");
        Label title=new Label("New Game");
        title.setId("gameTitle");
        newGameMenuHolder.getChildren().add(title);

        Label lbl=new Label("Enter name:");
        lbl.setId("text");
        newGameMenuHolder.getChildren().add(lbl);

        HBox textLineHolder=new HBox();
        textLineHolder.setId("tempHolder");
        newGameMenuHolder.getChildren().add(textLineHolder);

            TextField newGameInput=new TextField();
            newGameInput.setId("textField");
            textLineHolder.getChildren().add(newGameInput);

            Button enterButton=new Button("Enter");
            enterButton.setId("enterButton");
            enterButton.setOnAction(e->{
                controller.startNewGame(newGameInput.getText());
            });
            enterButton.setOnKeyTyped(e->{
                if(e.equals(KeyCode.ENTER))
                {
                    enterButton.fire();
                }
            });
            textLineHolder.getChildren().add(enterButton);

        newGameError=new Label();
        newGameError.setId("error");
        newGameMenuHolder.getChildren().add(newGameError);

        Button backButton=new Button("Back");
        backButton.setId("button");
        backButton.setOnAction(e->{
            newGameInput.setText("");
            showStartMenu();
        });
        newGameMenuHolder.getChildren().add(backButton);

        newGameMenuScene=new Scene(newGameMenuHolder);
        newGameMenuScene.getStylesheets().add("CSS/StartMenu.css");
    }

    public void addNewGameError(String error){
        newGameError.setText(error);
    }

    /**Josh
     *
     * shows new game menu
     */
    public void showNewGameMenu(){
        primaryStage.setScene(newGameMenuScene);
    }

//load menu
    /**
     *
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
     */
    Scene loadGameScene;
    VBox savesPane;

    /**Josh
     *
     * creates the load game menu
     *
     * css in startMenu.CSS
     *      subMenuPane
     *      gameTitle
     *      savesPane
     *      button
     *
     */
    private void setupLoadGameMenu(){
        VBox mainPane=new VBox();
        mainPane.setId("subMenuPane");

        Label title=new Label("Current saves");
        title.setId("gameTitle");
        mainPane.getChildren().add(title);

        savesPane=new VBox();
        savesPane.setId("savesPane");
        mainPane.getChildren().add(savesPane);

        Button back=new Button("Back");
        back.setId("button");
        back.setOnAction(e->showStartMenu());
        mainPane.getChildren().add(back);

        loadGameScene=new Scene(mainPane);
        loadGameScene.getStylesheets().add("CSS/StartMenu.css");
    }

    /**Josh
     *
     * shows the load game menu and creates the saves
     *
     * css in StartMenu.css
     *      savesHBox
     *      loadButtona
     *      text
     *
     * @param saves
     */
    public void showLoadGameMenu(String[] saves){
        System.out.println(Arrays.toString(saves));
        savesPane.getChildren().clear();
        for(String s:saves){

            HBox temp=new HBox();
            temp.setId("savesHBox");

            Label a=new Label(s);
            a.setId("text");
            temp.getChildren().add(a);

            Button open=new Button("Open");
            open.setId("loadButton");
            open.setOnAction(e->{
                controller.loadGameEnter(s);
            });
            temp.getChildren().add(open);

            Button delete=new Button("Delete");
            delete.setId("loadButton");
            delete.setOnAction(e->controller.deleteSave(s));
            temp.getChildren().add(delete);
            temp.setVisible(true);

            savesPane.getChildren().add(temp);
            savesPane.setVisible(true);
        }
        primaryStage.setScene(loadGameScene);
    }


    public void newGameScene(TextField info,EventHandler<ActionEvent> startAction,EventHandler<ActionEvent> backAction){

    }

//main game stuff
    //uses mainGame.css
    Scene mainGameScene;
    GridPane buttonsPane,basePane;

    //player info stuff
    Pane healthBar;
    Label healthNumber;
    Label weapon;
    VBox playerPlan;

    //textPlaneStuff
    VBox textPane;

    //options pane stuff
    Pane optionsPane;
    VBox invntoryPane;


    @Override
    public void update(Observable o, Object arg) {
        if(o instanceof Player){
            addTextToTextPane((String)arg);
            setUpPlayPane();
        }else if(o instanceof Room){
            addTextToTextPane((String) arg);
        }else if(o instanceof Monster){
            addTextToTextPane((String)arg);
        }
        controller.ready();
    }

    /**Josh
     *
     * css:
     *      escButton
     *      basePane
     *      overAllPane
     *
     */
    private void setupMainGameScene(){
        StackPane overAllPane=new StackPane();
        overAllPane.setId("overAllPane");
        overAllPane.setOnKeyTyped(e->{
            System.out.println("test");
            if (e.equals(KeyCode.ESCAPE)){
                showEscMenu();
            }
        });

        basePane=new GridPane();
        basePane.setId("basePane");
        overAllPane.getChildren().add(basePane);

        Button escButton=new Button("esc");
        escButton.setId("escButton");
        escButton.setOnAction(e->showEscMenu());
        overAllPane.getChildren().add(escButton);


        mainGameScene=new Scene(overAllPane);
        mainGameScene.getStylesheets().add("CSS/mainGame.css");

        playerPlan=new VBox();
        playerPlan.setId("playerPane");
        basePane.add(playerPlan,0,0);

        setUpTextPane();
        setupOptionsPane();
    }

    /**Josh
     *
     * this is done in the show method not at the start as it requiers the player to be built
     *
     * css:
     *      playerPane
     *      title
     *      healthPane
     *      label
     *      healthBarOutline
     *      healthBar
     *
     */
    private void setUpPlayPane(){
        playerPlan.getChildren().clear();

        Label title=new Label("Player Info:");
        title.setId("title");
        playerPlan.getChildren().add(title);

        HBox healthPane =new HBox();
        healthPane.setId("healthPane");
        playerPlan.getChildren().add(healthPane);

            Label health=new Label("Health:");
            health.setId("label");
            healthPane.getChildren().add(health);

            Pane healthBarOutline=new Pane();
            healthBarOutline.setId("healthBarOutline");
            healthPane.getChildren().add(healthBarOutline);

            healthBar=new Pane();
            healthBar.setId("healthBar");
            healthBar.setPrefWidth(200*controller.getPlayer().getHealth()/100);
            healthBarOutline.getChildren().add(healthBar);

            healthNumber =new Label(""+controller.getPlayer().getHealth());
            healthNumber.setId("label");
            healthPane.getChildren().add(healthNumber);

        Label weaponLabel=new Label("Equipped Weapon:");
        weaponLabel.setId("label");
        playerPlan.getChildren().add(weaponLabel);

        weapon=new Label("    "+controller.getPlayer().getWeapon().getName());
        weapon.setId("label");
        playerPlan.getChildren().add(weapon);
    }

    /**Josh
     *
     * css:
     *      scrollPane
     *      textPane
     *
     */
    public void setUpTextPane(){
        /*
        Pane overAllPane=new Pane();
        overAllPane.setId("majorPane");
        basePane.add(overAllPane,0,1);

         */

        ScrollPane textScrollPane=new ScrollPane();
        textScrollPane.setId("textScrollPane");
        basePane.add(textScrollPane,0,1);
        Platform.runLater(()->textScrollPane.vvalueProperty().bind(textPane.heightProperty()));


        textPane=new VBox();
        textPane.setId("textPane");
        textScrollPane.setContent(textPane);
    }

    /**Josh
     *
     * css:
     *      text
     *
     * @param s
     */
    public void addTextToTextPane(String s){
        Text t=new Text(s);
        t.setId("text");

        textPane.getChildren().add(t);
    }

    /**Josh
     *
     * this gets the text displayed in the text pane used for saving the text
     *
     * @return
     */
    public String getTextFromTextPane(){
        String rtn="";
        for(Node n:textPane.getChildren()){
            Text t=(Text)n;
            rtn=rtn+"\n"+t.getText();
        }
        return rtn.substring(1);
    }

    /**Josh
     *
     * shows the main game scene
     * creates the playerPane
     *
     */
    public void showGameScene() {
    //    primaryStage.hide();
        setUpPlayPane();
        primaryStage.setScene(mainGameScene);
        primaryStage.show();
    }

    /**Josh
     *
     * shows the game scene
     *
     * @param initText
     */
    public void showGameScene(String initText) {
        //    primaryStage.hide();
        setUpPlayPane();
        textPane.getChildren().clear();
        addTextToTextPane(initText);

        primaryStage.setScene(mainGameScene);
        primaryStage.show();

    }

    /**Josh
     *
     * css:
     *      optionsPane
     *
     */
    private void setupOptionsPane(){
        ScrollPane optionsScrollPane=new ScrollPane();
        optionsScrollPane.setContent(optionsPane);
        optionsScrollPane.setId("optionsScrollPane");
        optionsPane=new VBox();
        optionsPane.setId("optionsPane");
        basePane.add(optionsPane,1,1);
    }

    public Pane getOptionsPane(){
        return optionsPane;
    }

    /**Josh
     *
     * css:
     *      option
     *
     * @param button
     */
    public void addButtonsToOptions(List<Button> button){
        optionsPane.getChildren().clear();
        for(Button b:button){
            b.setId("option");
            optionsPane.getChildren().add(b);
        }
    }


//esc menu stuff
    //uses escMenu.css
    Scene escMenu;

    /**Josh
     *
     * css:
     *      mainPane
     *      button
     *
     */

    private void setUpEscMenu(){

        VBox paneMain=new VBox();
        paneMain.setId("otherPane");

        Button saveButton=new Button("Save");
        saveButton.setId("button");
        saveButton.setOnAction(e->controller.saveButton());
        paneMain.getChildren().add(saveButton);


        Button exitButton=new Button("Exit");
        exitButton.setId("button");
        exitButton.setOnAction(e-> {
            showStartMenu();
            controller.exitGame();
            System.out.println("tex");
        });

        paneMain.getChildren().add(exitButton);

        Button returnButton=new Button("Return");
        returnButton.setId("button");
        returnButton.setOnAction(e->showGameScene());
        paneMain.getChildren().add(returnButton);

        escMenu=new Scene(paneMain);
        escMenu.getStylesheets().add("CSS/escMenu.css");
    }

    public void showEscMenu(){
        primaryStage.setScene(escMenu);
    }



}


