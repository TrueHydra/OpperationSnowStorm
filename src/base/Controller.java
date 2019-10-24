package base;

import base.ModelStuff.Storage.Items.FoodItem;
import base.ModelStuff.Storage.Items.Item;
import base.ModelStuff.Storage.Items.PuzzleItem;
import base.ModelStuff.Storage.Items.Weapon;
import base.ModelStuff.Storage.Map;
import base.ModelStuff.Storage.Player;
import base.ModelStuff.Storage.Room;
import com.sun.xml.internal.ws.api.server.EndpointReferenceExtensionContributor;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.omg.CosNaming.NamingContextExtPackage.StringNameHelper;

import java.lang.reflect.Array;
import java.nio.file.Path;
import java.util.*;
import java.util.regex.Pattern;

public class Controller {

    private Player player;
    private Map map;


    private boolean keepGoing=false;
    private GUI gui;
    private SaveUtility saveUtility;



    public Controller(Stage primaryStage){
       this.gui=new GUI(primaryStage,this);
       saveUtility=new SaveUtility();
    }

    public void start(){
        gui.show();
        gui.showStartMenu();
    }

    public Player getPlayer(){
        return player;
    }

//start menu/load menu/start new game menu stuff

    //complete
    /**
     * is activated when load gae button pressed
     *
     */
    public void loadGameButton(){
        gui.showLoadGameMenu(saveUtility.getSaves());
    }

    /**Josh
     *
     * creates the neew save when button is hit also checks if name is proper
     *
     * @param s
     */
    public void startNewGame(String s){
        if(Arrays.asList(saveUtility.getSaves()).contains(s)){
            System.out.println("stuff");
            gui.addNewGameError("That name is already taken");
        }else if(s==null){
            gui.addNewGameError("Please enter name");
        }else if(!(s.length()>0 &&s.length()<10)){
            gui.addNewGameError("The name must be between 1 and 9 characters long");
        }
        else {
            saveUtility.createNewSave(s);
            loadGameEnter(s);
        }
    }

    /**Josh
     *
     * happens when the enter command is taken in the new game menu
     *
     * @param s
     */
    public void loadGameEnter(String s){
        saveUtility.loadSave(s);
        map=new Map();
        map.load(s);
    //    System.out.println("map loaded");
        player=new Player();
        player.load(s,map.getItems(),map.getRooms());
     //   System.out.println("player loaded");
        player.addObserver(gui);
        map.addObserver(gui);
        System.out.println("loaded observers");
        gui.showGameScene(saveUtility.getSavedGameText());
        createOptions();
    }

    /**Josh
     *
     * deletes the save
     *
     * @param s
     */
    public void deleteSave(String s) {
        saveUtility.deleteSave(s);
        loadGameButton();
    }

    public void exitGame(){
        player=new Player();
        map=new Map();
        saveUtility=new SaveUtility();
    }

//esc menu stuff
    public void saveButton(){
        System.out.println("saveButtom");

        player.save(saveUtility.getCurrentSaveName());
        map.save(saveUtility.getCurrentSaveName());
        saveUtility.saveGameText(gui.getTextFromTextPane());

        gui.showGameScene();
    }

//command stuff

    public void ready(){
        createOptions();
    }

    /**Josh
     *
     * is used to parse the game to create the optoins for the player
     *
     */
    private void createOptions(){
        Pane optionsPane=gui.getOptionsPane();
        optionsPane.getChildren().clear();


        Button inventoryButton=new Button("access Inventory");
        inventoryButton.setId("option");
        inventoryButton.setOnAction(e->{accessInventory(); });
        optionsPane.getChildren().add(inventoryButton);

        if(player.isAttacking()){
            Button attack=new Button("attack monster");
            attack.setId("option");
            attack.setOnAction(e->attackMonster());
            optionsPane.getChildren().add(attack);

            Button inspectMonster=new Button("inspect monster");
            inspectMonster.setId("option");
            inspectMonster.setOnAction(e->inspectMonster());
            optionsPane.getChildren().add(inspectMonster);

            Button escape=new Button("escape");
            escape.setId("option");
            escape.setOnAction(e->escape());
            optionsPane.getChildren().add(escape);
        }else if(player.isExamining()){

        }else {
            //creates momement buttons
            for (String s : player.getCurrentRoom().getConnections().keySet()) {

                Button b = new Button("go through door " + s);
                b.setId("option");
                b.setOnAction(e -> goToRoom(s));
                optionsPane.getChildren().add(b);
            }

            if(player.getCurrentRoom().hasMonster()){
                Button attack=new Button("attack monster");
                attack.setId("option");
                attack.setOnAction(e->attackMonster());
                optionsPane.getChildren().add(attack);

                Button inspectMonster=new Button("inspect monster");
                inspectMonster.setId("option");
                inspectMonster.setOnAction(e->inspectMonster());
                optionsPane.getChildren().add(inspectMonster);
            }

            Button inspect=new Button("inspect room");
            inspect.setId("option");
            inspect.setOnAction(e->insepectRoom());
            optionsPane.getChildren().add(inspect);

        }

    }

