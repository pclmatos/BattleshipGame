package battleship.battleship_common.players;

import java.util.UUID;

import battleship.battleship_common.Coordinates;
import battleship.battleship_common.board.AttackBoard;
import battleship.battleship_common.board.Board;
import battleship.battleship_common.board.BoardGenerator;
import battleship.battleship_common.board.DefenseBoard;

public abstract class AbstractPlayer {

    private AttackBoard attackBoard;
    private DefenseBoard defenseBoard;
    private UUID playerId;

    public AbstractPlayer() {
        this.playerId = UUID.randomUUID();
        this.defenseBoard = BoardGenerator.generateRandomBoard();
        this.attackBoard = new AttackBoard();
    }

    public boolean receiveShot(Coordinates coordinates) {
        char valueInCoordinates = this.getDefenseBoard().getAtCell(coordinates);
        return Board.SHIP_CHARS.contains(valueInCoordinates);
    }

    public UUID getPlayerId() {
        return playerId;
    }

    public AttackBoard getAttackBoard() {
        return attackBoard;
    }

    public DefenseBoard getDefenseBoard() {
        return defenseBoard;
    }

    public abstract Coordinates shoot(Coordinates coordinates);
}
