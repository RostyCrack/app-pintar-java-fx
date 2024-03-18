package com.example.apppintar2.modelo;

import javafx.scene.paint.Color;

import java.util.ArrayList;

public class ModeladorLineas {

    private final ArrayList<Line> undoList = new ArrayList<>();
    private final ArrayList<Line> redoList = new ArrayList<>();

    public ArrayList<Line> getUndoList() {
        if (undoList != null && !undoList.isEmpty()) {
            if (undoList.size() == 1) {
                redoList.add(undoList.get(0));
                undoList.remove(0);

            } else {
                Line removedLine = undoList.get(undoList.size() - 1);
                redoList.add(removedLine);
                undoList.remove(removedLine);
            }
        }
        return undoList;
    }

    public void drawLine(double x1, double y1, double x2, double y2){
            Line line = new Line( x1 ,y1, x2,y2);
            undoList.add(line);
            redoList.clear();
    }

    public double[] getRedoList(){
        double[] coordenadas = new double[4];

        if (redoList != null && !redoList.isEmpty()) {
            Line line = redoList.get(redoList.size() - 1);
            coordenadas[0] = line.getX1();
            coordenadas[1] = line.getY1();
            coordenadas[2] = line.getX2();
            coordenadas[3] = line.getY2();
            undoList.add(line);
            redoList.remove(line);
        }
        return coordenadas;
    }

}
