/**
 * saved in format
 *
 * id/puzzle text/correct response/puzzle response/ has been solved
 *
 *
 */

package base.ModelStuff.Storage;

import javafx.scene.layout.Pane;

import java.util.Observable;

public class Puzzle extends Observable implements Saveable{

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
        System.out.println("solve puzzle");
        return false;
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
    @Override
    public void save(String saveName) {

    }

    @Override
    public void load(String saveName) {

    }


}
