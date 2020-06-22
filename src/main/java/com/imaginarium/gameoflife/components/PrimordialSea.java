package com.imaginarium.gameoflife.components;

import javafx.scene.layout.StackPane;

public class PrimordialSea extends StackPane {
    
    private int cellSize = 10;
    private final int SEACELLS_X;
    private final int SEACELLS_Y;
    private final int WINDOW_X;
    private final int WINDOW_Y;

    private final Cell[][] seaCells;

    public PrimordialSea(int seaCellsX, int seaCellsY) {
        SEACELLS_X = seaCellsX;
        SEACELLS_Y = seaCellsY;
        WINDOW_X = SEACELLS_X * cellSize;
        WINDOW_Y = SEACELLS_Y * cellSize;
        seaCells = new Cell[SEACELLS_X][SEACELLS_Y];
    }

    public int getCellSize() {
        return this.cellSize;
    }

    public void setCellSize(int newSize) {
        cellSize = newSize;
    }

    public int getSEACELLS_X() {
        return this.SEACELLS_X;
    }

    public int getSEACELLS_Y() {
        return this.SEACELLS_Y;
    }

    public int getWINDOW_X() {
        return this.WINDOW_X;
    }

    public int getWINDOW_Y() {
        return this.WINDOW_Y;
    }

    public Cell getSingleCell(int x, int y) {
        return seaCells[x][y];
    }

    public Cell[][] getSeaCells() {
        return this.seaCells;
    }
}