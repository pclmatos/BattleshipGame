package battleship.battleship_common;

import java.util.UUID;

public class Shoot {

    private Coordinates coordinates;
    private UUID playerId;

    public Shoot() {
    }

    public Shoot(UUID playerId, Coordinates coordinates) {
        this.coordinates = coordinates;
        this.playerId = playerId;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public UUID getPlayerId() {
        return playerId;
    }

    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    public void setPlayerId(UUID playerId) {
        this.playerId = playerId;
    }

}
