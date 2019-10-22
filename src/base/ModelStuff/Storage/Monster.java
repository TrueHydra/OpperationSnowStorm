/**
 * saves by line in format
 *
 * id,name,health,damage
 *
 *
 */

package base.ModelStuff.Storage;

import javafx.application.Platform;

import java.util.Observable;

public class Monster extends Observable{

    private int id;
    private String name;
    private int health,damage;

    public Monster(){
        super();
    }

    public Monster(int id){
        super();
        this.id=id;
    }

    public Monster(int id,String name,int health,int damage){
        this.name=name;
        this.id=id;
        this.health=health;
        this.damage=damage;
    }

    public static Monster createFromString(String str) {
        String[] s=str.split(",");
        return new Monster(Integer.parseInt(s[0]),s[1],Integer.parseInt(s[2]),Integer.parseInt(s[3]));
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
        System.out.println("monster takeDamage()");
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
    }


//save/load stuff

    @Override
    public String toString() {
        return id+","+name+","+health+","+damage;
    }

}
