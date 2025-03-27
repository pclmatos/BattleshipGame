package battleship.battleship_common.board;

import battleship.battleship_common.Coordinates;

public class DefenseBoard extends Board {

    public DefenseBoard() {
        super();
    }

    /**
     * Return 0 if miss, 1 if hit and 2 if ship is sinked
     * 
     * @param coordinates
     * @return
     */
    public int receiveShot(Coordinates coordinates) {
        char atCell = getAtCell(coordinates);
        boolean hit = Board.SHIP_CHARS.contains(atCell);

        if (hit) {
            boolean sink = removeShipCell(coordinates, atCell);
            setCell(coordinates, '.');
            if (sink) {
                System.out.println("SINK " + atCell);
                return 2;
            } else {
                System.out.println("HIT");
                return 1;
            }
        }
        System.out.println("WATER");
        return 0;
    }

}
