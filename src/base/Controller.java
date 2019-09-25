package base;

import base.ModelStuff.Model;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Controller {

    private Model model;
    private Printer printer;

    private static String[] hold=new String[]{"START","QUIT","SAVE","LOAD","HELP","HEALTH","CONSUME","USE","EQUIP","PICKUP","DROP","OBSERVE","ATTACK","ESCAPE","EXAMINE","SOLVE","ENTER","LOOK"};
    private static final HashSet<String> ACCEPTABLE_COMMAND_STARTS=new HashSet<>(Arrays.asList(hold));

    public Controller(Model model,Printer printer){
       this.model=model;
       this.printer=printer;
    }


    /**
     * THis is the primary method that the player intact with it reads the commands the player gives
     *
     */
    public void takeCommand(){
        Scanner s=new Scanner(System.in);

        String command=s.nextLine().trim();

        if(!checkIfIsCommand(command)){
            printer.printBasicCommandProblem();
            return;
        }
        model.command(command);
    }


    /**
     * method is used to check if the command given by the player is an acceptable command and if the command has any characters that aren't letters
     *
     * checks if first word is acceptable command
     * @param s
     * @return
     */
    private Boolean checkIfIsCommand(String s){
        try {
            if ((ACCEPTABLE_COMMAND_STARTS.contains(s.split(" ")[0]) && !Pattern.matches("[^a-zA-Z]", s) && s.split(" ").length < 3)||s.split(" ")[0].equals("PICK")&&s.split(" ")[1].equals("UP"))
                return true;
        }catch (NullPointerException e){
            return false;
        }

        return false;

    }





}
