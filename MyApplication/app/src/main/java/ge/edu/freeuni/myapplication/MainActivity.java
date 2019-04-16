package ge.edu.freeuni.myapplication;

import android.os.Bundle;

import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    private Retrofit retrofit;
    private static final String BASE_URL = "http://restcountries.eu/";
    private Api api;

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        api.getCountries("name").enqueue(new Callback<List<Country>>() {
            @Override
            public void onResponse(Call<List<Country>> call, Response<List<Country>> response) {
                if (response.isSuccessful()){
                    response.body();//??result
                }
            }

            @Override
            public void onFailure(Call<List<Country>> call, Throwable t) {

            }
        });

    }
}
