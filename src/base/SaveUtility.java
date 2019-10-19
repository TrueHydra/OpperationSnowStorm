package base;

import base.ModelStuff.Storage.Items.Item;
import base.ModelStuff.Storage.Monster;
import base.ModelStuff.Storage.Room;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.*;

public class SaveUtility {

    private String currentURL,currentSaveName;

    public SaveUtility(){
        createPath();
    }

    /**
     * finds the path to the src folder
     *
     */
    private void createPath(){
        currentURL=(System.getProperty("user.dir"));
    }

    /**Josh
     *
     * returns the files in teh saves folder
     * @return
     */
    public String[] getSaves(){
        return new File(currentURL+"\\saves").list();
    }

    /**Josh
     *
     * copies the files from starting point into a new folder in Saves with name saveName
     *
     * @param saveName
     */
    public void createNewSave(String saveName){
    //System.out.println("test");
        String currentSaveFolder=currentURL+"\\saves\\"+saveName;

        String copyFrom=currentURL+"\\starting point";

        Path copyTo= Paths.get(currentSaveFolder);

        //creates the folder
        Path filesToCopy = Paths.get(copyFrom);
        copyFrom(copyTo,filesToCopy);

        //creates the player file
        copyTo=Paths.get(currentSaveFolder+"\\Player.txt");
        filesToCopy = Paths.get(copyFrom+"\\Player.txt");
        copyFrom(copyTo,filesToCopy);

        //creates the Puzzle file
        copyTo=Paths.get(currentSaveFolder+"\\Puzzle.txt");
        filesToCopy = Paths.get(copyFrom+"\\Puzzle.txt");
        copyFrom(copyTo,filesToCopy);

        //creates the Room file
        copyTo=Paths.get(currentSaveFolder+"\\Room.txt");
        filesToCopy = Paths.get(copyFrom+"\\Room.txt");
        copyFrom(copyTo,filesToCopy);

        //creates the Mosnters file
        copyTo=Paths.get(currentSaveFolder+"\\Monsters.txt");
        filesToCopy = Paths.get(copyFrom+"\\Monsters.txt");
        copyFrom(copyTo,filesToCopy);

        //creates the text file
        copyTo=Paths.get(currentSaveFolder+"\\Text.txt");
        filesToCopy = Paths.get(copyFrom+"\\Text.txt");
        copyFrom(copyTo,filesToCopy);

        //creates the helpText file
        copyTo=Paths.get(currentSaveFolder+"\\helpText.txt");
        filesToCopy = Paths.get(copyFrom+"\\helpText.txt");
        copyFrom(copyTo,filesToCopy);

        //creates the helpText file
        copyTo=Paths.get(currentSaveFolder+"\\Items.txt");
        filesToCopy = Paths.get(copyFrom+"\\Items.txt");
        copyFrom(copyTo,filesToCopy);

    }

    /**Josh
     *
     * copies files from a directory to a new directory
     *
     * @param copyTo
     * @param fileToCopy
     */
    private void copyFrom(Path copyTo,Path fileToCopy){
        try{
            if(!Files.exists(copyTo)){
                Files.copy(fileToCopy,copyTo, StandardCopyOption.COPY_ATTRIBUTES);
            }
        }catch (IOException e){
            System.out.println(e);
        }

    }

    public void loadSave(String saveName){
        currentSaveName=saveName;
        loadMonsters();
        loadItems();
        loadRooms();
    }

    public void save(){
        saveRooms();
        saveMonsters();
    }

//deleting save
    public void deleteSave(String name){
        deleteDir(new File(currentURL+"\\Saves\\"+name));

    }

    private static boolean deleteDir(File dir){
        if(dir.isDirectory()){
            File[] children=dir.listFiles();
            for(File f:children){
                boolean success= deleteDir(f);
                if(!success){
                    return false;
                }
            }
        }
        return dir.delete();
    }

//Room Stuff
    HashMap<Integer, Room> rooms=new HashMap<>();

    public HashMap<Integer,Room> getRooms() {
        return rooms;
    }

