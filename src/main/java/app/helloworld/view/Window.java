package app.helloworld.view;
import app.helloworld.controllers.GameController;
import app.helloworld.controllers.GameEndController;
import app.helloworld.controllers.Controller;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;
import java.io.InputStream;

import java.util.*;
import javafx.scene.layout.Pane;
import javafx.fxml.FXML;
import java.net.URL;

import javafx.scene.paint.Color;

public class Window {

    private final String gameControllerFXML = "/fxml/Game.fxml";
    private final String gameEndControllerFXML = "/fxml/GameEnd.fxml";
    Scene scene;
    // Controller controller = new GameController();
    // GameEndController gameEndController = new GameEndController();
    Parent p;
    private HashMap<String, Controller> controllersList =  new HashMap<>();

    private int playerWon = 0;

    public Window() throws Exception {
        this.scene = new Scene(new Pane());

        initializeScene();
        initialiseGameOverScene();
        
        this.scene.setRoot(controllersList.get("game-scene").getPane());
    }

    public void setPlayerWon(int playerWon) {
        this.playerWon = playerWon;
    }

    public int getPlayerWon() {
        return this.playerWon;
    }

    public void initializeScene() throws Exception {
        // URL fxmlLoader = getClass().getResource(gameControllerFXML);
        // FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Game.fxml"));,
        // Pane root = loader.load();
        // GameController controller = loader.getController();
        // controller.setPane(root);
        // controller.setScene(this.scene);

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(gameControllerFXML));
        // Parent content = loader.load(); 
        // p = content;
        // scene = new Scene(content);
        // Pane pane  = new Pane();
        Pane pane = loader.load(); 
        // this.scene = new Scene(pane); // set original screen to game scene
        // GameController controller = loader.getController();
        GameController controller = loader.getController();
        controller.setPane(pane);
        // this.scene.setRoot(pane);
        controller.setScene(this.scene);
        // controller.setWindow(this);
        this.controllersList.put("game-scene", controller);
        controller.setControllersList(this.controllersList);
	}

    public void initialiseGameOverScene() throws Exception {

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(gameEndControllerFXML));
        Pane pane = loader.load(); 
        // this.scene = new Scene(pane);

        GameEndController gameEndController = loader.getController();
        gameEndController.setPane(pane);
        gameEndController.setScene(this.scene);
        // // gameEndController.setWindow(this);
        this.controllersList.put("game-end-scene", gameEndController);
        gameEndController.setControllersList(this.controllersList);
    }

    public Parent getParent() {
        return p;
    }

    public Scene getScene() {
        return this.scene;
    }
    
}
