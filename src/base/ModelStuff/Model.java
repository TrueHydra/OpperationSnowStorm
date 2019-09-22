package base.ModelStuff;

import base.ModelStuff.Storage.Monster;
import base.ModelStuff.Storage.Player;
import base.ModelStuff.Storage.Puzzle;
import base.ModelStuff.Storage.Room;
import base.Printer;

import java.util.List;

public class Model {

    private Printer printer;
    private Player player;
    private Room currentRoom;
    private List<Room> rooms;
    private List<Puzzle> puzzles;
    private List<Monster> monsters;

    public Model(Printer printer){
        this.printer=printer;
    }



    /**
     * This begins the game
     *
     * takes in nothing
     */
    public void beginGame(){

    }




}
