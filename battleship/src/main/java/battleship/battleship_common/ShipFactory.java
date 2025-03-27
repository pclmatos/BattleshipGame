package battleship.battleship_common;

import battleship.battleship_common.ships.Battleship;
import battleship.battleship_common.ships.Carrier;
import battleship.battleship_common.ships.Cruiser;
import battleship.battleship_common.ships.Destroyer;
import battleship.battleship_common.ships.Ship;
import battleship.battleship_common.ships.Submarine;

public class ShipFactory {

    public static Ship createShip(String name) {

        switch (name) {
            case "Carrier":
                return new Carrier();
            case "Battleship":
                return new Battleship();
            case "Destroyer":
                return new Destroyer();
            case "Cruiser":
                return new Cruiser();
            case "Submarine":
                return new Submarine();
        }

        return null;
    }

}
