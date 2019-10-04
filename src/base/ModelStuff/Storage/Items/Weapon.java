package base.ModelStuff.Storage.Items;

import java.awt.event.WindowAdapter;

public class Weapon extends Item {

    private int damage,usesRemaining;

    public Weapon(){
        super();
    }

    public Weapon(String id,String name,int damage,int uses){
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

    /**
     * uses when player uses the weapon
     *
     * @return damage
     */
    public int use(){
        System.out.println("");
        return damage;
    }

}
