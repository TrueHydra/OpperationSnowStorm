package base.ModelStuff.Storage;

import base.ModelStuff.Storage.Items.Item;

import java.util.ArrayList;
import java.util.HashSet;

public class Room {

    private String name,description,id;
    private Monster monster;
    private Puzzle puzzle;
    private HashSet<Room> connections;
    private boolean hasBeenVisited;
    private ArrayList<Item> inventory;

    public Room(){}

    public Room(String name,String description,Monster monster,Puzzle puzzle,HashSet<Room> connections,boolean hasBeenVisited,String id, ArrayList<Item> inventory){
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
    public String getName() {
        return name;
    }

    public boolean isHasBeenVisited() {
        return hasBeenVisited;
    }

    public HashSet<Room> getConnections() {
        return connections;
    }

    public Monster getMonster() {
        return monster;
    }

    public Puzzle getPuzzle() {
        return puzzle;
    }

    public String getDescription() {
        return description;
    }

    public String getId() {
        return id;
    }

    public ArrayList<Item> getInventory(){
        return inventory;
    }

//public methodos

    /**
     *
     * checks if the room has a monster
     *
     * @return
     */
    public boolean hasMonster(){
        System.out.println("needs done hasMonster");
        return false;
    }

    /**
     * checks if room has puzzle
     *
     * @return
     */
    public boolean hasPuzzle(){
        System.out.println("needs doen hasPuzzle");
        return false;
    }
}
