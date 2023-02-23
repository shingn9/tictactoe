package app.helloworld.view;
import app.helloworld.controllers.GameController;
import app.helloworld.controllers.GameEndController;
import app.helloworld.controllers.Controller;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;

import java.util.*;
import javafx.scene.layout.Pane;

public class Window {

    private final String gameControllerFXML = "/fxml/Game.fxml";
    private final String gameEndControllerFXML = "/fxml/GameEnd.fxml";
    private Scene scene;
    private String gameEndControllerKey = "game-end-scene";
    private String gameControllerKey = "game-scene";
    
    private HashMap<String, Controller> controllersList =  new HashMap<>();

    public Window() throws Exception {
        this.scene = new Scene(new Pane());

        initializeScene();
        initialiseGameOverScene();
        
        this.scene.setRoot(controllersList.get(gameControllerKey).getPane());
    }

    public void initializeScene() throws Exception {

        // Parent content = loader.load(); 
        // scene = new Scene(content);
        // this.scene = new Scene(pane); // set original screen to game scene

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(gameControllerFXML));

        // this.scene.setRoot(pane);

        Pane pane = loader.load(); 
        GameController controller = loader.getController();
        controller.setPane(pane);
        controller.setScene(this.scene);
        this.controllersList.put(gameControllerKey, controller);
        controller.setControllersList(this.controllersList);
	}

    public void initialiseGameOverScene() throws Exception {

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(gameEndControllerFXML));
        Pane pane = loader.load(); 
        GameEndController gameEndController = loader.getController();
        gameEndController.setPane(pane);
        gameEndController.setScene(this.scene);
        this.controllersList.put(gameEndControllerKey, gameEndController);
        gameEndController.setControllersList(this.controllersList);
    }

    public Scene getScene() {
        return this.scene;
    }
    
}
