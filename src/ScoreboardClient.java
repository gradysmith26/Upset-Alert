import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public class ScoreboardClient {

    public static String fetchScoreboard() throws IOException, InterruptedException {
         String date = LocalDate.now(ZoneId.of("America/New_York"))
            .format(DateTimeFormatter.BASIC_ISO_DATE);

        String url = "https://site.api.espn.com/apis/site/v2/sports/basketball/mens-college-basketball/scoreboard?dates=" + date + "&groups=50&limit=500";

        HttpClient client = HttpClient.newHttpClient();

        HttpRequest request = HttpRequest.newBuilder().uri(URI.create(url)).GET().build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() != 200) {
            throw new IOException("ESPN returned HTTP " + response.statusCode());
        }

        return response.body();

    }

}