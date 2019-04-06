package ge.edu.freeuni.myapplication;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface Api {
    @GET("rest/v2/all")
    Call<List<Country>> getCountries(@Query("fields") String fields);
}
