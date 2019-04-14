package ge.edu.freeuni.assignment2.network;

import java.util.List;

import ge.edu.freeuni.assignment2.model.Location;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface LocationsApi {
    @GET("rest/v2/all")
    Call<List<Location>> getCountries(@Query("fields") String fields);
}
