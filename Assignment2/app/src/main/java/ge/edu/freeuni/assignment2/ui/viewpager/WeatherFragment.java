package ge.edu.freeuni.assignment2.ui.viewpager;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import ge.edu.freeuni.assignment2.R;
import ge.edu.freeuni.assignment2.model.weather.ForecastDay;
import ge.edu.freeuni.assignment2.model.weather.Weather;
import ge.edu.freeuni.assignment2.network.Api;
import ge.edu.freeuni.assignment2.network.RetrofitInstance;
import ge.edu.freeuni.assignment2.ui.recycler.ForecastRecyclerAdapter;
import ge.edu.freeuni.assignment2.util.Helper;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class WeatherFragment extends Fragment {
    private static final String API_ACCESS_KEY = "74357a82a546431595895929190604";
    public static final Integer NUM_FORECAST_DAYS = 10;
    private RecyclerView recyclerForecast;
    private ForecastRecyclerAdapter recyclerAdapter;

    private ProgressBar progressBar;

    private View infoGroup;

    private TextView txtLocation;
    private TextView txtDatetime;
    private TextView txtCelcius;
    private TextView txtPerceived;

    private TextView txtPrecipication;
    private TextView txtHumidity;
    private TextView txtWind;
    private TextView txtDayNight;

    private ConstraintLayout bgLayout;
    private ImageView imgSunMoon;
    private ImageView imgDrop;
    private ImageView imgFlag;
    private ImageView imgDrop1;
    private ImageView imgDrop2;
    private ImageView imgDrop3;

    private TextView txtError;
    private ImageView imgError;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_weather, container, false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        bindViews(view);
        getFragmentData(getArguments().getString("location"));
    }

    private void bindViews(View view) {
        recyclerForecast = view.findViewById(R.id.recycler_forecast);
        recyclerAdapter = new ForecastRecyclerAdapter();
        recyclerForecast.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerForecast.setAdapter(recyclerAdapter);
        txtLocation = view.findViewById(R.id.txt_city);
        txtDatetime = view.findViewById(R.id.txt_datetime);
        txtCelcius = view.findViewById(R.id.txt_temperature);
        txtPerceived = view.findViewById(R.id.txt_perceived);
        imgSunMoon = view.findViewById(R.id.img_sum_moon);
        txtPrecipication = view.findViewById(R.id.txt_precipitation);
        txtHumidity = view.findViewById(R.id.txt_humidity);
        txtWind = view.findViewById(R.id.txt_wind_speed);
        txtDayNight = view.findViewById(R.id.txt_day_and_night);
        bgLayout = view.findViewById(R.id.bg_layout);
        imgDrop = view.findViewById(R.id.img_drop);
        imgDrop1 = view.findViewById(R.id.img_drops_1);
        imgDrop2 = view.findViewById(R.id.img_drops_2);
        imgDrop3 = view.findViewById(R.id.img_drops_3);
        imgFlag = view.findViewById(R.id.img_flag);
        progressBar = view.findViewById(R.id.loading);
        infoGroup = view.findViewById(R.id.id_info_group);
        txtError = view.findViewById(R.id.txt_error);
        imgError = view.findViewById(R.id.img_error);

    }

    public static WeatherFragment newInstance(String location) {
        Bundle bundle = new Bundle();
        bundle.putString("location", location);
        WeatherFragment fragment = new WeatherFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    public void getFragmentData(String location) {
        showProgress();
        Retrofit retrofit = RetrofitInstance.getInstance().getRetrofitWeathers();
        Api api = retrofit.create(Api.class);
        api.getCountryWeather(location, NUM_FORECAST_DAYS, API_ACCESS_KEY).enqueue(new Callback<Weather>() {
            @Override
            public void onResponse(Call<Weather> call, Response<Weather> response) {
                if (response.body() != null) {
                    displayInfo(response.body());
                } else {
                    displayErrorMessage("No weather information for " + getArguments().getString("location"));
                }
                hideProgress();
            }

            @Override
            public void onFailure(Call<Weather> call, Throwable t) {
                displayErrorMessage("Data Access Error Occurred");
                hideProgress();
            }
        });
    }


    private void displayInfo(Weather data) {
        infoGroup.setVisibility(View.VISIBLE);
        if (data.getForecast().getForecastDays().size() > 0) {
            ForecastDay current = data.getForecast().getForecastDays().get(0);
            txtDatetime.setText(Helper.getDatetime(current.getDateEpoch(), true));
            txtPerceived.setText(String.format("Perceived %s℃", current.getDay().getMaxTempCelsius().intValue()));
            txtDayNight.setText(String.format("%s %s", current.getAstro().getSunrise().toLowerCase().replaceAll(" ", ""), current.getAstro().getSunset().toLowerCase().replaceAll(" ", "")));
            txtPrecipication.setText(String.format("%s%%", current.getDay().getAvgHumidity().intValue()));
            txtHumidity.setText(String.format("%s%%", current.getDay().getAvgHumidity().intValue()));
        }
        txtLocation.setText(data.getLocation().getName());
        txtCelcius.setText(String.format("%s℃", data.getCurrent().getTempCelsius().intValue()));
        txtWind.setText(String.format("%s kmh", data.getCurrent().getWindKph().intValue()));
        recyclerAdapter.setData(data.getForecast().getForecastDays());
        setDayNightTheme(data.getCurrent().getIsDay());
    }

    private void setDayNightTheme(Boolean isDay) {
        if (isDay) {
            bgLayout.setBackgroundResource(R.drawable.bg_gradient_day);
            imgSunMoon.setImageResource(R.drawable.ic_sun);
            setDayNightColors(R.color.color_day);
        } else {
            bgLayout.setBackgroundResource(R.drawable.bg_gradient_night);
            imgSunMoon.setImageResource(R.drawable.ic_moon);
            setDayNightColors(R.color.color_night);
        }
    }

    private void setDayNightColors(int color) {
        setColor(color, imgDrop);
        setColor(color, imgDrop1);
        setColor(color, imgDrop2);
        setColor(color, imgDrop3);
        setColor(color, imgFlag);
    }

    private void setColor(int color, ImageView img) {
        DrawableCompat.setTint(
                img.getDrawable(),
                ContextCompat.getColor(getContext(), color)
        );
    }

    private void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
        infoGroup.setVisibility(View.INVISIBLE);
    }

    private void hideProgress() {
        progressBar.setVisibility(View.INVISIBLE);
    }

    private void displayErrorMessage(String error) {
        txtError.setText(error);
        txtError.setVisibility(View.VISIBLE);
        imgError.setVisibility(View.VISIBLE);
    }
}
