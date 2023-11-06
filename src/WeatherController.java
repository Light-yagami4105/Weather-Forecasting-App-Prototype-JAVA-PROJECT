public class WeatherController {
    private WeatherView view;
    private WeatherService weatherService;

    public WeatherController(WeatherView view, WeatherService weatherService) {
        this.view = view;
        this.weatherService = weatherService;
    }

    public void searchWeather(String locationName) {
        WeatherData weatherData = weatherService.getWeatherData(locationName);
        view.displayWeatherData(weatherData);
    }
}
