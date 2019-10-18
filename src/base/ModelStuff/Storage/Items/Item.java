/**
 *
 * saved as item type(f,w,p),id,name,rest is dependent on type
 *
 */

package base.ModelStuff.Storage.Items;

import base.ModelStuff.Storage.Saveable;
import com.sun.corba.se.spi.ior.IORTemplate;

import java.util.Observable;

public class Item extends Observable implements Saveable {

    private String name,id;

    public Item(){
        super();
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


    @Override
    public void save(String saveName) {

    }

    @Override
    public void load(String saveName) {

    }
}
