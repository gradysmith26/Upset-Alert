public class Game {
    private String team1;
    private String team2;

    private int score1;
    private int score2;
    
    private int rank1;
    private int rank2;

    private boolean isFinal;

    public Game(String team1, String team2, int score1,
    int score2, int rank1, int rank2, boolean isFinal) {

        this.team1 = team1;
        this.team2 = team2;
        this.score1 = score1;
        this.score2 = score2;
        this.rank1 = rank1;
        this.rank2 = rank2;
        this.isFinal = isFinal;

    }

    public boolean isUpset() {
        if (!isFinal) {
            return false;
        }
        if (rank1 == 0) {
            if (rank2 == 0) {
                return false;
            } else {
                return score1 > score2;
            }
        }
        if (rank2 == 0) {
            return score2 > score1;
        }
        if (rank1 < rank2) {
            return score2 > score1;
        }
        if (rank2 < rank1) {
            return score1 > score2;
        }
        return false;
    }
}