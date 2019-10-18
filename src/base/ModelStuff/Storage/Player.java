/**
 *
 * player text save format:
 *
 * health,weapon(itemId),inventory(itemId)(seperated by '!'),currentRoom(roomId),is attacking,isexaming a puzzle6
 *
 *
 */

package base.ModelStuff.Storage;

import base.ModelStuff.Storage.Items.Item;
import base.ModelStuff.Storage.Items.Weapon;

import java.util.*;


public class Player extends Observable implements Saveable {

    private int health;
    private Room currentRoom;
    private HashMap<String,Item> inventory;
    private Weapon weapon;

    public Player() {
        super();
    }

    public Player(int health, Room currentRoom, HashMap<String,Item> inventory, Weapon weapon) {
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
    public HashMap<String,Item> getInventory() {
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




    @Override
    public void save(String saveName) {

    }

    @Override
    public void load(String saveName) {

    }


}