    private void clearOptions(){
        gui.getOptionsPane().getChildren().clear();
    }

    private void inspectMonster(){
        System.out.println("inspect monster");
    }

    private void accessInventory(){
        Pane optionsPane=gui.getOptionsPane();
        optionsPane.getChildren().clear();
        ArrayList<Item> inv=player.getInventory();
        optionsPane.getChildren().clear();
        if(inv.size()==0){
            ready();
        }
        else{
            for (Item i : inv) {
                HBox temp = new HBox();
                temp.setId("itemHBox");

                Label text = new Label(i.getName());
                temp.setId("itemLabel");
                temp.getChildren().add(text);

                Button inspect = new Button("inspect");
                inspect.setOnAction(e -> insectItem(i));
                inspect.setId("itemButton");
                temp.getChildren().add(inspect);

                if (i instanceof Weapon) {
                    Button equip = new Button("equip");
                    equip.setId("itemButton");
                    equip.setOnAction(e -> equipWeapon(i.getName()));
                    temp.getChildren().add(equip);

                }
                if (i instanceof PuzzleItem) {
                    Button use = new Button("use");
                    use.setId("itemButton");
                    use.setOnAction(e -> usePuzzleItem(i.getName()));
                    temp.getChildren().add(use);
                }
                if (i instanceof FoodItem) {
                    Button consume = new Button("consume");
                    consume.setId("itemButton");
                    consume.setOnAction(e -> consume(i.getName()));
                    temp.getChildren().add(consume);
                }

                Button drop=new Button("drop");
                drop.setId("itemButton");
                drop.setOnAction(e->dropItem(i.getName()));
                temp.getChildren().add(drop);

                optionsPane.getChildren().add(temp);

            }
            Button close=new Button("close");
            close.setId("itemButton");
            close.setOnAction(e->ready());
            optionsPane.getChildren().add(close);
        }




    }

    private void closeMenu(){

    }

    /**Josh
     *
     * moves the player
     * must cover the cases that there is still a monster left on the floor the player is on
     * and if there is a puzzle in the rooom the player is going to access
     *
     * @param connectionName
     */
    private void goToRoom(String connectionName){
        clearOptions();
        Room room=map.getRoomById(player.getCurrentRoom().getConnections().get(connectionName));
        //makes sure there is not a monster on the floor that needs to be killed
        if(room.getFloor()>player.getCurrentRoom().getFloor()&&map.isMonsterOnFloor(player.getCurrentRoom().getFloor()))
            gui.addTextToTextPane("There is still a monster left on the floor to kill");
        else if(room.hasPuzzle()){}
        else
            player.goToRoom(room,connectionName);
    }

    private void insepectRoom(){
        player.getCurrentRoom().inspect();
        if(player.getCurrentRoom().getInventory().size()>0){
            loadRoomInventory();
        }
    }

    /**Josh
     *
     * creates the options pane list of items in the rooms inventory
     *
     */
    private void loadRoomInventory(){
        Pane optionsPane=gui.getOptionsPane();
        optionsPane.getChildren().clear();
        ArrayList<Item> inv=player.getCurrentRoom().getInventory();
        for(Item i:inv){
            HBox temp = new HBox();
            temp.setId("itemHBox");

            Label text = new Label(i.getName());
            temp.setId("itemLabel");
            temp.getChildren().add(text);

            Button inspect = new Button("inspect");
            inspect.setOnAction(e -> insectItem(i));
            inspect.setId("itemButton");
            temp.getChildren().add(inspect);

            Button drop=new Button("pickup");
            drop.setId("itemButton");
            drop.setOnAction(e->pickUpItem(i.getName()));
            temp.getChildren().add(drop);

            optionsPane.getChildren().add(temp);
        }
        Button close=new Button("close");
        close.setId("itemButton");
        close.setOnAction(e->ready());
        optionsPane.getChildren().add(close);
    }

    private void insectItem(Item i){
        System.out.println("insepce item");
    }

    /**Josh
     *
     * handles the player picking up an item from a room
     *
     * @param itemName
     */
    private void pickUpItem(String itemName){
        player.addItemToInventory(player.getCurrentRoom().dropItem(itemName));
        loadRoomInventory();

    }

    private void consume(String itemName){

    }

    private void equipWeapon(String weaponName){
        player.equipWeapon((Weapon)player.getItemFromInventory(weaponName));
    }

    private void usePuzzleItem(String itemName){

    }

    private void dropItem(String itemName){
        player.getCurrentRoom().getInventory().add(player.getItemFromInventory(itemName));
        accessInventory();
    }



    private void observeMonster(){

    }

    private void attackMonster(){
        player.attackMonster(player.getCurrentRoom().getMonster());
        if(player.getCurrentRoom().hasMonster()){
            player.getCurrentRoom().getMonster().attackPlayer(player);
        }
    }

    private void escape(){

    }

    private void examinePuzzle(){

    }

    private void solvePuzzle(){

    }















}
