package ge.edu.freeuni.assignment2.model.weather;

import ge.edu.freeuni.assignment2.model.location.Location;

public class Weather {
    private Location location;
    private CurrentWeather current;
    private Forecast forecast;

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public CurrentWeather getCurrent() {
        return current;
    }

    public void setCurrent(CurrentWeather current) {
        this.current = current;
    }

    public Forecast getForecast() {
        return forecast;
    }

    public void setForecast(Forecast forecast) {
        this.forecast = forecast;
    }


}
