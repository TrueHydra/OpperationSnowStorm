package base.ModelStuff;

import base.Controller;
import base.ModelStuff.Storage.Monster;
import base.ModelStuff.Storage.Player;
import base.ModelStuff.Storage.Puzzle;
import base.ModelStuff.Storage.Room;
import base.Printer;

import java.util.List;

public class Model {

    private Printer printer;
    private Controller controller=new Controller(new Model(new Printer()),new Printer());
    private Player player;
    private Room currentRoom;
    private List<Room> rooms;
    private List<Puzzle> puzzles;
    private List<Monster> monsters;
    private boolean isReady;

    public Model(Printer printer) {
        this.printer=printer;
        setupNew();
    }

    public void setController(Controller controller){
        this.controller=controller;
    }

    public boolean isReady(){
        return isReady;
    }

    /**
     * This sets up a new game in the starting positions
     */
    public void setupNew(){
        player=new Player();
    }




    /**
     * for testing purposes
     * @param args
     */
    public static void main(String[] args) {
    }




}
