package base.ModelStuff.Storage;

import base.ModelStuff.Storage.Items.Item;
import base.ModelStuff.Storage.Items.Weapon;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

/**Holds the monsters,rooms, and items
 *
 */
public class Map extends Observable {

    HashMap<Integer, Room> rooms;
    HashMap<Integer,Monster>  monsters;
    HashMap<Integer,Item> items;
    HashMap<Integer,Puzzle> puzzles;


    //complete
    public void save(String saveName) {
        saveItems(saveName,items);
        saveMonsters(saveName,monsters);
        savePuzzles(saveName,puzzles);
        saveRooms(saveName,rooms);
    }


    //complete
    /**Josh
     *
     * load the game from the folder name
     *
     * @param saveName
     */
    public void load(String saveName) {
        monsters=loadMonsters(saveName);
        items=loadItems(saveName);
        puzzles=loadPuzzle(saveName);
        rooms=loadRooms(saveName,monsters,items,puzzles);
    }

    /**Josh
     *
     * gets a room by the id
     *
     *
     * @param id
     * @return
     */
    public Room getRoomById(int id){
        return rooms.get(id);
    }

    public HashMap<Integer,Item> getItems(){
        return items;
    }

    public HashMap<Integer,Room> getRooms(){
        return rooms;
    }


//static methods

    /**Josh
     *
     * creates a hashmap of monsters with the key being the id of the monsters
     *
     * @param saveName
     * @return
     */
    private static HashMap<Integer,Monster> loadMonsters(String saveName){
        HashMap<Integer,Monster> monsters=new HashMap<>();
        File f=new File("saves\\"+saveName+"\\Monsters.txt");
        try{
            Scanner s=new Scanner(f);
            while (s.hasNextLine()){
                String str=s.nextLine();
                String[] split=str.split(",");

                monsters.put(Integer.parseInt(split[0]),Monster.createFromString(str));
            }
        }catch (FileNotFoundException e){
            System.out.println(e);
        }
        return monsters;
    }


    /**Josh
     *
     * loads in the items from file
     *
     * @param saveName
     * @return
     */
    private static HashMap<Integer,Item> loadItems(String saveName){
        HashMap<Integer,Item> items=new HashMap<>();
        File f=new File("saves\\"+saveName+"\\Items.txt");
        try{
            Scanner s=new Scanner(f);

            while(s.hasNextLine()){
                String str=s.nextLine();
                String[] split=str.split(",");

                items.put(Integer.parseInt(split[1]),Item.getFromString(str));

            }


        }catch (FileNotFoundException e){
            System.out.println(e);
        }

        return items;
    }

    /**Josh
     *
     * loads in all the puzzles from the saveFile
     *
     * @param saveName
     * @return
     */
    private static HashMap<Integer,Puzzle> loadPuzzle(String saveName){
        File f=new File("saves\\"+saveName+"\\Puzzle.txt");
        HashMap<Integer,Puzzle> puzzles=new HashMap<>();
        try{
            Scanner s=new Scanner(f);

            while (s.hasNextLine()){
                String str=s.nextLine();
                String[] split=str.split("/");
                puzzles.put(Integer.parseInt(split[0]),Puzzle.getPuzzleFromString(str));

            }
        }catch (FileNotFoundException e){
            System.out.println(e);
        }

       return puzzles;
    }

    /**Josh
     *
     * loads the rooms from the saved file
     *
     * @param saveName
     * @param monstersm
     * @param items
     * @return
     */
    private static HashMap<Integer,Room> loadRooms(String saveName,HashMap<Integer,Monster> monstersm,HashMap<Integer,Item> items,HashMap<Integer,Puzzle> puzzles){
        HashMap<Integer,Room> rooms=new HashMap<>();
        File f=new File("saves\\"+saveName+"\\Room.txt");
        try{
            Scanner s=new Scanner(f);

            while (s.hasNextLine()){
                String str=s.nextLine();
                String[] split=str.split(",");

                rooms.put(Integer.parseInt(split[0]),Room.createRoomFromString(str,monstersm,items,puzzles));
            }



        }catch (FileNotFoundException e){
            System.out.println(e);
        }

        return rooms;
    }

    /**Johs
     *
     * saves the items passed in at the address saveName
     *
     * @param saveName
     * @param items
     */
    private static void saveItems(String saveName,HashMap<Integer,Item> items){
        File file=new File("saves\\"+saveName+"\\Items.txt");
        try {
            String s="";
            for(int i:items.keySet()){
                s=s+"\n"+items.get(i).getSaveString();
            }
            FileWriter fr = new FileWriter(file);
            fr.write(s.substring(1));
            fr.close();
        }catch (IOException e){
            System.out.println(e);
        }
    }

    /**Josh
     *
     * saves the mosnters to the file from the hasmap para
     *
     * @param saveName
     * @param monsters
     */
    private static void saveMonsters(String saveName,HashMap<Integer,Monster> monsters){
        File file=new File("saves\\"+saveName+"\\Monsters.txt");
        try {
            String s="";
            for(int i:monsters.keySet()){
                s=s+"\n"+monsters.get(i).toString();
            }
            FileWriter fr = new FileWriter(file);
            fr.write(s.substring(1));
            fr.close();
        }catch (IOException e){
            System.out.println(e);
        }
    }

    /**Josh
     *
     * saves the puzzles to the save
     *
     * @param saveName
     * @param puzzles
     */
    private static void savePuzzles(String saveName,HashMap<Integer,Puzzle> puzzles){
        File file=new File("saves\\"+saveName+"\\Puzzle.txt");
        try {
            String s="";
            for(int i:puzzles.keySet()){
                s=s+"\n"+puzzles.get(i).toString();
            }
            FileWriter fr = new FileWriter(file);
            fr.write(s.substring(1));
            fr.close();
        }catch (IOException e){
            System.out.println(e);
        }

    }

    /**Josh
     *
     * saves the rooms passed in to saveName
     *
     * @param saveName
     * @param rooms
     */
    private static void saveRooms(String saveName,HashMap<Integer,Room> rooms){
        File file=new File("saves\\"+saveName+"\\Room.txt");
        try {
            String s="";
            for(int i:rooms.keySet()){
                s=s+"\n"+rooms.get(i).toString();
            }
            FileWriter fr = new FileWriter(file);
            fr.write(s.substring(1));
            fr.close();
        }catch (IOException e){
            System.out.println(e);
        }
    }

    @Override
    public void addObserver(Observer o){
        super.addObserver(o);
        for(int i: rooms.keySet()){
            rooms.get(i).addObserver(o);
        }
        for(int i:items.keySet()){
            items.get(i).addObserver(o);
        }
        for(int i:monsters.keySet()){
            monsters.get(i).addObserver(o);
        }
        for(int i:puzzles.keySet())
            puzzles.get(i).addObserver(o);

    }


    @Override
    public String toString() {
        return monsters.toString()+"\n"+items.toString()+"\n"+rooms.toString();
    }

    public static void main(String[] args) {
        Map m=new Map();
        m.load("test");
        m.save("t");

    }


}
