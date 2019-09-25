package base.ModelStuff.Storage.Items;

import com.sun.corba.se.spi.ior.IORTemplate;

public class Item {

    private String description;
    private int itemId;

    public Item(){

    }

    public Item(int id,String description){
        itemId=id;
        this.description=description;
    }

    public String getDescription(){
        return description;
    }
}
