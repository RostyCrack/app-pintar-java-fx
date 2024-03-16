package com.example.apppintar2;

import Modelo.Line;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.ResourceBundle;

public class HelloController implements Initializable {

    @FXML
    private Canvas paneDibujo;
    private final List<Line> undoList = new ArrayList<>();

    private GraphicsContext gc;

    private final List<Line> redoList = new ArrayList<>();


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Get the GraphicsContext of the Canvas
        gc = paneDibujo.getGraphicsContext2D();
        // Drawing a line
        gc.setStroke(javafx.scene.paint.Color.BLACK); // Set line color
        gc.setLineWidth(2); // Set line width
        gc.strokeLine(50, 50, 350, 350); // First line
        gc.strokeLine(60, 20, 40, 20); // Second line
        Line line = new Line(50,50,350,350);// Draw line from (50,50) to (350,350)
        Line line2 = new Line(60,20,40,20);
        undoList.add(line);
        undoList.add(line2);
    }

    @FXML
    protected void undo() {

        clearCanvas();
        if (undoList != null && !undoList.isEmpty()) {

            if (undoList.size() == 1) {
                redoList.add(undoList.get(0));
                undoList.remove(0);
            }else{
                Line removedLine = undoList.get(undoList.size() - 1);
                redoList.add(removedLine);
                undoList.remove(removedLine);
            }
            ListIterator<Line> iterator = undoList.listIterator(undoList.size());
            while (iterator.hasPrevious()) {
                Line line = iterator.previous();
                gc.setStroke(javafx.scene.paint.Color.BLACK); // Set line color
                gc.strokeLine(line.getX1(), line.getY1(), line.getX2(), line.getY2());
            }
        }
    }

    @FXML
    protected void redo(){
        if (redoList != null && !redoList.isEmpty()) {
            // Get the last line from the redoList
            Line line = redoList.get(redoList.size() - 1);
            // Set the stroke color to black
            gc.setStroke(javafx.scene.paint.Color.BLACK);
            // Draw the line on the canvas using its coordinates
            gc.strokeLine(line.getX1(), line.getY1(), line.getX2(), line.getY2());
            // Add the line to the undoList for possible undo operation
            undoList.add(line);
            // Remove the line from the redoList
            redoList.remove(line);
        }

    }

    private void clearCanvas() {
        GraphicsContext gc = paneDibujo.getGraphicsContext2D();
        gc.clearRect(0, 0, paneDibujo.getWidth(), paneDibujo.getHeight());
    }
}