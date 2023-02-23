package app.helloworld.controllers;
import app.helloworld.view.Window;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.scene.layout.Pane;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;

import javafx.fxml.Initializable;
import java.util.*;
import javafx.event.ActionEvent;


public class GameEndController implements Controller {

    @FXML
    private AnchorPane anchorPane;

    @FXML
    private Button continueButton;

    @FXML
    private Text gameOverText;

    @FXML
    private Text mainText;

    @FXML
    private Button restartButton;

    private Pane pane;
    private Scene scene;

    HashMap<String, Controller> controllersList =  new HashMap<>(); 
    
    @FXML
    void restartGame(ActionEvent event) {
        // reset the buttons
        GameController gc = (GameController)controllersList.get("game-scene");
        gc.restartGame(event);
        // reset the scene 
        this.scene.setRoot(gc.getPane());
    }

    @FXML
    void continueGame(ActionEvent event) {
        GameController gc = (GameController)controllersList.get("game-scene");
        gc.continueGame();
        this.scene.setRoot(gc.getPane());
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
            anchorPane.setStyle("-fx-background-color: #ed8cd6"); 

        } else if (gc.getPlayerWon() == 1) {

            winner = "Player O has won!";
            anchorPane.setStyle("-fx-background-color: #88d0eb"); 

        } else if (gc.getPlayerWon() ==-1) {
            winner = "Players are tied!";
            anchorPane.setStyle("-fx-background-color: #a37ff0"); // purple
        }
        mainText.setText(winner);
    }

}
