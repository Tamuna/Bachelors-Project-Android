package ge.edu.freeuni.assignment2.network;

import java.util.List;

import ge.edu.freeuni.assignment2.model.location.Location;
import ge.edu.freeuni.assignment2.model.weather.Weather;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface Api {
    @GET("rest/v2/all")
    Call<List<Location>> getCountries(@Query("fields") String fields);

    @GET("v1/forecast.json")
    Call<Weather> getCountryWeather(@Query("q") String country, @Query("days") Integer days, @Query("key") String key);
}
