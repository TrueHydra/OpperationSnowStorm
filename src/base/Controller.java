package base;

import base.ModelStuff.Storage.Player;
import base.ModelStuff.Storage.Room;
import com.sun.xml.internal.ws.api.server.EndpointReferenceExtensionContributor;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
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

//start menu/load menu/start new game menu stuff

    //complete
    /**
     * is activated when load gae button pressed
     *
     */
    public void loadGameButton(){
        gui.showLoadGameMenu(saveUtility.getSaves());
    }

    /**Josh
     *
     * creates the neew save when button is hit also checks if name is proper
     *
     * @param s
     */
    public void startNewGame(String s){
        if(Arrays.asList(saveUtility.getSaves()).contains(s)){
            System.out.println("stuff");
            gui.addNewGameError("That name is already taken");
        }else if(s==null){
            gui.addNewGameError("Please enter name");
        }else if(!(s.length()>0 &&s.length()<10)){
            gui.addNewGameError("The name must be between 1 and 9 characters long");
        }
        else {
            saveUtility.createNewSave(s);
            loadGameEnter(s);
        }
    }

    /**Josh
     *
     * happens when the enter command is taken in the new game menu
     *
     * @param s
     */
    public void loadGameEnter(String s){
        System.out.println(s);
    }

    /**Josh
     *
     * deletes the save
     *
     * @param s
     */
    public void deleteSave(String s) {
        saveUtility.deleteSave(s);
        loadGameButton();
    }


//command stuff








}
