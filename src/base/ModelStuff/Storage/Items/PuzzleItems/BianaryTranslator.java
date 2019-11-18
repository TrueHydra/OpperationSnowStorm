package base.ModelStuff.Storage.Items.PuzzleItems;

import base.ModelStuff.Storage.Items.PuzzleItem;

public class BianaryTranslator extends PuzzleItem {

    public BianaryTranslator(){
        super();
    }

    public BianaryTranslator(int id,String name){
        super(id,name);
    }



    @Override
    public String use(String[] args){
        String rtn="";

        if(args[0].equals("00000")){
            return "a";
        }
        if(args[0].equals("00001")){
            return "b";
        }
        if(args[0].equals("00010")){
            return "c";
        }

        if(args[0].equals("00011")){
            return "d";
        }

        if(args[0].equals("00100")){
            return "e";
        }

        if(args[0].equals("00101")){
            return "f";
        }

        if(args[0].equals("00110")){
            return "g";
        }

        if(args[0].equals("00111")){
            return "h";
        }

        if(args[0].equals("01000")){
            return "i";
        }

        if(args[0].equals("01001")){
            return "j";
        }

        if(args[0].equals("01010")){
            return "k";
        }

        if(args[0].equals("01011")){
            return "l";
        }

        if(args[0].equals("01100")){
            return "m";
        }

        if(args[0].equals("01101")){
            return "n";
        }

        if(args[0].equals("01110")){
            return "o";
        }

        if(args[0].equals("01111")){
            return "p";
        }

        if(args[0].equals("10000")){
            return "q";
        }

        if(args[0].equals("10000")){
            return "r";
        }

        if(args[0].equals("10001")){
            return "s";
        }

        if(args[0].equals("10010")){
            return "t";
        }

        if(args[0].equals("10011")){
            return "u";
        }

        if(args[0].equals("10100")){
            return "v";
        }

        if(args[0].equals("10101")){
            return "w";
        }

        if(args[0].equals("10110")){
            return "x";
        }

        if(args[0].equals("10111")){
            return "y";
        }

        if(args[0].equals("11111")){
            return "z";
        }

        return "";
    }

    @Override
    public String getSaveString(){
        return "bt,"+getId()+","+getName();
    }

    public static BianaryTranslator getBTFromString(String saveString){
        String[] spllit=saveString.split(",");
        System.out.println("bnt");
        return new BianaryTranslator(Integer.parseInt(spllit[1]),spllit[2]);
    }


}
