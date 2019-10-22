/**
 * saved as:
 * type;id;name;damage;usesRemaining
 *
 */

package base.ModelStuff.Storage.Items;

import java.awt.event.WindowAdapter;

public class Weapon extends Item {

    private int damage,usesRemaining;

    public Weapon(){
        super();
    }

    public Weapon(int id,String name,int damage,int uses){
        super(id,name);
        this.damage=damage;
        this.usesRemaining=uses;
    }

    /**
     *
     *
     * @return damage
     */
    public int getDamage(){
        return damage;
    }

    public int getUsesRemaining(){
        return usesRemaining;
    }

    /**
     * uses when player uses the weapon
     *
     * @return damage
     */
    public int use(){
        usesRemaining--;
        return damage;

    }

    public void setUsesRemaining(int usesRemaining){
        this.usesRemaining=usesRemaining;
    }

    @Override
    public String getSaveString(){
        return "w,"+getId()+","+getName()+","+damage+","+usesRemaining;
    }

    /**Josh
     *
     * splits the savestroing and returns a weapon from it
     *
     * @param saveString
     * @return
     */
    public static Weapon getFromString(String saveString){
        String[] split=saveString.split(",");
        return new Weapon(Integer.parseInt(split[1]),split[2],Integer.parseInt(split[3]),Integer.parseInt(split[4]));
    }

}
