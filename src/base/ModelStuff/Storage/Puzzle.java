/**
 * saved in format
 *
 * id/puzzle text/correct response/puzzle response/ has been solved
 *
 *
 */

package base.ModelStuff.Storage;

import javafx.scene.layout.Pane;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Observable;

public class Puzzle extends Observable{

    private int id;
    private String puzzleText,solution, puzzleResponse;
    private boolean beenSolved;

    public Puzzle(){
        super();

    }

    public Puzzle(int i){
        id=i;
    }

    public Puzzle(String puzzleText, String solution, String puzzleResponse, int id,boolean beenSolved){
        this.puzzleResponse = puzzleResponse;
        this.puzzleText=puzzleText;
        this.solution=solution;
        this.id=id;
        this.beenSolved=beenSolved;
    }

//public stuff


    /**Josh
     *
     * returns the puzzle text
     *
     * @return
     */
    public String getPuzzleText() {
        return puzzleText;
    }

    /**Josh
     *
     * returns the puzzle response
     *
     * @return
     */
    public String getPuzzleResponse() {
        return puzzleResponse;
    }

    /**
     *
     * test the string agains this.solution if is correct beenSolved=true and return true
     *
     * @param solution
     * @return
     */
    public boolean solve(String solution){
        if(solution.equals(this.solution)){
            beenSolved=true;
            setChanged();
            notifyObservers(puzzleResponse);
            return true;
        }else{
            setChanged();
            notifyObservers("That was wrong.");
            return false;
        }
    }

    /**Josh
     *
     * return is th puzzle has been solved
     *
     * @return
     */
    public boolean hasBeenSolved() {
        return beenSolved;
    }

    public int getId() {
        return id;
    }

    public void examine(){
        System.out.println(countObservers());
        setChanged();
        notifyObservers(puzzleText);
    }

    /**Josh
     *
     * is used to create a puzzle from the string in which the puzzle is saved
     *
     * @param saveString
     * @return
     */
    public static Puzzle getPuzzleFromString(String saveString){
        String[] split=saveString.split("/");
       // System.out.println(Arrays.toString(split));
        return new Puzzle(split[1],split[2],split[3],Integer.parseInt(split[0]),Boolean.parseBoolean(split[4]));
    }

    public String a(){
        return toString();
    }

    public String toString(){
        return id+"/"+puzzleText+"/"+solution+"/"+puzzleResponse+"/"+beenSolved;
    }


}
