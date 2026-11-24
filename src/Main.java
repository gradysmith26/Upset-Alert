public class Main {
    public static void main(String[] args) {
        try {
            String json = ScoreboardClient.fetchScoreboard();
            ScoreboardParser.printGames(json);
            System.out.println("Successfully fetched " + json.length() + " characters of basketball data.");
        } catch (Exception e) {
            System.out.println("Error fetching scoreboard: " + e.getMessage());
        }
    }
}