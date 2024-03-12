package com.example.apppintar2;

import Modelo.Line;
import javafx.fxml.FXML;
import javafx.geometry.Point2D;
import javafx.scene.control.Label;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

public class HelloController {
    @FXML
    private Label welcomeText;
    @FXML
    private Canvas Canvas;
    
    private Point2D savedPoint = null;
    

    GraphicsContext gc = Canvas.getGraphicsContext2D();
    
        private void handlePrimaryClick(GraphicsContext gc, MouseEvent event) {
        Point2D clickPoint = new Point2D(event.getX(), event.getY());
        

        if (savedPoint == null) {
            savedPoint = clickPoint;
        } else {
            Line line = new Line(clickPoint.getX(),clickPoint.getY(),savedPoint.getX(),savedPoint.getY());
            gc.strokeLine(clickPoint.getX(),clickPoint.getY(),savedPoint.getX(),savedPoint.getY());

            savedPoint = null;
        }
    }
        
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