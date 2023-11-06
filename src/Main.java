public class Main {
    public static void main(String[] args) {
        WeatherView view = new WeatherView();
        WeatherService weatherService = new WeatherService();
        WeatherController weatherController = new WeatherController(view, weatherService);

        view.setWeatherController(weatherController);
        view.show();
    }
}
