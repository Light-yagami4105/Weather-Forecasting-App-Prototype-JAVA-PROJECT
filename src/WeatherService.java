import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class WeatherService {
    private static final String API_KEY = "927e6e13e80d4a28a9f92818231010";
    private static final String API_URL = "http://api.weatherapi.com/v1/history.json?key=927e6e13e80d4a28a9f92818231010&q=India&dt=2010-01-01";

    public WeatherData getWeatherData(String locationName) {
        try {
            URL url = new URL(String.format(API_URL, API_KEY, locationName));
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String inputLine;
            StringBuilder response = new StringBuilder();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            JSONObject jsonResponse = new JSONObject(response.toString());

            if (jsonResponse.has("location") && jsonResponse.has("current")) {
                WeatherData weatherData = new WeatherData();
                weatherData.setLocationName(jsonResponse.getJSONObject("location").getString("name"));
                weatherData.setWeatherInfo(jsonResponse.getJSONObject("current").getJSONObject("condition").getString("text"));
                // You can extract more weather data attributes as needed and set them in the WeatherData object

                return weatherData;
            } else {
                System.out.println("Error: Invalid API response");
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
