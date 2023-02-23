package app.helloworld.controllers;
import app.helloworld.view.Window;

import javafx.event.ActionEvent;
import javafx.event.*;
import javafx.animation.*;
import javafx.util.Duration;

import javafx.fxml.FXML;
import java.util.*;
import javafx.fxml.Initializable;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

import javafx.scene.Scene;

import java.net.URL;

public class GameController implements Controller, Initializable {

    @FXML
    private Button B1;

    @FXML
    private Button B2;

    @FXML
    private Button B3;

    @FXML
    private Button B4;

    @FXML
    private Button B5;

    @FXML
    private Button B6;

    @FXML
    private Button B7;

    @FXML
    private Button B8;

    @FXML
    private Button B9;

    @FXML
    private Label OPlayerScore;

    @FXML
    private Label XPlayerScore;

    @FXML
    private Text playerOtext;

    @FXML
    private Text playerXtext; 

    @FXML
    private Button restartButton;

    @FXML
    private Button switchModeButton;

    private int playerTurn = 0;
    private int playerWon = 2; // if even == X, odd == O

    private int playerXscore = 0;
    private int playerOscore = 0;
    
    private ArrayList<Button> buttons;
    private Pane pane;

    private Scene scene;
    private Window window;
    private HashMap<String, Controller> controllersList =  new HashMap<>(); 

    final String startingColourHex = "-fx-background-color:#33A9BA;";

    // dark background
    final String switchColourHex = "-fx-background-color:#25282b;";

    private int mode = 0;
    
    public GameController() {
    }

    public int getPlayerWon() {
        // if even == X, odd == O
        return this.playerWon;
    }

    public void setScene(Scene scene){
        this.scene = scene;
    }

    public void setPane(Pane pane){
        this.pane = pane;
    }

    public void setWindow(Window window){
        this.window = window;
    }  
    
    public void setControllersList(HashMap<String, Controller> controllersList) {
        this.controllersList = controllersList;
    }

    public Pane getPane() {
        return this.pane;
    }
    
    @Override
    public void initialize(URL location, ResourceBundle resourceBundle) {
        buttons = new ArrayList<>(Arrays.asList(B1, B2, B3, B4, B5, B6, B7, B8, B9));
        buttons.forEach(b -> {
            setUpButton(b);
            System.out.println("hi");
            b.setFocusTraversable(false);
        });
    }

    public void setUpButton(Button b) {
        playerTurn = 0;
        b.setStyle(startingColourHex);
        b.setOnMouseClicked(mouseEvent -> {
            
            setPlayerSymbol(b);
            if (b.getText().equals("X")) 
                b.setStyle("-fx-background-color:#F02A9C;-fx-font-size:40;");
            else 
                b.setStyle("-fx-background-color:#62D4FA;-fx-font-size:40;");
            b.setDisable(true);
            checkGameOver();
        }); 
    }

    public void resetButton(Button b) {
        playerTurn = 0;
        b.setStyle(startingColourHex);
        b.setDisable(false);
        b.setText("");
    }

    public void setPlayerSymbol(Button b) {
        if (playerTurn == 0) {
            b.setText("X");
            playerTurn = 1;
            
        } else {
            b.setText("O");
            playerTurn = 0; 
        }
    }

    public void checkGameOver() {
        String result = "";
        for (int i = 1 ; i <= 8; i++) {

            result = switch (i) {
                case 1 -> B1.getText() + B2.getText() + B3.getText();
                case 2 -> B4.getText() + B5.getText() + B6.getText(); 
                case 3 -> B7.getText() + B8.getText() + B9.getText();
                case 4 -> B1.getText() + B5.getText() + B9.getText();
                case 5 -> B3.getText() + B5.getText() + B7.getText();
                case 6 -> B1.getText() + B4.getText() + B7.getText();
                case 7 -> B2.getText() + B5.getText() + B8.getText();
                case 8 -> B3.getText() + B6.getText() + B9.getText();
                default -> "TIE";
            };

            if (result.equals("XXX")) {
                // X won even 
                playerWon = 0;
                playerXscore++;
                String s = Integer.toString(playerXscore);
                XPlayerScore.setText(s);

                GameEndController gec = (GameEndController) controllersList.get("game-end-scene");
                gec.setPlayerWon();
                this.scene.setRoot(gec.getPane());
                return;

            } else if (result.equals("OOO")) {
                // 0 won
                playerWon = 1;
                playerOscore++;
                String s = Integer.toString(playerOscore);
                OPlayerScore.setText(s);

                GameEndController gec = (GameEndController) controllersList.get("game-end-scene");
                gec.setPlayerWon();
                this.scene.setRoot(gec.getPane());
                return;
            }

        }
        checkTied();
    }

    private void checkTied() {
        int count = 0;
        for (Button b: buttons) {
            if (b.isDisabled() == true) {
                count++;
            }
        }
        if (count == 9) {
            playerWon = -1;
            GameEndController gec = (GameEndController) controllersList.get("game-end-scene");
            gec.setPlayerWon();
            this.scene.setRoot(gec.getPane());
        }
    }

    void continueGame() {
        buttons.forEach(this::resetButton);
    }


    @FXML
    void restartGame(ActionEvent event) {
        // default starting is player X
        buttons.forEach(this::resetButton);
        OPlayerScore.setText("0");
        XPlayerScore.setText("0");
    }

    @FXML
    void clickButton(Button button, ActionEvent event) {

    }

    @FXML
    void switchMode(ActionEvent event) {
        if (mode == 0) {
            for (Button b: buttons) {
                if (!b.isDisabled()) {
                    b.setStyle(switchColourHex);
                }
            }
            mode = 1;
        }  else if (mode == 1) {

            for (Button b: buttons) {
                if (!b.isDisabled()) {
                    b.setStyle(startingColourHex);
                }
            }
            mode = 0;
        }
    }


}
