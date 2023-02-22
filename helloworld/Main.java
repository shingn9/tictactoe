package app.helloworld;
import app.helloworld.view.Window;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
// import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Window w = new Window();
        primaryStage.setTitle("Tic Tac Toe");
        primaryStage.setScene(w.getScene());
        primaryStage.show();
    }

    public static void main(String[] args) { launch(args); }
}
