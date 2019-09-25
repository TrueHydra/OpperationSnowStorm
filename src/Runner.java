import base.Controller;
import base.ModelStuff.Model;
import base.Printer;

public class Runner {


    public static void main(String[] args){

        Controller controller;

        Printer printer=new Printer();
        Model model=new Model(printer);
        controller=new Controller(model,printer);

        model.setController(controller);




    }




}
