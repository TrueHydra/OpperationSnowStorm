package base.ModelStuff.Storage.Items;


import java.util.HashMap;

public class PuzzleItem extends Item{

    HashMap<String[],String> results;

    public PuzzleItem(String id,String name,HashMap<String[],String> results){
        super(id,name);
        this.results=results;
    }

    /**
     *
     * handles the use of the puzzle item
     *
     * @param input
     * @return
     */
    public String getResultFromStrings(String[] input){
        System.out.println("getResultFromString");
        return "";
    }

}
