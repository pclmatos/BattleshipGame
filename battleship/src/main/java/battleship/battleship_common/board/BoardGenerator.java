package battleship.battleship_common.board;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import battleship.battleship_common.Coordinates;
import battleship.battleship_common.ShipFactory;
import battleship.battleship_common.ships.Ship;

public class BoardGenerator {

    public static DefenseBoard generateRandomBoard() {
        Random rnd = new Random();
        DefenseBoard board = new DefenseBoard();
        List<Ship> ships = new LinkedList<>();

        Ship cruiser = ShipFactory.createShip("Cruiser");
        Ship battleship = ShipFactory.createShip("Battleship");
        Ship destroyer = ShipFactory.createShip("Destroyer");
        Ship carrier = ShipFactory.createShip("Carrier");
        Ship submarine = ShipFactory.createShip("Submarine");

        ships.add(cruiser);
        ships.add(submarine);
        ships.add(carrier);
        ships.add(destroyer);
        ships.add(battleship);

        for (int i = 0; i < 5; i++) {
            Ship toPlace = ships.get(rnd.nextInt(ships.size()));
            Coordinates start;
            Coordinates end;
            do {
                boolean orientation = rnd.nextDouble(1) > 0.5 ? true : false;
                int startRow = rnd.nextInt(Board.ROW_SIZE);
                int startCol = rnd.nextInt(Board.ROW_SIZE);
                start = new Coordinates(startRow, startCol);
                if (orientation) {
                    if (startCol + toPlace.getSize() > Board.ROW_SIZE) {
                        start = new Coordinates(startRow, startCol - toPlace.getSize() + 1);
                        end = new Coordinates(startRow, startCol);
                    } else {
                        end = new Coordinates(startRow, startCol + toPlace.getSize() - 1);
                    }
                } else {
                    if (startRow + toPlace.getSize() > Board.ROW_SIZE) {
                        start = new Coordinates(startRow - toPlace.getSize() + 1, startCol);
                        end = new Coordinates(startRow, startCol);
                    } else {
                        end = new Coordinates(startRow + toPlace.getSize() - 1, startCol);
                    }
                }
            } while (!board.isValidPosition(board, start, end));

            board.placeShip(toPlace, start, end);
            ships.remove(toPlace);
        }
        return board;
    }

}
