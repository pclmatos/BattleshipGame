package battleship;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Random;

import battleship.battleship_common.Coordinates;
import battleship.battleship_common.board.AttackBoard;
import battleship.battleship_common.board.DefenseBoard;
import battleship.battleship_common.players.CpuPlayer;
import battleship.battleship_common.players.HumanPlayer;

public class App {
    public static void main(String[] args) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        System.out.print("Clica [1] para jogar contra CPU e [2] para jogar 1vs1: ");
        String optionStr = "";

        try {
            do {
                optionStr = reader.readLine();
            } while (!optionStr.matches("[1-2]"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        int option = Integer.parseInt(optionStr);
        try {

            switch (option) {
                case 1:
                    playAgainstCPU(reader);
                    break;
                case 2:
                    PvP(reader);
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private static void PvP(BufferedReader reader) throws IOException {
        System.out.print("Nome jogador 1: ");
        String namePlayer1 = reader.readLine();

        System.out.print("Nome jogador 2: ");
        String namePlayer2 = reader.readLine();

        HumanPlayer p1 = new HumanPlayer(namePlayer1), p2 = new HumanPlayer(namePlayer2);

        AttackBoard p1AttackBoard = p1.getAttackBoard();
        DefenseBoard p1DefenseBoard = p1.getDefenseBoard();

        AttackBoard p2AttackBoard = p2.getAttackBoard();
        DefenseBoard p2DefenseBoard = p2.getDefenseBoard();

        Random rnd = new Random();

        boolean turn = rnd.nextInt(2) == 0 ? true : false;

        while (!p1DefenseBoard.getShips().isEmpty() && !p2DefenseBoard.getShips().isEmpty()) {
            System.out.println();
            if (turn) {
                System.out.println("Turno " + p1.getName());
                play(p1AttackBoard, p2DefenseBoard, reader);
                turn = false;
            } else {
                System.out.println("Turno " + p2.getName());
                play(p2AttackBoard, p1DefenseBoard, reader);
                turn = true;
            }

        }

        if (p1DefenseBoard.getShips().isEmpty()) {
            System.out.println(p2.getName() + " WON!!");
        } else {
            System.out.println(p1.getName() + " WIN!!");
        }
    }

    private static void playAgainstCPU(BufferedReader reader) throws IOException {
        System.out.println("What is your name?");
        String name = reader.readLine();
        HumanPlayer player = new HumanPlayer(name);
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
    }

    private static boolean play(AttackBoard attack, DefenseBoard defense, BufferedReader reader) {
        try {
            // System.out.println();
            for (int i = 0; i < 3; i++) {
                System.out.println("--------------- Attack Board ----------------");
                attack.printBoard();
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
                } while (attack.getAtCell(coordinates) != 'W');

                int shotResult = defense.receiveShot(coordinates);
                attack.markShotResult(coordinates, shotResult);

                if (defense.getShips().isEmpty()) {
                    return true;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return false;
    }

}
