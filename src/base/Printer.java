package base;

public class Printer {

    public Printer(){

    }

    public void print(String s){
        System.out.println(s);
    }

    /**
     * this will print error message if player input a non existant command
     */
    public void printBasicCommandProblem(){
        System.out.println("");
    }
}
