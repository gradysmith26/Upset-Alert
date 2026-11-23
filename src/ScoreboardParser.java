import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
public class ScoreboardParser {

    public static void printGames(String json) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode root = mapper.readTree(json);
        JsonNode events = root.path("events");
        int count = 0;
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
            if (game.isUpset()) {
                System.out.println("UPSET DETECTED!");
                System.out.println(name1 + " " + points1 + " - " + name2 + " " + points2);
            }

            System.out.println(
                "Game ID: " + gameId + " | " + name1 + " (#" + rank1 + ") " + points1
                + " - " + name2 + " (#" + rank2 + ") " + points2 + " | Final: " + isFinal
            );

            count++;

            if (count == 5) {
                break;
            }

        }
    }
}