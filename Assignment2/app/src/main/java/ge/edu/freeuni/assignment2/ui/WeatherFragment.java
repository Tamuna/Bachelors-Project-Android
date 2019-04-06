package ge.edu.freeuni.assignment2.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.core.widget.ContentLoadingProgressBar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import ge.edu.freeuni.assignment2.R;
import ge.edu.freeuni.assignment2.model.weather.ForecastDay;
import ge.edu.freeuni.assignment2.model.weather.Weather;

public class WeatherFragment extends Fragment {
    private RecyclerView recyclerForecast;
    private ForecastRecyclerAdapter adapter;
    private Weather data;

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


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_weather, container, false);

        recyclerForecast = view.findViewById(R.id.recycler_forecast);
        adapter = new ForecastRecyclerAdapter();
        recyclerForecast.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerForecast.setAdapter(adapter);

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

        return view;
    }

    public static WeatherFragment newInstance() {
        return new WeatherFragment();
    }

    public void setData(Weather data) {
        if (data != null) {
            if (data.getForecast().getForecastDays().size() > 0) {
                Format formatter = new SimpleDateFormat("EEEE dd MMM yyyy hh:mm");
                String today = formatter.format(new Date());
                ForecastDay current = data.getForecast().getForecastDays().get(0);
                txtDatetime.setText(today);
                txtPerceived.setText(String.format("Perceived %s℃", current.getDay().getMaxTempCelsius().intValue()));
                txtDayNight.setText(String.format("%s %s", current.getAstro().getSunrise().toLowerCase(), current.getAstro().getSunset().toLowerCase()));
                txtPrecipication.setText(String.format("%s%%", current.getDay().getAvgHumidity().intValue()));
                txtHumidity.setText(String.format("%s%%", current.getDay().getAvgHumidity().intValue()));
            }
            txtLocation.setText(data.getLocation().getName());
            txtCelcius.setText(String.format("%s℃", data.getCurrent().getTempCelsius().intValue()));


            txtWind.setText(String.format("%s kmh", data.getCurrent().getWindKph().intValue()));
            adapter.setData(data.getForecast().getForecastDays());
            if (data.getCurrent().getIsDay()) {
                bgLayout.setBackgroundResource(R.drawable.bg_gradient_day);
                imgSunMoon.setImageResource(R.drawable.ic_sun);
                setDayNightTheme(R.color.color_day);
            } else {
                bgLayout.setBackgroundResource(R.drawable.bg_gradient_night);
                imgSunMoon.setImageResource(R.drawable.ic_moon);
                setDayNightTheme(R.color.color_night);
            }
        }
    }

    private void setDayNightTheme(int color) {
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
}
