import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.JSONObject;

class Location {
    private static final String GEOLOCATION_URL = "https://ip-api.com/json/";

    public static void main(String[] args) {
        try {
            String jsonResponse = getGeolocationResponse();
            parseAndPrintLocation(jsonResponse);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static String getGeolocationResponse() throws Exception {
        URL url = new URL(GEOLOCATION_URL);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");

        try {
            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                StringBuilder response = new StringBuilder();
                String inputLine;

                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();
                return response.toString();
            } else {
                throw new Exception("Failed to connect: " + responseCode);
            }
        } finally {
            connection.disconnect();
        }
    }

    private static void parseAndPrintLocation(String jsonResponse) {
        JSONObject jsonObject = new JSONObject(jsonResponse);

        if ("success".equals(jsonObject.optString("status"))) {
            String city = jsonObject.optString("city", "N/A");
            String region = jsonObject.optString("regionName", "N/A");
            String country = jsonObject.optString("country", "N/A");
            double lat = jsonObject.optDouble("lat", 37.7749);
            double lon = jsonObject.optDouble("lon", -122.4194);

            System.out.println("City: " + city);
            System.out.println("Region: " + region);
            System.out.println("Country: " + country);
            System.out.println("Latitude: " + lat);
            System.out.println("Longitude: " + lon);
        } else {
            System.out.println("Location not found.");
        }
    }
}
