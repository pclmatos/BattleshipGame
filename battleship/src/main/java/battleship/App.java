package battleship;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import battleship.battleship_common.Coordinates;
import battleship.battleship_common.board.AttackBoard;
import battleship.battleship_common.board.DefenseBoard;
import battleship.battleship_common.players.CpuPlayer;
import battleship.battleship_common.players.HumanPlayer;

public class App {
    public static void main(String[] args) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        HumanPlayer player = new HumanPlayer();
        CpuPlayer cpu = new CpuPlayer();

        AttackBoard playerAttackBoard = player.getAttackBoard();
        DefenseBoard playerDefenseBoard = player.getDefenseBoard();

        System.out.println("--------------- Defense Board -----------------");
        playerDefenseBoard.printBoard();

        AttackBoard cpuAttackBoard = cpu.getAttackBoard();
        DefenseBoard cpuDefenseBoard = cpu.getDefenseBoard();

        System.out.println("--------------- CPU Board -----------------");
        cpuDefenseBoard.printBoard();

        while (!playerDefenseBoard.getShips().isEmpty() && !cpuDefenseBoard.getShips().isEmpty()) {

            try {
                System.out.println();
                for (int i = 0; i < 3; i++) {
                    System.out.println("--------------- Attack Board ----------------");
                    playerAttackBoard.printBoard();
                    int row, col;
                    Coordinates coordinates;
                    do {
                        String read;
                        do {
                            System.out.print("Fila: ");
                            read = reader.readLine();
                            if (!read.matches("[0-9]")) {
                                System.out.println("Número inválido. Tente novamente!");
                            }
                        } while (!read.matches("[0-9]"));
                        // while(read)
                        row = Integer.parseInt(read);
                        do {
                            System.out.print("Coluna: ");
                            read = reader.readLine();
                            if (!read.matches("[0-9]")) {
                                System.out.println("Número inválido. Tente novamente!");
                            }
                        } while (!read.matches("[0-9]"));
                        col = Integer.parseInt(read);
                        coordinates = Coordinates.of(row, col);
                    } while (playerAttackBoard.getAtCell(coordinates) != 'W');

                    int shotResult = cpuDefenseBoard.receiveShot(coordinates);
                    playerAttackBoard.markShotResult(coordinates, shotResult);

                    if (cpuDefenseBoard.getShips().isEmpty()) {
                        break;
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

            if (cpuDefenseBoard.getShips().isEmpty()) {
                break;
            }
            try {
                System.out.println("Waiting for CPU...");
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            for (int i = 0; i < 3; i++) {
                Coordinates shoot = cpu.shoot(Coordinates.of(-1, -1));
                int shotResult = playerDefenseBoard.receiveShot(shoot);
                cpuAttackBoard.markShotResult(shoot, shotResult);
            }
            System.out.println("--------------- Defense Board ----------------");
            playerDefenseBoard.printBoard();
        }

        if (playerDefenseBoard.getShips().isEmpty()) {
            System.out.println("CPU WON!!");
        } else {
            System.out.println("YOU WIN!!");
        }

        // playerDefenseBoard.printBoard();
        // cpuAttackBoard.printBoard();

    }

}
