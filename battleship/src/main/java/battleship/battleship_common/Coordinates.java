package battleship.battleship_common;

import java.util.Objects;

public class Coordinates {

    private int row, col;

    public Coordinates(int row, int col) {
        this.row = row;
        this.col = col;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    @Override
    public String toString() {
        return "Coordinates [row=" + row + ", col=" + col + "]";
    }

    @Override
    public int hashCode() {
        return Objects.hash(row, col);
    }

    @Override
    public boolean equals(Object obj) {
        return this.row == ((Coordinates) obj).row && this.col == ((Coordinates) obj).col;
    }

    public static Coordinates of(int row, int col) {
        return new Coordinates(row, col);
    }

}
