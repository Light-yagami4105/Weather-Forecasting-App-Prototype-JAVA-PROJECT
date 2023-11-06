import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class WeatherView {
    private JFrame frame;
    private JTextField locationTextField;
    private JTextArea weatherInfoTextArea;
    private JButton searchButton;
    private WeatherController weatherController;

    public WeatherView() {
        frame = new JFrame("Weather Forecasting App");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new FlowLayout());

        locationTextField = new JTextField(20);
        searchButton = new JButton("Search");
        weatherInfoTextArea = new JTextArea(10, 30);
        weatherInfoTextArea.setEditable(false);

        inputPanel.add(new JLabel("Enter Location: "));
        inputPanel.add(locationTextField);
        inputPanel.add(searchButton);

        frame.add(inputPanel, BorderLayout.NORTH);
        frame.add(new JScrollPane(weatherInfoTextArea), BorderLayout.CENTER);

        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String locationName = locationTextField.getText();
                weatherController.searchWeather(locationName);
            }
        });
    }

    public void setWeatherController(WeatherController controller) {
        this.weatherController = controller;
    }

    public void displayWeatherData(WeatherData weatherData) {
        if (weatherData != null) {
            weatherInfoTextArea.setText("Location: " + weatherData.getLocationName() + "\n");
            weatherInfoTextArea.append("Weather Info: " + weatherData.getWeatherInfo());
        } else {
            weatherInfoTextArea.setText("Error fetching weather data. Please try again.");
        }
    }

    public void show() {
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
