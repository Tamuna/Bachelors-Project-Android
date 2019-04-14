package ge.edu.freeuni.assignment2.network;

import ge.edu.freeuni.assignment2.model.Weather;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface WeatherApi {
    @GET("v1/forecast.json")
    Call<Weather> getCountryWeather(@Query("q") String country, @Query("days") Integer days, @Query("key") String key);
}
