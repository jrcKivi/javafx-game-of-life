package com.imaginarium.gameoflife.components;

import com.imaginarium.gameoflife.enums.CellState;

import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;

public class Cell extends Rectangle {
    
    private int liveNeighbours;
    private CellState renderState;
    private CellState state;
    private final Paint LIVE_COLOUR = Color.PINK;
    private final Paint DEAD_COLOUR = Color.rgb(0, 0, 0, 0);

    public Cell(int x, int y, int cellSize) {
        setTranslateX(x * cellSize);
        setTranslateY(y * cellSize);
        setWidth(cellSize - 2);
        setHeight(cellSize - 2);
        setStrokeWidth(1.5);
        setStroke(Color.DARKGREY);
        setFill(DEAD_COLOUR);
    }
    
    public boolean renderAlive() {
        return this.renderState == CellState.ALIVE ? true : false;
    }

    public CellState getRenderState() {
        return this.renderState;
    }

    public void setRenderState(CellState state) {
        this.renderState = state;
    }

    public boolean alive() {
        return this.state == CellState.ALIVE ? true : false;
    }

    public CellState getState() {
        return this.state;
    }

    public void setState(CellState state) {
        this.state = state;
    }

    public Paint getLIVE_COLOUR() {
        return this.LIVE_COLOUR;
    }

    public Paint getDEAD_COLOUR() {
        return this.DEAD_COLOUR;
    }

    public int getLiveNeighbours() {
        return this.liveNeighbours;
    }

    public void setLiveNeighbours(int liveNeighbours) {
        this.liveNeighbours = liveNeighbours;
    }
}