package ge.edu.freeuni.assignment2.ui;

import android.os.Bundle;
import android.widget.Toast;

import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;
import ge.edu.freeuni.assignment2.R;
import ge.edu.freeuni.assignment2.model.Location;
import ge.edu.freeuni.assignment2.network.LocationsApi;
import ge.edu.freeuni.assignment2.network.RetrofitInstance;
import ge.edu.freeuni.assignment2.ui.weather.ViewPagerAdapter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {
    private ViewPager viewPager;
    private ViewPagerAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewPager = findViewById(R.id.vp_locations);
        adapter = new ViewPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(adapter);

        getAllCountries();

    }

    private void getAllCountries() {
        Retrofit retrofit = RetrofitInstance.getInstance().getRetrofitCounties();
        LocationsApi api = retrofit.create(LocationsApi.class);
        api.getCountries("name").enqueue(new Callback<List<Location>>() {
            @Override
            public void onResponse(Call<List<Location>> call, Response<List<Location>> response) {
                adapter.setData(response.body());
            }

            @Override
            public void onFailure(Call<List<Location>> call, Throwable t) {
                Toast toast = Toast.makeText(MainActivity.this, getString(R.string.data_access_error), Toast.LENGTH_LONG);
                toast.show();
            }
        });
    }
}
