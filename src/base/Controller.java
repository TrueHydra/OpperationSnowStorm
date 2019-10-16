package base;

import base.ModelStuff.Storage.Player;
import base.ModelStuff.Storage.Room;
import com.sun.xml.internal.ws.api.server.EndpointReferenceExtensionContributor;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.omg.CosNaming.NamingContextExtPackage.StringNameHelper;

import java.nio.file.Path;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Controller {

    private Player player;
    private HashMap<Integer, Room> rooms;


    private boolean keepGoing=false;
    private GUI gui;
    private SaveUtility saveUtility;


    private static String[] hold=new String[]{"START","QUIT","SAVE","LOAD","HELP","HEALTH","CONSUME","USE","EQUIP","PICKUP","DROP","OBSERVE","ATTACK","ESCAPE","EXAMINE","SOLVE","ENTER","LOOK"};
    private static final HashSet<String> ACCEPTABLE_COMMAND_STARTS=new HashSet<>(Arrays.asList(hold));

    public Controller(Stage primaryStage){
       this.gui=new GUI(primaryStage,this);
       saveUtility=new SaveUtility();
    }

    public void start(){
        gui.show();
        gui.showStartMenu();
    }


//command stuff

    /**
     * is activated when load gae button pressed
     *
     */
    public void loadGameButton(){

    }

    /**
     *
     * happens when the enter command is taken in the new game menu
     *
     * @param s
     */
    public void loadGameEnter(String s){
        System.out.println(s);
    }




//public stuff
    public void startGame(){
        keepGoing=true;
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
            if ((ACCEPTABLE_COMMAND_STARTS.contains(s.split(" ")[0]) && !Pattern.matches("[^a-zA-Z]", s))||s.split(" ")[0].equals("PICK")&&s.split(" ")[1].equals("UP"))
                return true;
        }catch (NullPointerException e){
            return false;
        }

        return false;

    }





}
