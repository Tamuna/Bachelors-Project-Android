package ge.edu.freeuni.assignment2.model.weather;

import com.google.gson.annotations.SerializedName;

public class ForecastDay {
    private String date;
    private Day day;
    private Astro astro;
    @SerializedName("date_epoch")
    private long dateEpoch;

    public long getDateEpoch() {
        return dateEpoch;
    }

    public void setDateEpoch(long dateEpoch) {
        this.dateEpoch = dateEpoch;
    }

    public Astro getAstro() {
        return astro;
    }

    public void setAstro(Astro astro) {
        this.astro = astro;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Day getDay() {
        return day;
    }

    public void setDay(Day day) {
        this.day = day;
    }
}
