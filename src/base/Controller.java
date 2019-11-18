package base;

import base.ModelStuff.Storage.Items.FoodItem;
import base.ModelStuff.Storage.Items.Item;
import base.ModelStuff.Storage.Items.PuzzleItem;
import base.ModelStuff.Storage.Items.PuzzleItems.BianaryTranslator;
import base.ModelStuff.Storage.Items.PuzzleItems.PuzzleBook;
import base.ModelStuff.Storage.Items.Weapon;
import base.ModelStuff.Storage.Map;
import base.ModelStuff.Storage.Player;
import base.ModelStuff.Storage.Puzzle;
import base.ModelStuff.Storage.Room;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.*;
import java.util.regex.Matcher;
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
        gui.startUpAnimation();

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
            //System.out.println("stuff");
            gui.addNewGameError("That name is already taken");
        }else if(s==null){
            gui.addNewGameError("Please enter name");
        }else if(!(s.length()>0 &&s.length()<10)){
            gui.addNewGameError("The name must be between 1 and 9 characters long");
        }else if(checkName(s)){
            gui.addNewGameError("The name must only contain characters");

        }
        else {
            saveUtility.createNewSave(s);
            loadGameEnter(s);
        }
    }

    private boolean checkName(String s){
        Pattern p= Pattern.compile("[^a-zA-Z]");
        Matcher m=p.matcher(s);
        return m.find();
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
        }
        else if(player.isExamining()){
            System.out.println("test");
            HBox temp=new HBox();
            temp.setId("puzzleHBox");
            optionsPane.getChildren().add(temp);

            TextField puzzleSolution=new TextField();
            puzzleSolution.setId("puzzleSolution");
            temp.getChildren().add(puzzleSolution);

            Button enter=new Button("enter");
            enter.setId("option");
            enter.setOnAction(e->solvePuzzle(puzzleSolution.getText()));
            temp.getChildren().add(enter);

            Button examine=new Button("Examine");
            examine.setId("option");
            examine.setOnAction(e->examinePuzzle());
            optionsPane.getChildren().add(examine);

            Button close=new Button("close");
            close.setId("option");
            close.setOnAction(e->puzzleClose());
            optionsPane.getChildren().add(close);

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

    private void puzzleClose() {
        player.stopExamining();
        ready();
    }

    private void clearOptions(){
        gui.getOptionsPane().getChildren().clear();
    }

    private void inspectMonster(){
        player.getCurrentRoom().getMonster().inspect();
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
                if (i instanceof PuzzleBook) {
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
                if(i instanceof BianaryTranslator){
                    Button use = new Button("use");
                    use.setId("itemButton");
                    use.setOnAction(e -> useBinaryTrans((BianaryTranslator)i));
                    temp.getChildren().add(use);

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
        if(room.getFloor()>player.getCurrentRoom().getFloor()&&map.isMonsterOnFloor(player.getCurrentRoom().getFloor())) {
            gui.addTextToTextPane("There is still a monster left on the floor to kill");
            ready();
        }
        else if(room.hasPuzzle()&&!room.getPuzzle().hasBeenSolved()){
            player.encounterPuzzle();
            gui.addTextToTextPane("The player must solve the puzzle to enter the room.");

            room.getPuzzle().examine();
            doorNameForPuzzleRoom =connectionName;
        }
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
        System.out.println("test");
        Pane optionsPane=gui.getOptionsPane();
        optionsPane.getChildren().clear();
        ArrayList<Item> inv=player.getCurrentRoom().getInventory();
        for(Item i:inv){
           // System.out.println(i);
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
        i.inspect();
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
        player.consume(itemName);
        accessInventory();
    }

    private void equipWeapon(String weaponName){
        player.equipWeapon((Weapon)player.getItemFromInventory(weaponName));
        accessInventory();
    }

    private void usePuzzleItem(String itemName){
        PuzzleItem p=(PuzzleItem)player.getItemFromInventory(itemName);
        player.addItemToInventoryQuiet(p);
        p.use(new String[]{});
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

    /**Josh
     *
     * when the player wants to escape from the monster
     *
     */
    private void escape(){
        player.escapeFromAttacking();
        player.getCurrentRoom().getMonster().attackPlayer(player);
    }

    String doorNameForPuzzleRoom ="";
    private void examinePuzzle(){
        map.getRooms().get(player.getCurrentRoom().getConnections().get(doorNameForPuzzleRoom)).getPuzzle().examine();
    }

    /**Josh
     *
     * is used when the enter button in the puzzle
     *
     * @param solution
     */
    private void solvePuzzle(String solution){
        if(map.getRooms().get(player.getCurrentRoom().getConnections().get(doorNameForPuzzleRoom)).getPuzzle().solve(solution)){
         goToRoom(doorNameForPuzzleRoom);
         player.stopExamining();
        }
        ready();
    }

    private void useBinaryTrans(BianaryTranslator bt){
        Pane optionsPane=gui.getOptionsPane();
        optionsPane.getChildren().clear();

        Text t=new Text("Please enter 5 digit binary string to be translated");
        t.setId("optionText");
        optionsPane.getChildren().add(t);

        TextField tf=new TextField();
        tf.setId("optionsTextField");
        optionsPane.getChildren().add(tf);

        Text res=new Text();
        res.setId("optionsText");
        optionsPane.getChildren().add(res);

        Button enter=new Button("enter");
        enter.setId("option");
        enter.setOnAction(e->{
            String temp=tf.getText();
            if(checkEnsert(temp)){
                res.setText(bt.use(new String[]{temp}));
            }else
                res.setText("not valid");
        });
        optionsPane.getChildren().add(enter);

        Button back=new Button("back");
        back.setId("option");
        back.setOnAction(e->accessInventory());
        optionsPane.getChildren().add(back);
    }

    private boolean checkEnsert(String temp){
       // System.out.println(temp+"  "+temp.length());
        if (temp.length()!=5)
            return false;
        for(int i=0;i<temp.length();i++){
            //System.out.println(temp.charAt(i));
            if(temp.charAt(i)=='0'||temp.charAt(i)=='1'){}
            else {
            //    System.out.println("test");
                return false;
            }

        }
        return true;
    }
    
}
