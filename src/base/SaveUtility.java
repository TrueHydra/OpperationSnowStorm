package base;

import java.io.File;
import java.nio.file.Path;
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

    public String[] getSaves(){
        return new File(currentURL+"\\src\\Saves").list();
    }


}
