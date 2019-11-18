/**
 *
 * saved as item in player/rooms id
 *
 * saved in items file type(f,w,p),name,stuff from specific item type
 *
 *
 */

package base.ModelStuff.Storage.Items;



import base.ModelStuff.Storage.Items.PuzzleItems.BianaryTranslator;
import base.ModelStuff.Storage.Items.PuzzleItems.PuzzleBook;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Observable;

public class Item extends Observable{

    private int id;
    private String name;

    public Item(){
        super();
        id=0;
        name="";
    }

    public Item(int id){
        this.id=id;
    }

    public Item(int id,String name){
        this.id=id;
        this.name=name;
    }

    public int getId() {
        return id;
    }

    private void setId(int id){
        this.id=id;
    }

    private void setName(String name) {
        this.name = name;
    }

    /**Josh
     *
     * returns item name
     *
     * @return
     */
    public String getName(){
        return name;
    }

//save/load stuff

    /**
     * returns the string that the item should be saved as
     *
     * @return
     */
    public String getSaveString(){
        return "i,"+id+","+name;
    }

//static methods

    /**Josh
     *
     * returns an item from the string
     *
     * @param saveString
     * @return
     */
    public static Item getFromString(String saveString){

        System.out.println("needs work puzzle item");
        String[] split=saveString.split(",");
        if(split.length==1)
            return new Item(Integer.parseInt(split[0]));
        if(split[0].trim().equals("i"))
            return new Item(Integer.parseInt(split[1]),split[2]);
        if(split[0].trim().equals("w"))
            return Weapon.getFromString(saveString);
        if(split[0].trim().equals("f"))
            return FoodItem.getFromString(saveString);
        if(split[0].trim().equals("pb")){
            return PuzzleBook.getFromString(saveString);
        }
        if(split[0].trim().equals("bt")) {
            return BianaryTranslator.getBTFromString(saveString);
        }
        return new Item();
    }

    public static String getInventoryString(ArrayList<Item> inventory){
        String rtn="";
        if(inventory.size()==0)
            return rtn;
        for(Item i:inventory){
            rtn=rtn+";"+i.id;
        }
        rtn=rtn.substring(1);
        return rtn;
    }

    /**Josh
     *
     * returns the inventory based on a stirng and the items of the game
     *
     * @param savedListString
     * @param itemList
     * @return
     */
    public static ArrayList<Item> getInventoryFromStringAndItemsList(String savedListString,HashMap<Integer,Item> itemList){
        //System.out.println("test");
        ArrayList<Item> items=new ArrayList<>();

        if(savedListString.equals(""))
            return items;

        String[] split=savedListString.split(";");
        for(String s:split){
            if(itemList.get(Integer.parseInt(s))!=null)
                items.add(itemList.get(Integer.parseInt(s)));
        }
        return items;
    }

    @Override
    public String toString() {
        if(name==null){
            return ""+id;
        }
        return id+","+name;
    }

    public String inspect() {
        setChanged();
        notifyObservers(name);
        return name;
    }

    public void pickup() {
        setChanged();
        notifyObservers("Player has picked up "+name+".");
    }

    public void equip(){
        setChanged();
        notifyObservers("Player has equipped "+name+".");
    }
}
