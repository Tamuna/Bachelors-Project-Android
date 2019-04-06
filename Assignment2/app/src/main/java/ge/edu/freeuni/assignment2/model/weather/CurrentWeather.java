package ge.edu.freeuni.assignment2.model.weather;

import com.google.gson.annotations.SerializedName;

public class CurrentWeather {
    @SerializedName("last_updated_epoch")
    private Long lastUpdatedEpoch;

    @SerializedName("temp_c")
    private Double tempCelsius;

    @SerializedName("is_day")
    private Integer isDay;

    private Condition condition;

    @SerializedName("wind_kph")
    private Double windKph;

    private Double humidity;

    @SerializedName("feelslike_c")
    private Double feelLikeCelcius;

    public Long getLastUpdatedEpoch() {
        return lastUpdatedEpoch;
    }

    public void setLastUpdatedEpoch(Long lastUpdatedEpoch) {
        this.lastUpdatedEpoch = lastUpdatedEpoch;
    }

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

    public Condition getCondition() {
        return condition;
    }

    public void setCondition(Condition condition) {
        this.condition = condition;
    }

    public Double getWindKph() {
        return windKph;
    }

    public void setWindKph(Double windKph) {
        this.windKph = windKph;
    }

    public Double getHumidity() {
        return humidity;
    }

    public void setHumidity(Double humidity) {
        this.humidity = humidity;
    }

    public Double getFeelLikeCelcius() {
        return feelLikeCelcius;
    }

    public void setFeelLikeCelcius(Double feelLikeCelcius) {
        this.feelLikeCelcius = feelLikeCelcius;
    }
}
