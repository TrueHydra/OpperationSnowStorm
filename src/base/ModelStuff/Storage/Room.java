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
    private HashMap<String,Room> connections;
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

    public Room(int id,String name,String description,Monster monster,Puzzle puzzle,HashMap<String,Room> connections,boolean hasBeenVisited, ArrayList<Item> inventory){
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
    public HashMap<String,Room> getConnections() {
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
    public static Room createRoomFromString(String str){
        String[] s=str.split(",");
    //    System.out.println(str);
     //   System.out.println(Arrays.toString(s));
        return new Room(Integer.parseInt(s[0]),s[1],s[2],new Monster(Integer.parseInt(s[3])),new Puzzle(Integer.parseInt(s[4])),createConnectionsFromString(s[5]),Boolean.parseBoolean(s[6]),Item.getInventoryFromString(s[7]));
    }

    private static HashMap<String,Room> createConnectionsFromString(String str){
        HashMap<String,Room> temp=new HashMap<>();
        if(str.length()==0)
            return temp;
        String[] s=str.split("!");
        for(String st:s){
            String[] ss=st.split(";");
            temp.put(ss[0],new Room(Integer.parseInt(ss[1])));
        }
        return temp;
    }


    private String connectionsString(){
        if(connections.size()==0){
            return "";
        }
        String rtn="";
        for(String i:connections.keySet()){
            rtn=rtn+"!"+i+";"+connections.get(i).id;
        }
        return rtn.substring(1);
    }

    public String toString(){
        return id+","+name+","+description+","+monster.getId()+","+puzzle.getId()+","+connectionsString()+","+hasBeenVisited+","+Item.getInventoryString(inventory)+" ,";
    }


}
