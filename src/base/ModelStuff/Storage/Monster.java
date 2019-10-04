package base.ModelStuff.Storage;

import javafx.application.Platform;

public class Monster {

    private String name,id;
    private int health,damage;

    public Monster(String name,String id,int health,int damage){
        this.name=name;
        this.id=id;
        this.health=health;
        this.damage=damage;
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
}
