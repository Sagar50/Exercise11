//Sagar patel CSCI1660 3/29/2020

package com.company.Patel;
import java.util.*;

class PressureSensor implements WeatherDataSource {
    private double currentPressure;
    private List<WeatherDataListener> listeners = new ArrayList<>();

    PressureSensor() {
        updatePressure();
    }

    public double getCurrentPressure() {
        return currentPressure;
    }

    private void updatePressure() {
        currentPressure = new Random().nextDouble();
    }

    @Override
    public void addListener(WeatherDataListener listener) {
        listeners.add(listener);
    }

    @Override
    public void removeListener(WeatherDataListener listener) {
        listeners.remove(listener);
    }

    @Override
    public void update() {
        System.out.println("PressureSensor: getting new data.");
        updatePressure();
        for (WeatherDataListener listener : listeners) {
            listener.updateData(new WeatherData("pressure", currentPressure) {
                @Override
                public String getUpdateMessage() {
                    return "Pressure updated to " + currentPressure;
                }
            });
        }

    }
}
class TemperatureSensor implements WeatherDataSource {
    private double currentTemperature;
    private List<WeatherDataListener> listeners = new ArrayList<>();

    TemperatureSensor() {
        updateTemperature();
    }

    public double getCurrentTemperature() {
        return currentTemperature;
    }

    private void updateTemperature() {
        currentTemperature = new Random().nextDouble() * 100;
    }

    @Override
    public void addListener(WeatherDataListener listener) {
        listeners.add(listener);
    }

    @Override
    public void removeListener(WeatherDataListener listener) {
        listeners.remove(listener);
    }

    @Override
    public void update() {
        System.out.println("TemperatureSensor: getting new data.");
        updateTemperature();
        for (WeatherDataListener listener : listeners) {
            listener.updateData(new WeatherData("temperature", currentTemperature) {
                @Override
                public String getUpdateMessage() {
                    return "The new temperature is " + currentTemperature;
                }
            });
        }

    }
}

class HumiditySensor implements WeatherDataSource {
    private double currentHumidity;
    private List<WeatherDataListener> listeners = new ArrayList<>();

    HumiditySensor() {
        updateHumidity();
    }

    public double getCurrentHumidity() {
        return currentHumidity;
    }

    private void updateHumidity() {
        currentHumidity = new Random().nextDouble();
    }

    @Override
    public void addListener(WeatherDataListener listener) {
        listeners.add(listener);
    }

    @Override
    public void removeListener(WeatherDataListener listener) {
        listeners.remove(listener);
    }

    @Override
    public void update() {
        System.out.println("HumiditySensor: getting new data.");
        updateHumidity();
        for (WeatherDataListener listener : listeners) {
            listener.updateData(new WeatherData("humidity", currentHumidity) {
                @Override
                public String getUpdateMessage() {
                    return "Humidity updated to " + currentHumidity;
                }
            });
        }

    }
}

class WeatherStation implements WeatherDataListener {
    private Map<String, Double> allWeatherData = new HashMap<>();
    private List<String> log = new ArrayList<>();

    @Override
    public void updateData(WeatherData newData) {
        System.out.println("WeatherStation: Updating the weather station data with new "
                + newData.getDataType() + " data.");
        allWeatherData.put(newData.getDataType(), newData.getMeasuredValue());
        log.add(newData.getUpdateMessage());
    }

    public void displayCurrentWeather() {
        System.out.println("Weather Report");
        for (String dataType : allWeatherData.keySet()) {
            System.out.println(dataType + ": " + allWeatherData.get(dataType));
        }
    }

    public void displayLog() {
        for (int i = 0; i < log.size(); i++) {
            int currentLine = i + 1;
            System.out.println(currentLine + ": " + log.get(i));
        }
    }
}
public class Main {
    public static void main(String[] args) {
        System.out.println("Main: creating objects");
        WeatherStation localWeatherStation = new WeatherStation();

        HumiditySensor humiditySensor = new HumiditySensor();
        PressureSensor pressureSensor = new PressureSensor();
        TemperatureSensor temperatureSensor = new TemperatureSensor();

        humiditySensor.addListener(localWeatherStation);
        pressureSensor.addListener(localWeatherStation);
        temperatureSensor.addListener(localWeatherStation);

        System.out.println(" ");
        humiditySensor.update();
        temperatureSensor.update();
        pressureSensor.update();
        System.out.println(" ");
        
        System.out.println("Main: displaying report and logs");
        localWeatherStation.displayCurrentWeather();
        localWeatherStation.displayLog();
        System.out.println(" ");



    }
}