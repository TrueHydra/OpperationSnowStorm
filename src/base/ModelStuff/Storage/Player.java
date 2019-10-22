/**
 *
 * player text save format:
 *
 * health,weapon(itemId),inventory(itemId)(seperated by '!'),currentRoom(roomId),is attacking,isexaming a puzzle6
 *
 *
 */

package base.ModelStuff.Storage;

import base.ModelStuff.Storage.Items.FoodItem;
import base.ModelStuff.Storage.Items.Item;
import base.ModelStuff.Storage.Items.Weapon;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;


public class Player extends Observable {

    private int health;
    private Room currentRoom;
    private ArrayList<Item> inventory;
    private Weapon weapon;
    private boolean isAttacking,isExamining;

    public Player() {
        super();
        health=100;
        currentRoom=new Room();
        inventory=new ArrayList<>();
        weapon=new Weapon();
        isExamining=false;
        isAttacking=false;
    }

    public Player(int health, Room currentRoom, ArrayList<Item> inventory, Weapon weapon) {
        super();
        this.health = health;
        this.currentRoom = currentRoom;
        this.inventory = inventory;
        this.weapon = weapon;
    }

    /**
     * creates player from save string
     *
     * @param saveString
     */
    public Player(String saveString) {
        System.out.println("needs done player from save stirng");
    }

//public stuff

    /**adds an item to inventory
     *
     *
     *
     * @param item
     */
    public void addItemToInventory(Item item){
        System.out.println("needs done add To Inventory");
        inventory.add(item);
    }


    /**takes in monster an applies damage to it
     *
     * @param monster
     */
    public void attackMonster(Monster monster){
        System.out.println("needs done attack monster");
    }

    /**takes in the damage from the monster
     *
     *
     * @param damage
     */
    public void takeDamage(int damage){
        System.out.println("needs done player take damage");
    }

    /**
     * takes in string and returns item from the player inventory
     * if item not in inventory returns null
     * does not remove item
     *
     * @param itemName
     * @return
     */
    public Item getItemFromInventory(String itemName){
        System.out.println("needs done get item from inventory");
        return null;
    }

    /**
     * takes in string and returns item from the player inventory
     * if item not in inventory returns null
     * removes item
     *
     * @param itemName
     * @return
     */
    public Item removeItemFromInventory(String itemName){
        System.out.println("needs done remove item from inventory");
        return null;
    }

    /**
     * takes in weapon and extanges it for the currently equipt one then returns the prior equipt weapon
     *
     * @param weapon
     * @return
     */
    public Weapon equipWeapon(Weapon weapon){
        System.out.println("equipWeapon");
         return null;
    }

    /**Josh
     *
     * gets health
     *
     * @return
     */
    public int getHealth(){
        return health;
    }

    /**Josh
     *
     * returns the equiped weapon
     *
     * @return
     */
    public Weapon getWeapon(){
        return weapon;
    }

    /**Josh
     *
     * returns the room the player occupies
     *
     * @return
     */
    public Room getCurrentRoom() {
        return currentRoom;
    }

    /**Josh
     *
     * returns the players inventory
     *
     * @return
     */
    public ArrayList<Item> getInventory() {
        return inventory;
    }

    /**Josh
     *
     * changes the room the player is in
     *
     *
     * @param currentRoom
     */
    public void setCurrentRoom(Room currentRoom) {
        this.currentRoom = currentRoom;
    }

    /**
     * checks if item is in players inventory
     *
     * @param itemName
     * @return
     */
    public boolean hasItem(String itemName){
        System.out.println("hasItem()");
        return false;
    }

//saveing and loading stuff


    /**Josh
     *
     * saves the player in the saves folder under the saveName
     *
     * @param saveName
     */
    public void save(String saveName) {
        File file=new File("saves\\"+saveName+"\\Player.txt");
        try {
            FileWriter fr = new FileWriter(file);
            fr.write(toString());
            fr.close();
        }catch (IOException e){
            System.out.println(e);
        }
    }


    public void load(String saveName,HashMap<Integer,Item> items,HashMap<Integer,Room> rooms) {

        File f=new File("saves\\"+saveName+"\\Player.txt");
        try{
            Scanner s=new Scanner(f);
            String[] stuff=s.nextLine().split(",");
            //System.out.println(Arrays.toString(stuff));
            health=Integer.parseInt(stuff[0]);
            if(stuff[1].equals(""))
                weapon=new Weapon();
            else
                weapon=(Weapon)items.get(Integer.parseInt(stuff[1]));
            if(stuff[2].equals(""))
                inventory=new ArrayList<>();
            else
                inventory=Item.getInventoryFromStringAndItemsList(stuff[2],items);
            //currentRoom=Room.getFromId(Integer.parseInt(stuff[3]));
            currentRoom=rooms.get(Integer.parseInt(stuff[3]));
            isAttacking=Boolean.parseBoolean(stuff[4]);
            isExamining=Boolean.parseBoolean(stuff[5]);
        }catch (FileNotFoundException e){
            System.out.println(e);
        }


    }

    public String toString(){
        return health+","+weapon.getSaveString()+","+Item.getInventoryString(inventory)+","+currentRoom.getId()+","+isAttacking+","+isExamining;
    }

    public static void main(String[] args) {
        Player p =new Player();
        p.addItemToInventory(new Item());
        p.addItemToInventory(new Weapon(0,"weapon ",1,2));
        p.addItemToInventory(new FoodItem(1,"food adjoasjdao s",4));
        System.out.println(p.inventory.size());
        //p.load("as");
        System.out.println(p);
    }


}