/**
 * saved as
 * f;id;name;saturation
 *
 */

package base.ModelStuff.Storage.Items;

public class
FoodItem extends Item{

    private int saturation;
    public FoodItem(){
    }

    public FoodItem(int id,String name,int saturation){
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

    @Override
    public String getSaveString(){
        return "f,"+getId()+","+getName()+","+saturation;
    }

    @Override
    public String inspect(){
        setChanged();
        notifyObservers(getName()+" heals for "+saturation+".");
        return "";
    }


    /**Josh
     *
     * returns a food item from that is created from the string
     *
     * @param saveString
     * @return
     */
    public static FoodItem getFromString(String saveString){
        String[] split=saveString.split(",");
        return new FoodItem(Integer.parseInt(split[1]),split[2],Integer.parseInt(split[3]));
    }
}
