package base.ModelStuff.Storage.Items;

import com.sun.corba.se.spi.ior.IORTemplate;

public class Item {

    private String name,id;

    public Item(){

    }

    public Item(String id,String name){
        this.id=id;
        this.name=name;
    }

//public methods

    /**Josh
     *
     * returns item name
     *
     * @return
     */
    public String getName(){
        return name;
    }






}
