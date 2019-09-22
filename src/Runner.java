import base.Controller;
import base.Model;
import base.Printer;

public class Runner {


    public static void main(String[] args){




        Printer printer=new Printer();
        Model model=new Model(printer);
        Controller controller=new Controller(model);
        



    }




}
