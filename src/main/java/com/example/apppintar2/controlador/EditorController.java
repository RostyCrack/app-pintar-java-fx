package com.example.apppintar2.controlador;

import com.example.apppintar2.modelo.Line;
import com.example.apppintar2.modelo.ModeladorLineas;
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

public class EditorController implements Initializable {

    @FXML
    private Canvas paneDibujo;
    private Point2D savedPoint = null;
    private GraphicsContext gc;

    private ModeladorLineas instanciaModelado;


    @FXML
    private void handlePrimaryClick(MouseEvent event) {

        Point2D clickPoint = new Point2D(event.getX(), event.getY());

        if (savedPoint == null) {
            savedPoint = clickPoint;
        } else {
            instanciaModelado.drawLine(clickPoint.getX() ,clickPoint.getY(), savedPoint.getX(),savedPoint.getY());
            gc.setStroke(Color.RED);
            gc.strokeLine(clickPoint.getX() ,clickPoint.getY(), savedPoint.getX(),savedPoint.getY());
            savedPoint = null;
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        gc = paneDibujo.getGraphicsContext2D();
        gc.setStroke(javafx.scene.paint.Color.RED);
        gc.setLineWidth(4);
        instanciaModelado = new ModeladorLineas();

    }

    @FXML
    protected void undo() {
        clearCanvas();
        ArrayList<Line> iterator = instanciaModelado.getUndoList();

        for (Line line : iterator) {
            gc.setStroke(javafx.scene.paint.Color.RED); // Set line color
            gc.strokeLine(line.getX1(), line.getY1(), line.getX2(), line.getY2());
        }
    }

    @FXML
    protected void redo(){
            double coordenadas[] = instanciaModelado.getRedoList();
            gc.setStroke(javafx.scene.paint.Color.RED);
            gc.strokeLine(coordenadas[0], coordenadas[1], coordenadas[2], coordenadas[3]);

    }

    private void clearCanvas() {
        GraphicsContext gc = paneDibujo.getGraphicsContext2D();
        gc.clearRect(0, 0, paneDibujo.getWidth(), paneDibujo.getHeight());
    }
}