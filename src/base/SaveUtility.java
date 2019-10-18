package base;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;

public class SaveUtility {

    String currentURL;

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
        System.out.println("test");
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


}