    //not tested
    /**Josh
     *
     * loads the rooms from currentSave
     *
     * needs to create the rooms add the connections and
     */
    private void loadRooms(){
        rooms=new HashMap<Integer, Room>();
        File f=new File("saves\\"+currentSaveName+"\\Room.txt");
        try{
            Scanner s=new Scanner(f);

            ArrayList<String> roomsStrings=new ArrayList<>();
            while(s.hasNextLine()){
                roomsStrings.add(s.nextLine());
            }

            System.out.println(roomsStrings);

            for(String str:roomsStrings){
                Room temp=Room.createRoomFromString(str);
             //   System.out.println(temp);
                rooms.put(temp.getId(),temp);
            }

            //creates the connections, adds items, adds puzzle, adds monsters

            //may be incorrect
            for(Integer i:rooms.keySet()){

                //creates the connections
                for(String t:rooms.get(i).getConnections().keySet()){
                    //adds a new connection to the rooms connections that has the same string but a real room attached
                    rooms.get(i).getConnections().put(
                            t,rooms.get(rooms.get(i).getConnections().get(t).getId())
                    );
                }
                for(Item it:rooms.get(i).getInventory()){

                }
            }

        }catch (FileNotFoundException e){
            System.out.println(e);
        }
    }

    //not tested
    /**josh
     *
     * saves the rooms in the game
     *
     */
    private void saveRooms(){
        File f=new File("saves\\"+currentSaveName+"\\Room.txt");
        try{
            FileWriter fr=new FileWriter(f);

            String saveString="";
            for(Integer i:rooms.keySet()){
                saveString=saveString+rooms.get(i)+"\n";
            }
            fr.write(saveString);
            fr.close();
        }catch (IOException e){
            System.out.println(e);
        }
    }

//monster Stuff
    HashMap<Integer, Monster> monsters=new HashMap<>();

    //not Tested
    /**Josh
     *
     * loads in the monsters allows the monsters to be added to the rooms
     *
     *
     *
     */
    private void loadMonsters(){
        monsters=new HashMap<>();
        File f=new File("saves\\"+currentSaveName+"\\Monsters.txt");
        try{
            Scanner s=new Scanner(f);

            ArrayList<String> monsterStrings=new ArrayList<>();
            while(s.hasNextLine()){
                monsterStrings.add(s.nextLine());
            }

            System.out.println(monsterStrings);

            for(String str:monsterStrings){
                Monster temp=Monster.createFromString(str);
                monsters.put(temp.getId(),temp);
            }

        }catch (FileNotFoundException e){
            System.out.println(e);
        }
    }

    //not tested
    /**Josh
     *
     * saves the monsters in the monster folder under the current save
     *
     */
    private void saveMonsters(){
        File f=new File("saves\\"+currentSaveName+"\\Monster.txt");
        try{
            FileWriter fr=new FileWriter(f);

            String saveString="";
            for(Integer i:monsters.keySet()){
                saveString=saveString+monsters.get(i)+"\n";
            }
            fr.write(saveString);
            fr.close();
        }catch (IOException e){
            System.out.println(e);
        }
    }

//item stuff
    HashMap<Integer, Item> items=new HashMap<>();

    //not tested
    private void loadItems(){
        items=new HashMap<>();
        File f=new File("saves\\"+currentSaveName+"\\Items.txt");
        try{
            Scanner s=new Scanner(f);

            ArrayList<String> itemStrings=new ArrayList<>();
            while(s.hasNextLine()){
                itemStrings.add(s.nextLine());
            }

            System.out.println(itemStrings);

            for(String str:itemStrings){
                Item temp=Item.getFromString(str);
                items.put(temp.getId(),temp);
            }

        }catch (FileNotFoundException e){
            System.out.println(e);
        }
    }

    //not tested
    private void saveItems(){
        File f=new File("saves\\"+currentSaveName+"\\Items.txt");
        try{
            FileWriter fr=new FileWriter(f);

            String saveString="";
            for(Integer i:items.keySet()){
                saveString=saveString+items.get(i).getSaveString()+"\n";
            }
            fr.write(saveString);
            fr.close();
        }catch (IOException e){
            System.out.println(e);
        }
    }

    public HashMap<Integer,Item> getItems(){
        return items;
    }


    public static void main(String[] args) {
        SaveUtility saveUtility=new SaveUtility();

        saveUtility.loadSave("as");
        System.out.println(saveUtility.getRooms());
    }



}
