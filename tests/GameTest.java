import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class GameTest {

    @Test
    public void testUnrankedBeatsRanked() {

        Game game = new Game("Purdue", "Indiana", 73, 78, 4, 0, true);
        assertTrue(game.isUpset());

    }

    @Test
    public void testHigherRankedTeamWins() {

        Game game = new Game("Duke", "Kentucky", 85, 76, 2, 18, true);
        assertFalse(game.isUpset());

    }

    @Test
    public void testLowerRankedTeamWins() {
        
        Game game = new Game("Michigan", "Ohio State", 71, 69, 22, 5, true);
        assertTrue(game.isUpset());

    }

    @Test
    public void testGameNotFinal() {

        Game game = new Game("Purdue", "Indiana", 73, 78, 4, 0, false);
        assertFalse(game.isUpset());

    }

    @Test
    public void testUnrankedWinnerlistedFirst() {
        
        Game game = new Game("Villinova", "Michigan", 79, 73, 0, 18, true);
        assertTrue(game.isUpset());

    }

    @Test
    public void testUnrankedTeamLoses() {

        Game game = new Game("Duke", "Queens", 90, 63, 5, 0, true);
        assertFalse(game.isUpset());

    }

    @Test
    public void testTwoUnrankedTeams() {

        Game game = new Game("Team A", "Team B", 70, 80, 0, 0, true);
        assertFalse(game.isUpset());

    }

    @Test
    public void testSameRank() {

        Game game = new Game("Team A", "Team B", 80, 70, 10, 10, true);
        assertFalse(game.isUpset());

    }

    @Test
    public void testTiedScore() {

        Game game = new Game("Team A", "Team B", 65, 71, 3, 20, true);
        assertTrue(game.isUpset());

    }

    @Test
    public void testLowerRankedWinnerListedSecond() {

        Game game = new Game("Team A", "Team B", 65, 71, 3, 20, true);
        assertTrue(game.isUpset());

    }

}