package ge.edu.freeuni.assignment2.model.weather;

import com.google.gson.annotations.SerializedName;

public class CurrentWeather {


    @SerializedName("temp_c")
    private Double tempCelsius;

    @SerializedName("is_day")
    private Integer isDay;

    @SerializedName("wind_kph")
    private Double windKph;


    public Double getTempCelsius() {
        return tempCelsius;
    }

    public void setTempCelsius(Double tempCelsius) {
        this.tempCelsius = tempCelsius;
    }

    public Boolean getIsDay() {
        return isDay == 1;
    }

    public void setIsDay(Integer isDay) {
        this.isDay = isDay;
    }


    public Double getWindKph() {
        return windKph;
    }

    public void setWindKph(Double windKph) {
        this.windKph = windKph;
    }
}
