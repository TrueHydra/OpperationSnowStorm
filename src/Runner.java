import base.Controller;
import javafx.application.Application;
import javafx.stage.Stage;

public class Runner extends Application {


    public static void main(String[] args){
        launch(args);
    }

    public void start(Stage primaryStage){

        Controller controller;

        controller=new Controller(primaryStage);
        controller.start();

        //controller.loadGameEnter("test");
        //controller.loadGameEnter("test");
    }




}
