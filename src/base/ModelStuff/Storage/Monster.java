/**
 * saves by line in format
 *
 * id,name,health,damage,floorNumber
 *
 *
 */

package base.ModelStuff.Storage;

import javafx.application.Platform;

import java.util.Observable;

public class Monster extends Observable{

    private int id;
    private String name;
    private int health,damage,floorNumber;

    public Monster(){
        super();
        id=0;
    }

    public Monster(int id){
        super();
        this.id=id;
    }

    public Monster(int id,String name,int health,int damage,int floorNumber){
        this.name=name;
        this.id=id;
        this.health=health;
        this.damage=damage;
        this.floorNumber=floorNumber;
    }

    public static Monster createFromString(String str) {
        String[] s=str.split(",");
        return new Monster(Integer.parseInt(s[0]),s[1],Integer.parseInt(s[2]),Integer.parseInt(s[3]),Integer.parseInt(s[4]));
    }

    public  int getId() {
        return id;
    }

//public methods

    /**
     *
     * takes in damage from monster
     *
     * @param damage
     */
    public void takeDamage(int damage){
        health=health-damage;
        if(health<=0) {
            setChanged();
            notifyObservers("Player attacks " + name + " for " + damage + " damage and has killed " + name);
            return;
        }
        setChanged();
        notifyObservers("Player attack "+name+" for "+damage+" damage and "+name+" now has "+health+" health.");
    }

    /**Josh
     *
     * returns the name of the room
     *
     * @return
     */
    public String getName() {
        return name;
    }

    /**Josh
     *
     * returns monster health
     *
     * @return
     */
    public int getHealth() {
        return health;
    }

    /**
     *
     * takes in player and damages them
     *
     * @param player
     */
    public void attackPlayer(Player player){
        System.out.println("attack player");
        player.takeDamage(damage);



    }


//save/load stuff

    @Override
    public String toString() {
        return id+","+name+","+health+","+damage+","+floorNumber;
    }

}
