package ge.edu.freeuni.assignment2.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class Forecast {

    @SerializedName("forecastday")
    private ArrayList<ForecastDay> forecastDays;

    public ArrayList<ForecastDay> getForecastDays() {
        return forecastDays;
    }

    public void setForecastDays(ArrayList<ForecastDay> forecastDays) {
        this.forecastDays = forecastDays;
    }


}
