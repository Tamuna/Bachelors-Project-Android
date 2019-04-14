package ge.edu.freeuni.assignment2.model;

import com.google.gson.annotations.SerializedName;

public class ForecastDay {
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

    public Day getDay() {
        return day;
    }

    public void setDay(Day day) {
        this.day = day;
    }
}
