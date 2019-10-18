

package base.ModelStuff.Storage.Items;

public class
FoodItem extends Item{

    private int saturation;

    public FoodItem(String id,String name,int saturation){
        super(id,name);
        this.saturation=saturation;
    }

    /**Josh
     *
     *retunrs the saturation
     *
     * @return
     */
    public int use(){
        return saturation;
    }
}
