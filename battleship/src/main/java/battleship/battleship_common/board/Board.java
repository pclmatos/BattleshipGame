package battleship.battleship_common.board;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import battleship.battleship_common.Coordinates;
import battleship.battleship_common.ships.Ship;

public abstract class Board {

    public static final int ROW_SIZE = 10;
    public static final List<Character> SHIP_CHARS = List.of('C', 'c', 'S', 'B', 'D');

    private char[][] board;
    private final HashMap<Character, List<Coordinates>> ships;

    public Board() {
        this.board = new char[ROW_SIZE][ROW_SIZE];
        this.ships = new HashMap<>();
        for (int i = 0; i < ROW_SIZE; i++) {
            for (int j = 0; j < ROW_SIZE; j++) {
                this.board[i][j] = 'W';
            }
        }
    }

    public void placeShip(Ship ship, Coordinates start, Coordinates end) {
        char initial = ship.getName().charAt(0);

        List<Coordinates> shipCoords = new LinkedList<>();

        if (isHorizontal(start, end)) {
            int startCol = Math.min(start.getCol(), end.getCol());
            int endCol = Math.max(start.getCol(), end.getCol());

            // Mark ship horizontally
            for (int i = startCol; i <= endCol; i++) {
                this.board[start.getRow()][i] = initial;
                shipCoords.add(new Coordinates(start.getRow(), i));
            }
            this.ships.put(ship.getName().charAt(0), shipCoords);

            // Mark surrounding cells
            for (int i = startCol - 1; i <= endCol + 1; i++) {
                for (int j = start.getRow() - 1; j <= start.getRow() + 1; j++) {
                    if (isValidCell(j, i) && this.board[j][i] == 'W') {
                        this.board[j][i] = 'r';
                    }
                }
            }

            // ships.put(ship.getName(), []);
        } else {
            int startRow = Math.min(start.getRow(), end.getRow());
            int endRow = Math.max(start.getRow(), end.getRow());

            // Mark ship vertically
            for (int i = startRow; i <= endRow; i++) {
                this.board[i][start.getCol()] = initial;
                shipCoords.add(new Coordinates(i, start.getCol()));
            }
            this.ships.put(ship.getName().charAt(0), shipCoords);

            // Mark surrounding cells
            for (int i = startRow - 1; i <= endRow + 1; i++) {
                for (int j = start.getCol() - 1; j <= start.getCol() + 1; j++) {
                    if (isValidCell(i, j) && this.board[i][j] == 'W') {
                        this.board[i][j] = 'r';
                    }
                }
            }
        }
    }

    private boolean isHorizontal(Coordinates start, Coordinates end) {
        return start.getRow() == end.getRow();
    }

    public boolean isValidCell(int row, int col) {
        return row >= 0 && row < ROW_SIZE && col >= 0 && col < ROW_SIZE;
    }

    // Helper method to check if a cell is within the board boundaries
    public static boolean isValidCell(Coordinates coordinates) {
        int row = coordinates.getRow(), col = coordinates.getCol();
        return row >= 0 && row < ROW_SIZE && col >= 0 && col < ROW_SIZE;
    }

    public void printBoard() {
        System.out.print("   ");
        for (int i = 0; i < ROW_SIZE; i++) {
            System.out.printf("  %d ", i);
        }
        System.out.println();

        for (int i = 0; i < ROW_SIZE; i++) {
            System.out.printf("%d ", i);
            for (int j = 0; j < ROW_SIZE; j++) {
                System.out.printf(" | %c", board[i][j] == 'W' || board[i][j] == 'r' ? ' ' : board[i][j]);
            }
            System.out.println(" |");

            System.out.print("  ");
            for (int j = 0; j < ROW_SIZE; j++) {
                System.out.print("----");
            }
            System.out.println("--");
        }
    }

    public char[][] getBoard() {
        return board;
    }

    public char getAtCell(Coordinates coordinates) {
        if (!isValidCell(coordinates.getRow(), coordinates.getCol())) {
            return 'X';
        }
        return this.board[coordinates.getRow()][coordinates.getCol()];
    }

    protected boolean isValidPosition(Coordinates start, Coordinates end) {
        char[][] board = getBoard();
        if (start.getRow() == end.getRow()) {
            for (int i = start.getCol(); i < end.getCol(); i++) {
                char curr = board[start.getRow()][i];
                if (curr != 'W') {
                    return false;
                }
            }

            for (int i = start.getCol() - 1; i <= end.getCol() + 1; i++) {
                for (int j = start.getRow() - 1; j <= start.getRow() + 1; j++) {
                    if (isValidCell(j, i) && this.board[j][i] != 'W') {
                        return false;
                    }
                }
            }
        } else {
            for (int i = start.getRow(); i < end.getRow(); i++) {
                char curr = board[i][start.getCol()];
                if (curr != 'W') {
                    return false;
                }
            }

            for (int i = start.getRow() - 1; i <= end.getCol() + 1; i++) {
                for (int j = start.getCol() - 1; j <= start.getCol() + 1; j++) {
                    if (isValidCell(i, j) && this.board[i][j] != 'W') {
                        return false;
                    }
                }
            }
        }

        return true;
    }

    public void setCell(Coordinates coordinates, char c) {
        this.board[coordinates.getRow()][coordinates.getCol()] = c;
    }

    public boolean removeShipCell(Coordinates c, char atCell) {
        if (ships.containsKey(atCell)) {
            List<Coordinates> shipCoordinates = ships.get(atCell);
            if (shipCoordinates.contains(c)) {
                shipCoordinates.remove(c);
            }
            if (shipCoordinates.isEmpty()) {
                ships.remove(atCell);
                return true;
            }
        }

        return false;
    }

    public HashMap<Character, List<Coordinates>> getShips() {
        return ships;
    }
}
