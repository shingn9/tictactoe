package app.helloworld.controllers;

import javafx.scene.layout.Pane;
import javafx.scene.Scene;

public interface Controller {
    public void setScene(Scene scene);
    public void setPane(Pane pane);
    public Pane getPane();
}
