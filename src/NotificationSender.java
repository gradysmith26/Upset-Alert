import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class NotificationSender {

    public static void send(String message) throws Exception {

        String topic = System.getenv("NTFY_TOPIC");

        if (topic == null || topic.isBlank()) {
            throw new IllegalStateException("NTFY_TOPIC is not set");
        }

        String url = "https://ntfy.sh/" + topic;

        HttpClient client = HttpClient.newHttpClient();

        HttpRequest request = HttpRequest.newBuilder().uri(URI.create(url))
        .header("Title", "Upset Alert").POST(HttpRequest.BodyPublishers.ofString(message)).build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() != 200) {
            throw new Exception("Notification failed: " + response.statusCode());
        }

        System.out.println("Notification sent successfully!");
    }

    public static void main(String[] args) throws Exception {
        send("Test notification sent from Java!");
    }

}