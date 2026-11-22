public class Main {
    public static void main(String[] args) {
        Game game1 = new Game("Purdue", "Indiana", 73, 78, 4, 0, true);
        Game game2 = new Game("Duke", "Kentucky", 85, 76, 2, 18, true);
        Game game3 = new Game("Michigan", "Kansas", 71, 69, 22, 5, true);
        Game game4 = new Game("Purdue", "Indiana", 73, 78, 4, 0, false);

        System.out.println("Game 1: " + game1.isUpset());
        System.out.println("Game 2: " + game2.isUpset());
        System.out.println("Game 3: " + game3.isUpset());
        System.out.println("Game 4: " + game4.isUpset());

        try {
            String json = ScoreboardClient.fetchScoreboard();
            System.out.println("Successfully fetched " + json.length() + " characters of basketball data.");
        } catch (Exception e) {
            System.out.println("Error fetching scoreboard: " + e.getMessage());
        }
    }
}