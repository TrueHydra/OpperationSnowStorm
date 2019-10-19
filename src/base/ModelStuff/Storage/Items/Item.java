/**
 *
 * saved as item type(f,w,p);id;name;rest is dependent on type
 *
 */

package base.ModelStuff.Storage.Items;

import base.ModelStuff.Storage.Saveable;
import com.sun.corba.se.spi.ior.IORTemplate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Observable;

public class Item extends Observable{

    private int id;
    private String name;

    public Item(){
        super();
        id=0;
        name="test";
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
        return "i;"+id+";"+name;
    }

    /**Josh
     *
     * returns an item from the string
     *
     * @param saveString
     * @return
     */
    public static Item getFromString(String saveString){

        String[] split=saveString.split(";");
        return new Item(Integer.parseInt(split[1]),split[2]);
    }

    public static String getInventoryString(ArrayList<Item> inventory){
        String rtn="";
        if(inventory.size()==0)
            return rtn;
        for(Item i:inventory){
            rtn=rtn+"!"+i.getSaveString();
        }
        rtn=rtn.substring(1);
        return rtn;
    }


    /**Josh
     *
     * creates the hasmap of items saved for the player
     *
     * @param saveListString
     * @return
     */
    public static ArrayList<Item> getInventoryFromString(String saveListString){
        saveListString=saveListString.trim();
        ArrayList<Item> rtn=new ArrayList<>();
        if(saveListString.length()==0)
            return rtn;
     //   System.out.println(saveListString);

        String[] split=saveListString.split("!");


        for(String s:split){
            if(s.charAt(0)=='w'){
                rtn.add(Weapon.getFromString(s));
            }else if(s.charAt(0)=='f'){
                rtn.add(FoodItem.getFromString(s));
            }else{
                rtn.add(getFromString(s));
            }
        }
        return rtn;
    }


}
