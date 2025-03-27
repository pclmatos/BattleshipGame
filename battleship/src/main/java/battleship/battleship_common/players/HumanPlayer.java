package battleship.battleship_common.players;

import battleship.battleship_common.Coordinates;

public class HumanPlayer extends AbstractPlayer {

    private String name;

    public HumanPlayer(String name) {
        super();
        this.name = name;
    }

    @Override
    public Coordinates shoot(Coordinates coordinates) {
        // TODO Auto-generated method stub
        return coordinates;
    }

    public String getName() {
        return name;
    }

}
