package battleship.battleship_common.players;

import java.util.List;
import java.util.Random;

import battleship.battleship_common.Coordinates;
import battleship.battleship_common.board.AttackBoard;
import battleship.battleship_common.board.Board;

public class CpuPlayer extends AbstractPlayer {

    public CpuPlayer() {
        super();
    }

    @Override
    public Coordinates shoot(Coordinates coordinates) {
        AttackBoard attackBoard = getAttackBoard();
        List<Coordinates> hits = attackBoard.getHits();
        Random rnd = new Random();
        int[][] directions = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };
        Coordinates shotCoordinates;
        if (attackBoard.getHits().isEmpty()) {
            // No hits yet, pick random cell
            int row = rnd.nextInt(Board.ROW_SIZE);
            int col = rnd.nextInt(Board.ROW_SIZE);

            shotCoordinates = new Coordinates(row, col);
        } else {
            // Pick random hit and shoot at an adjacent cell
            Coordinates rndHit = hits.get(rnd.nextInt(hits.size()));
            int col = rndHit.getCol(), row = rndHit.getRow();
            do {
                int[] direction = directions[rnd.nextInt(directions.length)];
                row = row + direction[0];
                col = col + direction[1];
                shotCoordinates = Coordinates.of(row, col);
            } while (!Board.isValidCell(row, col) || attackBoard.getAtCell(shotCoordinates) != 'W');

        }

        System.out.println("Shooting at: " + shotCoordinates);
        // addPreviousShot(shotCoordinates);
        return shotCoordinates;
    }

}
