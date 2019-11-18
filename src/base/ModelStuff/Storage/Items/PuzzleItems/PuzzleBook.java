/**
 * extra save stuff is just the cipher text for each floor
 */
package base.ModelStuff.Storage.Items.PuzzleItems;

import base.ModelStuff.Storage.Items.PuzzleItem;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Observer;

public class PuzzleBook extends PuzzleItem {

    private String des;

    private String text="";

    public PuzzleBook(){
        super();

    }


    public PuzzleBook(int id,String name,String desc){
        super(id,name);
        des=desc;
    }

    public static PuzzleBook getFromString(String saveString){
        System.out.println("test pb");
        String[] split=saveString.split(",");
        String temp="";
        System.out.println(split.length);
        for(int i=3;i <split.length;i++){
            temp=temp+split[i]+"\n";
        }
        return new PuzzleBook(Integer.parseInt(split[1]),split[2],temp);
    }

    @Override
    public String getSaveString(){
       String rtn="pb,"+getId()+","+getName();
       String[] split=des.split("\n");

       for(String s:split){
           rtn=rtn+","+s;
       }
       // System.out.println(rtn);
       return rtn;
    }


    @Override
    public String use(String[] args){

        setChanged();
        notifyObservers(des);
        return des;
    }


}
