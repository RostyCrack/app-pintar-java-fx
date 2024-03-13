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

    private GraphicsContext gc;

    private List<Line> redoList = new ArrayList<>();


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Get the GraphicsContext of the Canvas
        gc = paneDibujo.getGraphicsContext2D();
        // Drawing a line
        gc.setStroke(javafx.scene.paint.Color.BLACK); // Set line color
        gc.setLineWidth(2); // Set line width
        gc.strokeLine(50, 50, 350, 350);
        gc.strokeLine(60,60,400,400);
        Line line = new Line(60,60,400,400);// Draw line from (50,50) to (350,350)
        Line line2 = new Line(60,60,400,400);
        undoList.add(line);
        undoList.add(line2);
    }

    @FXML
    protected void undo() {
        clearCanvas();
        for (Line line: undoList) {
            undoList.remove(line);
            System.out.println("dibujando linea");
            gc.strokeLine(line.getX1(), line.getX2(), line.getY1(), line.getY2());
            redoList.add(line);
        }
    }

    @FXML
    protected void redo(){
        clearCanvas();
        for (Line line: redoList) {
            System.out.println("Dibujando linea");
            gc.setStroke(javafx.scene.paint.Color.BLACK); // Set line color
            gc.strokeLine(line.getX1(), line.getX2(), line.getY1(), line.getY2());
            undoList.add(line);
        }

    }

    private void clearCanvas() {
        GraphicsContext gc = paneDibujo.getGraphicsContext2D();
        gc.clearRect(0, 0, paneDibujo.getWidth(), paneDibujo.getHeight());
    }
}