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
            String name1 = team1.path("team").path("displayName").asText();
            String name2 = team2.path("team").path("displayName").asText();
            String score1 = team1.path("score").asText("N/A");
            String score2 = team2.path("score").asText("N/A");
            boolean isFinal = event.path("status").path("type").path("completed").asBoolean();
            System.out.println(name1 + " " + score1 + " - " + name2 + " " + score2 + " | Final: " + isFinal);
            count++;
            if (count == 5) {
                break;
            }
        }
    }
}