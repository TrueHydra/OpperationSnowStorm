package base.ModelStuff.Storage;

import base.ModelStuff.Storage.Items.Item;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class Room {

    private String name,description,id;
    private Monster monster;
    private Puzzle puzzle;
    private HashMap<String,Room> connections;
    private boolean hasBeenVisited;
    private HashMap<String,Item> inventory;

    public Room(){}

    public Room(String name,String description,Monster monster,Puzzle puzzle,HashMap<String,Room> connections,boolean hasBeenVisited,String id, HashMap<String,Item> inventory){
        this.name=name;
        this.description=description;
        this.monster=monster;
        this.puzzle=puzzle;
        this.connections=connections;
        this.hasBeenVisited=hasBeenVisited;
        this.id=id;
        this.inventory=inventory;
    }

    /**
     * creates a room from the saved string
     *
     * @param saveString
     */
    public Room(String saveString){
        System.out.println("needs done:: room creat from save string");

    }

//get methods

    /**Josh
     *
     * returns the room name
     *
     * @return
     */
    public String getName() {
        return name;
    }

    /**Josh
     *
     * returns the room description
     */
    public String getDescription() {
        return description;
    }

    /**Josh
     *
     * returns the puzzle in the room
     *
     * @return
     */
    public Puzzle getPuzzle() {
        return puzzle;
    }

    /**Josh
     *
     * returns the monster in the room
     *
     * @return
     */
    public Monster getMonster() {
        return monster;
    }

    /**Josh
     *
     * returns hasBeenVisited
     *
     * @return
     */
    public boolean hasBeenVisited() {
        return hasBeenVisited;
    }

    /**Josh
     *
     * returns the inventory
     *
     * @return
     */
    public HashMap<String, Item> getInventory() {
        return inventory;
    }

    /**Josh
     *
     * returns the rooms inventory
     *
     * @return
     */
    public HashMap<String, Room> getConnections() {
        return connections;
    }

    /**
     *
     * returns if the room has a monster
     *
     * @return
     */
    public boolean hasMonster(){
        System.out.println("hasMonster()");
        return false;
    }

    /**
     *
     * returns if the room has a puzzle
     *
     * @return
     */
    public boolean hasPuzzle(){
        System.out.println("hasPuzzle()");
        return false;
    }

    /**Josh
     *
     * when player visits room sets has been visited true
     *
     */
    public void visit(){
        hasBeenVisited=true;
    }

}
