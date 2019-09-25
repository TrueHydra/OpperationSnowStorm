package base.ModelStuff.Storage;

import base.ModelStuff.Storage.Items.Item;
import base.ModelStuff.Storage.Items.Weapon;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;


public class Player {

    private int health;
    private Room currentRoom;
    private LinkedList<Item> inventory;
    private Weapon weapon;

    public Player(){
        health=100;
        inventory=new LinkedList<>();
    }

//public stuff

    /**
     * returns the String of the player's inventory
     * @return
     */
    public String getInventory(){
        return "not done yet";
    }

    /**
     * adds item to the players inventory
     * @param i
     */
    public void addItemToInventory(Item i){
        inventory.add(i);
    }

    /**
     * removes item from inventory and returns that item
     *
     *
     * @param itemName
     * @return
     */
    public Item removeItemFromInventory(String itemName){
        for(Item i:inventory){
            if(itemName.equals(i.getDescription())){
                Item hold=i;
                inventory.remove(i);
                return hold;
            }
        }
        return null;
    }

    /**
     * extanges the current weapon for the new one
     *
     * the weapon name MUST be of a weapon aka handle int controller
     * @param weaponName
     */
    public void equipWeapon(String weaponName){
        for(Item i:inventory){
            if(i.getDescription().equals(weaponName)){
                inventory.add(weapon);
                weapon=(Weapon)i;
            }
        }
    }

    /**
     *  you idiot
     *
     * @return
     */
    public int getHealth(){
        return health;
    }





}
