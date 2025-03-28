package battleship.battleship_server;

import jakarta.websocket.server.*;

import java.io.IOException;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

import javax.websocket.*;

@ServerEndpoint("/game")
public class BattleshipServer {
    private static final Map<Session, String> players = new ConcurrentHashMap<>();
    private static final Map<String, List<Session>> games = new ConcurrentHashMap<>();
    private static final Queue<Session> waitingPlayers = new LinkedList<>();

    @OnOpen
    public void onOpen(Session session) throws IOException {
        System.out.println("Player connected: " + session.getId());

        // If there is already a player waiting, match them into a game
        if (!waitingPlayers.isEmpty()) {
            Session player1 = waitingPlayers.poll();
            Session player2 = session;
            String gameId = UUID.randomUUID().toString(); // Unique game ID

            games.put(gameId, Arrays.asList(player1, player2));
            players.put(player1, gameId);
            players.put(player2, gameId);

            player1.getBasicRemote().sendText("MATCHED: You are playing against " + player2.getId());
            player2.getBasicRemote().sendText("MATCHED: You are playing against " + player1.getId());
        } else {
            // No waiting player, add to the queue
            waitingPlayers.add(session);
            session.getBasicRemote().sendText("Waiting for an opponent...");
        }
    }

    @OnMessage
    public void onMessage(String message, Session session) throws IOException {
        String gameId = players.get(session);
        if (gameId == null) {
            session.getBasicRemote().sendText("ERROR: You are not in a game!");
            return;
        }

        // Find the opponent in the same game
        List<Session> gamePlayers = games.get(gameId);
        if (gamePlayers != null) {
            for (Session player : gamePlayers) {
                if (!player.equals(session)) {
                    player.getBasicRemote().sendText("Opponent attacked: " + message);
                    break;
                }
            }
        }
    }

    @OnClose
    public void onClose(Session session) {
        String gameId = players.remove(session);
        if (gameId != null) {
            List<Session> gamePlayers = games.remove(gameId);
            if (gamePlayers != null) {
                for (Session player : gamePlayers) {
                    if (!player.equals(session)) {
                        try {
                            player.getBasicRemote().sendText("Opponent disconnected. Game over.");
                        } catch (IOException ignored) {
                        }
                    }
                }
            }
        } else {
            waitingPlayers.remove(session);
        }
        System.out.println("Player disconnected: " + session.getId());
    }
}
