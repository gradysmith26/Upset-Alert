import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.HashSet;
import java.util.Set;

public class ScoreboardParser {

    public static void printGames(String json) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode root = mapper.readTree(json);
        JsonNode events = root.path("events");

        Path file = Path.of("data/reported-games.txt");

        Set<String> reportedGames = new HashSet<>();

        if (Files.exists(file)) {
            reportedGames.addAll(Files.readAllLines(file));
        }

        for (JsonNode event : events) {
            JsonNode competitors = event.path("competitions").path(0).path("competitors");
            if (competitors.size() < 2) {
                continue;
            }
            JsonNode team1 = competitors.get(0);
            JsonNode team2 = competitors.get(1);

            String gameId = event.path("id").asText();

            int rank1 = team1.path("curatedRank").path("current").asInt(-1);
            int rank2 = team2.path("curatedRank").path("current").asInt(-1);

            if (rank1 == 99) {
                rank1 = 0;
            }
            if (rank2 == 99) {
                rank2 = 0;
            }

            String name1 = team1.path("team").path("displayName").asText();
            String name2 = team2.path("team").path("displayName").asText();

            boolean isFinal = event.path("status").path("type").path("completed").asBoolean();
            
            int points1 = team1.path("score").asInt(-1);
            int points2 = team2.path("score").asInt(-1);

            if (!isFinal || points1 < 0 || points2 < 0 || rank1 == -1 || rank2 == -1) {
                continue;
            }

            Game game = new Game(name1, name2, points1, points2, rank1, rank2, isFinal);
            if (game.isUpset() && !reportedGames.contains(gameId)) {

                String winner;
                String loser;
                int winningScore;
                int losingScore;
                String winningRank;
                String losingRank;

                if (points1 > points2) {
                    winner = name1;
                    loser = name2;
                    winningScore = points1;
                    losingScore = points2;
                    if (rank1 == 0) {
                        winningRank = "Unranked";
                    } else {
                        winningRank = "#" + rank1;
                    }
                    losingRank = "#" + rank2;
                } else {
                    winner = name2;
                    loser = name1;
                    winningScore = points2;
                    losingScore = points1;
                    if (rank2 == 0) {
                        winningRank = "Unranked";
                    } else {
                        winningRank = "#" + rank2;
                    }
                    losingRank = "#" + rank1;
                }

                String message = winningRank + " " + winner + " Defeat " + losingRank + " " + loser + " " + winningScore + "-" + losingScore;
                NotificationSender.send(message);
                Files.writeString(
                    file, gameId + System.lineSeparator(), StandardOpenOption.CREATE, StandardOpenOption.APPEND
                    );
                reportedGames.add(gameId);
                System.out.println("UPSET DETECTED: " + message);
            }
        }
    }
}