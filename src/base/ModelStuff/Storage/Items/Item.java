package base.ModelStuff.Storage.Items;

import com.sun.corba.se.spi.ior.IORTemplate;

public class Item {

    private String name;
    private String itemId;

    public Item(){

    }

    public Item(String id,String name){
        itemId=id;
        this.name=name;
    }

    public String getName(){
        return name;
    }

    public String getID(){
        return itemId;
    }
}
