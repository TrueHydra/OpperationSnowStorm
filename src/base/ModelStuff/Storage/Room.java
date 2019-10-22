/**
 *
 * saved as
 *
 * id,name,desc,Monster(id),puzzle(id),Connections(main redux ! second ; ... holds name of connection with the room it connects to),hasBeenVisited,inventory(saved same as in player)
 *
 */
package base.ModelStuff.Storage;

import base.ModelStuff.Storage.Items.Item;

import java.io.File;
import java.util.*;

public class Room extends Observable{

    private int id;
    private String name,description;
    private Monster monster;
    private Puzzle puzzle;
    private HashMap<String,Integer> connections;
    private boolean hasBeenVisited;
    private ArrayList<Item> inventory;

    public Room(){
        super();
        id=0;
        name="name";
        description="desc";
        monster=new Monster();
        puzzle=new Puzzle();
        connections=new HashMap<>();
        hasBeenVisited=false;
        inventory=new ArrayList<>();
    }

    public Room(int i){
        id=i;
    }

    public Room(int id,String name,String description,Monster monster,Puzzle puzzle,HashMap<String,Integer> connections,boolean hasBeenVisited, ArrayList<Item> inventory){
        this.name=name;
        this.description=description;
        this.monster=monster;
        this.puzzle=puzzle;
        this.connections=connections;
        this.hasBeenVisited=hasBeenVisited;
        this.id=id;
        this.inventory=inventory;
    }


//get methods


    public int getId() {
        return id;
    }

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
    public ArrayList<Item> getInventory() {
        return inventory;
    }

    /**Josh
     *
     * returns the rooms inventory
     *
     * @return
     */
    public HashMap<String,Integer> getConnections() {
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

//save/load stuff

    /**Josh
     *
     * creates a room from a string
     *
     * @param str
     * @return
     */
    public static Room createRoomFromString(String str,HashMap<Integer,Monster> monsters,HashMap<Integer,Item> items,HashMap<Integer,Puzzle> puzzles){
        String[] split=str.split(",");
        if(split.length==7)
            return new Room(Integer.parseInt(split[0]),split[1],split[2],monsters.get(Integer.parseInt(split[3])),puzzles.get(Integer.parseInt(split[4])),createConnectionsFromString(split[5]),Boolean.parseBoolean(split[6]),new ArrayList<>());

       return new Room(Integer.parseInt(split[0]),split[1],split[2],monsters.get(Integer.parseInt(split[3])),puzzles.get(Integer.parseInt(split[4])),createConnectionsFromString(split[5]),Boolean.parseBoolean(split[6]),Item.getInventoryFromStringAndItemsList(split[7],items));
    }

    private static HashMap<String,Integer> createConnectionsFromString(String str){
        HashMap<String,Integer> temp=new HashMap<>();
        if(str.length()==0)
            return temp;
        String[] s=str.split("!");
        for(String st:s){
            String[] ss=st.split(";");
            temp.put(ss[0],Integer.parseInt(ss[1]));
        }
        return temp;
    }


    private String connectionsString(){
        if(connections.size()==0){
            return "";
        }
        String rtn="";
        for(String i:connections.keySet()){
            rtn=rtn+"!"+i+";"+connections.get(i);
        }
        return rtn.substring(1);
    }

    public String toString(){
        return id+","+name+","+description+","+monster.getId()+","+puzzle.getId()+","+connectionsString()+","+hasBeenVisited+","+Item.getInventoryString(inventory);
    }

    //makes the room string
    public static void main(String[] args){
        HashMap<String, Room> con=new HashMap<>();
        con.put("door to 1",new Room(1));
        con.put("door to 2",new Room(2));
        ArrayList<Item> inv=new ArrayList<>();
        //inv.add(new Item(1));
        //inv.add(new Item(2));
  //      Room r=new Room(3,"test 3","test room 3:has Monster",new Monster(1),new Puzzle(0),con,false,inv);
       // System.out.println(r);
    }




}
