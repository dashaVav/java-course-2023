package edu.project2;

import lombok.Data;

@Data
public class Cell {
    private int col;
    private int row;
    private Cell previous;
    private Type type;

    public enum Type {
        WALL, PASSAGE, WAY
    }

    public Cell(int col, int row) {
        this.col = col;
        this.row = row;
        type = Type.WALL;
    }
}
