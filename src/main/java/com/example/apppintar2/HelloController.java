package com.example.apppintar2;

import Modelo.Line;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.ResourceBundle;

public class HelloController implements Initializable {

    @FXML
    private Canvas paneDibujo;
    private List<Line> undoList = new ArrayList<>();

    private List<Line> redoList = new ArrayList<>();

    @FXML
    private Label welcomeText;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Get the GraphicsContext of the Canvas
        GraphicsContext gc = paneDibujo.getGraphicsContext2D();

        // Drawing a line
        gc.setStroke(javafx.scene.paint.Color.BLACK); // Set line color
        gc.setLineWidth(2); // Set line width
        gc.strokeLine(50, 50, 350, 350); // Draw line from (50,50) to (350,350)
        Line line = new Line(50,50,350,350);
        undoList.add(line);
    }

    @FXML
    protected void undo() {
        welcomeText.setText("Undo");
        Canvas newCanvas = new Canvas(paneDibujo.getWidth(), paneDibujo.getHeight());
        GraphicsContext newGc = newCanvas.getGraphicsContext2D();
        for (Line line: undoList) {
            newGc.strokeLine(line.getX1(), line.getX2(), line.getY1(), line.getY2());
            redoList.add(line);
        }
        paneDibujo = newCanvas;
    }

    @FXML
    protected void redo(){
        welcomeText.setText("Redo");
        Canvas newCanvas = new Canvas(paneDibujo.getWidth(), paneDibujo.getHeight());
        GraphicsContext newGc = newCanvas.getGraphicsContext2D();
        for (Line line: redoList) {
            newGc.strokeLine(line.getX1(), line.getX2(), line.getY1(), line.getY2());
            redoList.add(line);
        }
    }

}