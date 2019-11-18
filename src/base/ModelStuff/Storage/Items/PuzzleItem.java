package base.ModelStuff.Storage.Items;


import java.util.HashMap;

public class PuzzleItem extends Item{

  //  HashMap<String[],String> results;

    public PuzzleItem(){}



    public PuzzleItem(int id,String name){
        super(id,name);
     //   this.results=results;
    }

    /**
     * basic puzzle interaction since every puzzle item is different it needs to be versitle
     *
     * @param args
     * @return
     */
    public String use(String[] args){
        return"";
    }

}
