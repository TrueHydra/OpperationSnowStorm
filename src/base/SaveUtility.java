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
import java.sql.SQLOutput;
import java.util.*;

public class SaveUtility {

    private String currentURL,currentSaveName;

    public SaveUtility(){
        createPath();
    }

    public String getCurrentSaveName(){
        return currentSaveName;
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

//gets text
    public String getSavedGameText(){
        File f=new File("saves\\"+currentSaveName+"\\Text.txt");
        String rtn="";
        try{
            Scanner s=new Scanner(f);
            while(s.hasNextLine()){
                rtn=rtn+"\n"+s.nextLine();
            }
        }catch (FileNotFoundException e){
            System.out.println(e);
        }
        return rtn.substring(1);
    }

    public void saveGameText(String saveString){
        File f=new File("saves\\"+currentSaveName+"\\Text.txt");
        try{
            FileWriter fr=new FileWriter(f);
            fr.write(saveString);
            fr.close();
        }catch (IOException e){
            System.out.println(e);
        }
    }


}
