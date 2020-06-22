package com.imaginarium.gameoflife;

import java.util.Arrays;

import com.imaginarium.gameoflife.components.Cell;
import com.imaginarium.gameoflife.components.PrimordialSea;
import com.imaginarium.gameoflife.enums.BirthType;
import com.imaginarium.gameoflife.enums.CellState;

import javafx.stage.Stage;

public class LogicOfLife {
    
    public void giveBirth(PrimordialSea world, BirthType birthType) {
        world.getChildren().clear();
        Cell[][] seaCells = world.getSeaCells();
        switch (birthType) {
            case RANDOM:
                for (int y = 0; y < world.getSEACELLS_Y(); y++) {
                    for (int x = 0; x < world.getSEACELLS_X(); x++) {
                        Cell newCell = new Cell(x, y, world.getCellSize());
                        newCell.setState(Math.random() < 0.10 ? CellState.ALIVE : CellState.DEAD);
                        newCell.setRenderState(newCell.getState());
                        seaCells[x][y] = newCell;
                        world.getChildren().add(newCell);
						
						render(newCell);
                    }
                }
                break;

            case EXPLODER:
                for (int y = 0; y < world.getSEACELLS_Y(); y++) {
                    for (int x = 0; x < world.getSEACELLS_X(); x++) {
                        Cell newCell = new Cell(x, y, world.getCellSize());
                        newCell.setState(CellState.DEAD);
                        newCell.setRenderState(newCell.getState());
                        seaCells[x][y] = newCell;
                        world.getChildren().add(newCell);
						
						render(newCell);
                    }
                }

                Cell cell = world.getSingleCell((((int) Stage.getWindows().get(0).getWidth() / 2) / world.getCellSize()) - 3, (((int) Stage.getWindows().get(0).getHeight() / 2) / world.getCellSize()) - 3);
                int[] relativeCoords = new int[] {
                    -2, -2,
                    -2, -1,
                    -2, 0,
                    -2, 1,
                    -2, 2,
                    -1, -2,
                    -1, -1,
                    -1, 0,
                    -1, 1,
                    -1, 2,
                    0, -2,
                    0, -1,
                    0, 0,
                    0, 1,
                    0, 2,
                    1, -2,
                    1, -1,
                    1, 0,
                    1, 1,
                    1, 2,
                    2, -2,
                    2, -1,
                    2, 0,
                    2, 1,
                    2, 2
                };

                for (int i = 0; i < relativeCoords.length; i++) {
                    int dx = relativeCoords[i];
                    int dy = relativeCoords[++i];
        
                    int neighbourX = (int) cell.getTranslateX() / world.getCellSize() + dx;
                    int neighbourY = (int) cell.getTranslateY() / world.getCellSize() + dy;
        
                    if (i == 1 || i == 3 || i == 5 || i == 7 || i == 9 || i == 21 || i == 29
                        || i == 41 || i == 43 || i == 45 || i == 47 || i == 49) {
                            Cell neighbourCell = world.getSingleCell(neighbourX, neighbourY);
                            neighbourCell.setState(CellState.ALIVE);
                            neighbourCell.setRenderState(CellState.ALIVE);
                            render(neighbourCell);
                    }
                }

                break;
            
            default:
                break;
        }
    }

    public void live(PrimordialSea world) {
        Arrays.stream(world.getSeaCells()).forEach(array ->
                Arrays.stream(array).forEach(cell ->
                cell.setRenderState(cell.getState())));
        for (int y = 0; y < world.getSEACELLS_Y(); y++) {
            for (int x = 0; x < world.getSEACELLS_X(); x++) {
                Cell cell = world.getSingleCell(x, y);
                int liveNeighbours = checkNeighbouringLife(cell, world);

                switch (cell.getState()) {
                    case ALIVE:
                        if (liveNeighbours < 2 || liveNeighbours > 3) {
                            cell.setState(CellState.DEAD);
                        }
                        break;
                    
                    case DEAD:
                        if (liveNeighbours == 3) {
                            cell.setState(CellState.ALIVE);
                        }
                        break;
                }

                render(cell);
            }
        }
    }

    private void render(Cell cell) {
        cell.setFill(cell.renderAlive() ? cell.getLIVE_COLOUR()
        : cell.getDEAD_COLOUR());
    }

    private int checkNeighbouringLife(Cell cell, PrimordialSea world) {
        int liveNeighbours = 0;
        
        /* Coordinates (x, y) relative to check adjacent tiles from
        -1 / 1 x is one tile left / right, -1 / 1 y is one tile up / down */
        int[] relativeCoords = new int[] {
            -1, -1,
            -1, 0,
            -1, 1,
            0, -1,
            0, 1,
            1, -1,
            1, 0,
            1, 1
        };

        for (int i = 0; i < relativeCoords.length; i++) {
            int dx = relativeCoords[i];
            int dy = relativeCoords[++i];

            int neighbourX = (int) cell.getTranslateX() / world.getCellSize() + dx;
            int neighbourY = (int) cell.getTranslateY() / world.getCellSize() + dy;

            if (neighbourX >= 0 && neighbourX < world.getSEACELLS_X()
                    && neighbourY >= 0 && neighbourY < world.getSEACELLS_Y()) {
                liveNeighbours += world.getSingleCell(neighbourX, neighbourY).renderAlive() ? 1 : 0;
            }
        }

        return liveNeighbours;
    }
}