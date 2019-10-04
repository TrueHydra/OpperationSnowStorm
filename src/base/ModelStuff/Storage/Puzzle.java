package base.ModelStuff.Storage;

public class Puzzle {

    private String puzzleText,solution, puzzleResonse,id;

    public Puzzle(){}

    public Puzzle(String puzzleText, String solution, String puzzleResonse, String id){
        this.puzzleResonse = puzzleResonse;
        this.puzzleText=puzzleText;
        this.solution=solution;
        this.id=id;
    }

    /**
     * creates puzzle from a save string
     *
     * @param saveString
     */
    public Puzzle(String saveString){
        System.out.println("needs done puzzle from save");
    }

// get methds


    public String getPuzzleResonse() {
        return puzzleResonse;
    }

    public String getPuzzleText() {
        return puzzleText;
    }

    public String getId() {
        return id;
    }

//public methods

    /**
     *
     * checks is password matches solution
     *
     * @param password
     * @return
     */
    public boolean isCorrect(String password){
        System.out.println("neneds done isCOrrect");
        return false;
    }
}
