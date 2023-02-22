package app.helloworld.controllers;
import app.helloworld.view.Window;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.scene.layout.Pane;
import javafx.scene.Scene;

import javafx.fxml.Initializable;
import java.util.*;
import javafx.event.ActionEvent;

public class GameEndController implements Controller {

    @FXML
    private Text gameOverText;

    @FXML
    private Text mainText;

    @FXML
    private Button restartButton;

    private Pane pane;
    private Scene scene;
    // private Window window;

    HashMap<String, Controller> controllersList =  new HashMap<>(); 
    
    @FXML
    void restartGame(ActionEvent event) {
        // reset the buttons
        GameController gc = (GameController)controllersList.get("game-scene");
        gc.restartGame(event);
        // reset the scene 
        this.scene.setRoot(controllersList.get("game-scene").getPane());
    }

    public void setScene(Scene scene){
        this.scene = scene;
    }

    public void setPane(Pane pane){
        this.pane = pane;
    }

    public void setControllersList(HashMap<String, Controller> controllersList) {
        this.controllersList = controllersList;
    }

    public Pane getPane() {
        return this.pane;
    }

    public void setPlayerWon() {
        String winner = "";
        GameController gc = (GameController)controllersList.get("game-scene");

        if (gc.getPlayerWon() == 0) {
            // player X
            winner = "Player X has won!";
        } else if (gc.getPlayerWon() == 1) {
            winner = "Player O has won!";
        } else if (gc.getPlayerWon() ==-1) {
            winner = "Both players are tied!";
        }
        mainText.setText(winner);
    }

    // @Override
    // public void initialize(URL location, ResourceBundle resourceBundle) {
    //     this.scene.setRoot(controllersList.get("game-end-scene").getPane());

    //     String winner = "";

    //     // if (this.window.getPlayerWon() == 0) {
    //     //     // player X
    //     //     winner = "Player X has won!";
    //     // } else {
    //     //     winner = "Player O has won!";
    //     // }
    //     mainText.setText(winner);
    // }

}
