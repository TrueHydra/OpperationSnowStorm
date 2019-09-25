package base.ModelStuff.Storage;

public class Monster {

    private String name,id;
    private int health,damage;

    public Monster(String name,String id,int health,int damage){
        this.name=name;
        this.id=id;
        this.health=health;
        this.damage=damage;
    }

    /**
     * takes in the save string and creates monster from it
     *
     * @param saveString
     */
    public Monster(String saveString){
        System.out.println("needs to be done monster from string");
    }

//get methods


    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getDamage() {
        return damage;
    }

    public int getHealth() {
        return health;
    }

    /**
     * this removes amount health from monster
     *
     * returns false if monster dies
     * @param amount
     * @return
     */
    public boolean dealDamage(int amount){
        health-=amount;
        if(health<=0)
            return false;
        return true;
    }

}
