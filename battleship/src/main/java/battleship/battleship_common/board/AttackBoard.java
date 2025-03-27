package battleship.battleship_common.board;

import java.util.LinkedList;
import java.util.List;

import battleship.battleship_common.Coordinates;

public class AttackBoard extends Board {

    private final List<Coordinates> hits;

    public AttackBoard() {
        super();
        // this.misses = new LinkedList<>();
        this.hits = new LinkedList<>();
    }

    public void markShotResult(Coordinates coordinates, int result) {
        if (result == 1) {
            setCell(coordinates, '.');
            hits.add(coordinates);
        } else if (result == 0) {
            setCell(coordinates, 'X');
            // misses.add(coordinates);
        } else {
            setCell(coordinates, '.');
            // Get adjacent hits and remove from hits list
            int[][] directions = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };
            // int[] direction = new int[2];
            hits.remove(coordinates);
            System.out.println("Removing hit at: " + coordinates);
            for (int[] dir : directions) {
                removeAllAdjacentHits(coordinates, dir);
            }

        }
    }

    public List<Coordinates> getHits() {
        return hits;
    }

    private void removeAllAdjacentHits(Coordinates coordinates, int[] direction) {
        Coordinates tmp = Coordinates.of(coordinates.getRow() + direction[0], coordinates.getCol() + direction[1]);
        while (isValidCell(tmp.getRow(), tmp.getCol()) && getAtCell(tmp) == '.') {
            System.out.println("Removing hit at: " + tmp);
            hits.remove(tmp);
            tmp = Coordinates.of(tmp.getRow() + direction[0], tmp.getCol() + direction[1]);
        }
    }

    // public List<Coordinates> getMisses() {
    // return misses;
    // }

}
