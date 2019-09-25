package base.ModelStuff;

import base.ModelStuff.Storage.Monster;
import base.ModelStuff.Storage.Player;
import base.ModelStuff.Storage.Puzzle;
import base.ModelStuff.Storage.Room;
import base.Printer;

import java.util.List;

public class Model {

    private Printer printer;
    private Player player;
    private Room currentRoom;
    private List<Room> rooms;
    private List<Puzzle> puzzles;
    private List<Monster> monsters;

    public Model(Printer printer){
        this.printer=printer;
        setupNew();
    }

    /**
     * This sets up a new game in the starting positions
     */
    public void setupNew(){
        player=new Player();

    }

    /**
     *this method takes in a command and responds to it
     */
    public void command(String command){
        String[] split=command.split(" ");
        if(split[0].equals("START")){
            startCommand();
        }
        else if(split[0].equals("QUIT")){
            endCommand();
        }
        else  if(split[0].equals("SAVE")){
            saveCommand();
        }
        else if(split[0].equals("LOAD")){
            loadGame();
        }
        else if(split[0].equals("HELP")){
            helpCommand();
        }
        else if(split[0].equals("HEALTH")){
            healthCommand();
        }
        else if(split[0].equals("INVENTORY")){
            inventoryCommand();
        }
        else if(split[0].equals("CONSUME")){
            consumeCommand(command.substring(split[0].length()+1,command.length()));
        }
        else if(split[0].equals("USE")){
            useCommand(command.substring(split[0].length()+1,command.length()));
        }
        else if(split[0].equals("EQUIP ")){
            equipCommand(command.substring(split[0].length()+1,command.length()));
        }
        else if(split[0].equals("PICK")){
            pickUPCommand(command.substring(split[0].length()+1,command.length()));
        }
        else if(split[0].equals("DROP")){
            dropCommand(command.substring(split[0].length()+1,command.length()));
        }
        else if(split[0].equals("OBSERVE")){
            observeCommand();
        }
        else if(split[0].equals("ATTACK")){
            attackCommand();
        }
        else if(split[0].equals("ESCAPE")){
            escapeCommand();
        }
        else if(split[0].equals("EXAMINE")){
            examineCommand();
        }
        else if(split[0].equals("SOLVE")){
            solveCommand(command.substring(split[0].length()+1));
        }
        else if(split[0].equals("ENTER")){
            enterRoomCommand(command.substring(split[0].length()+1,command.length()));
        }
        else if(split[0].equals("LOOK")){
            lookCommand();
        }
    }


//command methods
    /**
     * this handels the start command
     *
     * creates new game
     *
     * The player must be able to begin a new game by using the START command at the title screen. The system will then create a new game and start the player in Room 1.1.
     */
    private void startCommand(){
        System.out.println("needs done");
    }

    /**
     * this handles the Quit command
     *
     * ends game and closes window
     *
     * The player must be able to leave the game via the QUIT command. The system will then end the game and close the program.
     */
    private void endCommand(){
        System.out.println("neeeds doen");
    }

    /**
     * this handles the saveing aspect of the game
     *
     *  The game will save the player’s progression - It will written into a text file.
     */
    private void saveCommand(){
        System.out.println("need done");
    }

    /**
     * handles the loading of a new game
     *
     * The game will load to the player’s previous save.
     */
    private void loadGame(){
        System.out.println("needs done:: load game");
    }

    /**
     * handles the help command
     *
     * The player must be able to request a list of all game commands by using the HELP command. Upon entering the command, which the player may do at any point in the game, the system shall display a list of all commands along witha short description for each command that explains its function.
     */
    private void helpCommand(){
        System.out.println("needs done ::help command");
    }

    /**
     * The player must be able to view their current health with the HEALTH command. The system will then display the player’s current health.
     *
     * The player begins the game with 100 health. Performing some actions may increase the player’s health while others may lower it. Once the player reaches 0 health (or lower), the player dies, their items are added to the room they died in, and they are respawned in the main room of the floor that they died on, with 10 health and an empty inventory.
     */
    private void healthCommand(){
        System.out.println("needs done:: health command");
    }

    /**
     *The player must be able to view their currently held and equipped items with the INVENTORY command. The system will then display a list of all the items and their descriptions in the player’s inventory as well as which item the player currently has equipped. The player should be able to use this displayed list when using the USE, CONSUME, EQUIP, and DROP commands.
     *
     */
    private void inventoryCommand(){
        System.out.println("needs done:: invn command");
    }

    /**
     * The player must be able to eat food items by using the CONSUME command followed by the item name, where the name of the food item is in the player’s inventory. This destroys the food item. The system then must remove the food item from the player’s inventory and increases the player’s health by the food item’s saturation.
     *
     * must catch if the food is not food
     * @param foodName
     */
    private void consumeCommand(String foodName){
        System.out.println("needs done:: consume :"+ foodName);
    }

