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
import javafx.geometry.Point2D;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

public class HelloController implements Initializable {

    @FXML
    private Canvas paneDibujo;
    private final List<Line> undoList = new ArrayList<>();
    private Point2D savedPoint = null;
    private GraphicsContext gc;

    private final List<Line> redoList = new ArrayList<>();

    @FXML
    private void handlePrimaryClick(MouseEvent event) {

        Point2D clickPoint = new Point2D(event.getX(), event.getY());


        if (savedPoint == null) {
            savedPoint = clickPoint;
        } else {
            Line line = new Line(clickPoint.getX() ,clickPoint.getY(), savedPoint.getX(),savedPoint.getY());
            gc.setStroke(Color.RED);
            gc.strokeLine(line.getX1(),line.getY1(),line.getX2(),line.getY2());
            undoList.add(line);
            redoList.clear();

            savedPoint = null;
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Get the GraphicsContext of the Canvas
        gc = paneDibujo.getGraphicsContext2D();
        // Drawing a line
        gc.setStroke(javafx.scene.paint.Color.RED); // Set line color
        gc.setLineWidth(4); // Set line width
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
                gc.setStroke(javafx.scene.paint.Color.RED); // Set line color
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
            gc.setStroke(javafx.scene.paint.Color.RED);
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