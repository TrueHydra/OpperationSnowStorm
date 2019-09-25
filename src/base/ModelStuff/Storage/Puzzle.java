package base.ModelStuff.Storage;

public class Puzzle {

    private String puzzleText,solution,correctResponse,id;

    public Puzzle(){}

    public Puzzle(String puzzleText,String solution,String correctResponse,String id){
        this.correctResponse=correctResponse;
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


    public String getCorrectResponse() {
        return correctResponse;
    }

    public String getPuzzleText() {
        return puzzleText;
    }

    public String getSolution() {
        return solution;
    }

    public String getId() {
        return id;
    }
}