    /**
     * The player must be able to use puzzle items by using the USE command followed by the item name, where the name of the puzzle item is in the player’s inventory. The system will then promptthe user for a number of arguments such that each argument is on its own line, as in      Arg A: user-input      Arg B: user-input      Arg C: user-input
     *
     * Once the user provides all of the arguments, the system will then display the result of the puzzle item.
     * @param puzzleItemName
     */
    private void useCommand(String puzzleItemName){
        System.out.println("needs done:: use command");
    }

    /**
     *The player must be able to select a weapon item in their inventory by issuing the EQUIP command followed by the item name, where the name of the weapon item is in the player’s inventory. The player starts the game with no equipped weapon. If the player enters the EQUIP command while they already have an item equipped, the newly equipped item will replace the previous item as the currently equipped item
     *
     * must catch if armorName is not the name of armor
     *
     * @param armorName
     */
    private void equipCommand(String armorName){
        System.out.println("needs done:: equip command");
    }

    /**
     *The player must be able to add visible items to their inventory using the PICK UP command followed by the item’s name. The system will then add the item to the player’s inventory.
     *
     * needs to catch if itemname is not an item name
     *
     * @param itemName
     */
    private void pickUPCommand(String itemName){
        System.out.println("needs done:: pick up");
    }

    /**
     * The player must be able to remove an item from their inventory using the DROP command followed by the item’s name. The system will then remove the item from the player’s inventory and add the item to the room the player is currently in. If the LOOK feature is used, the droppeditem will be displayed as ‘The is (a) [item name] on the floor.’ The item can be acquired via the PICK UP command as normal and will remain in the room until the player does so.
     *
     * must catch if item name is not an item
     *
     * @param itemName
     */
    private void dropCommand(String itemName){
        System.out.println("needs done:: drop command");
    }

    /**
     * The player must be able to get a description of a visible monster using the OBSERVE command. The system will
     * Monster is visible then display a description of the monster, including its current health and hostility.
     *
     */
    private void observeCommand(){
        System.out.println("needs done:: observe command");
    }

    /**
     * The player must be able to attack a visible monster using the ATTACK command. The system will then generate an attack based on the player’s equipped items or base attack. After an ATTACK command has been issued, the player will only be able to use the OBSERVE, ATTACK, ESCAPE, EQUIP, CONSUME, HELP, INVENTORY, CLOSE, and QUIT commands. For every ATTACK command the player makes after the initial ATTACK command, the monster will make an attack.
     */
    private void attackCommand(){
        System.out.println("needs done:: attack command");
    }

    /**
     * The player must be able to escape a visible monster attacking them using the ESCAPE command. The system will then allow the player to complete commands other than OBSERVE, ATTACK, ESCAPE, EQUIP, CONSUME, HELP, INVENTORY, CLOSE, and QUIT commands. For every command the player uses after the ESCAPE command, the monster will make an attack.
     */
    private void escapeCommand(){
        System.out.println("needs done: escape command");
    }

    /**
     * The player must be able to get a description of the puzzle by using the EXAMINE command. The system will The system will then prompt the player with the puzzle’s text (see table in 3.1.3).
     *
     * The player may then only use the SOLVE, CLOSE, INVENTORY, and USE commands.
     */
    private void examineCommand(){
        System.out.println("needs done: examine command");
    }

    /**
     * The player must be able to solve puzzles by using the SOLVE command. The user will then be prompted for a password. Upon entering the correct password, the system will mark the puzzle as solved and the user will be shown the puzzles response.
     *
     * If the password is incorrect, the user will be notified and the interaction will be over. The player can attempt to solve each puzzle as many times as they like.
     *
     * @param answer
     */
    private void solveCommand(String answer){
        System.out.println("needs done:: solve command");

    }

    /**
     *
     * The player must be able to enter an accessible room by using the ENTER command. The player should specify which room they wish to enter by also naming the accessible room following the command. The system must then put the player in the entered room and will display the aforementioned room’s description.
     *
     * If the player selects the elevator, the system will prompt the user about which floor the user wishes to go to with a list. Only unlocked floors will be displayed. Completing that floors puzzle unlocks the floor above. If the player changes floors,
     *
     * @param roomName
     */
    private void enterRoomCommand(String roomName){
        System.out.println("needs done :: enter room command");
    }

    /**
     *The player must be able to examine the room they are currently in using the LOOK command. The system must then display a description of all visible monsters, puzzles, items, and entrances to other rooms.
     *
     */
    private void lookCommand(){
        System.out.println("needs done:: look command");
    }


    /**
     * for testing purposes
     * @param args
     */
    public static void main(String[] args) {
        Model m=new Model(new Printer());
        m.command("CONSUME foodName");
    }




}
