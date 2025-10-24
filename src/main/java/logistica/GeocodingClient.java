package logistica;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.Optional;

public class GeocodingClient {
    private final String apiKey;
    private static final ObjectMapper MAPPER = new ObjectMapper();
    private static final HttpClient HTTP = HttpClient.newBuilder().connectTimeout(Duration.ofSeconds(20)).build();

    public GeocodingClient(String apiKey) { this.apiKey = apiKey; }

    public Optional<LatLng> geocode(String address) {
        try {
            String enc = URLEncoder.encode(address, StandardCharsets.UTF_8);
            String url = "https://maps.googleapis.com/maps/api/geocode/json?address=" + enc + "&key=" + apiKey;
            HttpRequest req = HttpRequest.newBuilder(URI.create(url)).timeout(Duration.ofSeconds(20)).GET().build();
            HttpResponse<String> resp = HTTP.send(req, HttpResponse.BodyHandlers.ofString());
            if (resp.statusCode() != 200) return Optional.empty();
            JsonNode root = MAPPER.readTree(resp.body());
            if (!root.has("results") || root.get("results").isEmpty()) return Optional.empty();
            JsonNode loc = root.get("results").get(0).get("geometry").get("location");
            return Optional.of(new LatLng(loc.get("lat").asDouble(), loc.get("lng").asDouble()));
        } catch (Exception e) {
            return Optional.empty();
        }
    }
}