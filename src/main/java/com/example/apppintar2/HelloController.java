package com.example.apppintar2;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class HelloController {
    @FXML
    private Label welcomeText;
    @FXML
    private Canvas Canvas;

    GraphicsContext gc = Canvas.getGraphicsContext2D();
    
    gc.strokeRect(0, 0, Canvas.getWidth(), Canvas.getHeight());
    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }

    @FXML
    protected void undo(){
        welcomeText.setText("Undo");
    }

    @FXML
    protected void redo(){
        welcomeText.setText("Redo");
    }
}