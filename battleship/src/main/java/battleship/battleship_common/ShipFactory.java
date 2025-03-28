package battleship.battleship_common;

import battleship.battleship_common.ships.Battleship;
import battleship.battleship_common.ships.Carrier;
import battleship.battleship_common.ships.Cruiser;
import battleship.battleship_common.ships.Destroyer;
import battleship.battleship_common.ships.Ship;
import battleship.battleship_common.ships.Submarine;

public class ShipFactory {

    public static Ship createShip(String name) {
        Ship ship = null;
        switch (name) {
            case "Carrier":
                ship = new Carrier();
                break;
            case "Battleship":
                ship = new Battleship();
                break;
            case "Destroyer":
                ship = new Destroyer();
                break;
            case "Cruiser":
                ship = new Cruiser();
                break;
            case "Submarine":
                ship = new Submarine();
                break;
        }

        return ship;
    }

}
