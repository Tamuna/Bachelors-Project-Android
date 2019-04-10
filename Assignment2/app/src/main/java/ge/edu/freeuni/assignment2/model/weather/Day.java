package ge.edu.freeuni.assignment2.model.weather;

import com.google.gson.annotations.SerializedName;

public class Day {
    @SerializedName("maxtemp_c")
    private Double maxTempCelsius;

    @SerializedName("avgtemp_c")
    private Double avgTempCelsius;

    @SerializedName("avghumidity")
    private Double avgHumidity;

    private Condition condition;

    public Double getMaxTempCelsius() {
        return maxTempCelsius;
    }

    public void setMaxTempCelsius(Double maxTempCelsius) {
        this.maxTempCelsius = maxTempCelsius;
    }

    public Double getAvgTempCelsius() {
        return avgTempCelsius;
    }

    public void setAvgTempCelsius(Double avgTempCelsius) {
        this.avgTempCelsius = avgTempCelsius;
    }

    public Double getAvgHumidity() {
        return avgHumidity;
    }

    public void setAvgHumidity(Double avgHumidity) {
        this.avgHumidity = avgHumidity;
    }

    public Condition getCondition() {
        return condition;
    }

    public void setCondition(Condition condition) {
        this.condition = condition;
    }

}
