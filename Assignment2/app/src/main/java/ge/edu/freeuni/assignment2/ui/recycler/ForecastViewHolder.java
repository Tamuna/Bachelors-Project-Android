package ge.edu.freeuni.assignment2.ui.recycler;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import ge.edu.freeuni.assignment2.R;
import ge.edu.freeuni.assignment2.model.weather.ForecastDay;
import ge.edu.freeuni.assignment2.util.Helper;

public class ForecastViewHolder extends RecyclerView.ViewHolder {
    private TextView txtForecastDate;
    private ImageView imgForecastIcon;
    private TextView txtForecastTemperature;
    private TextView txtForecastHumidity;

    public ForecastViewHolder(@NonNull View itemView) {
        super(itemView);
        txtForecastDate = itemView.findViewById(R.id.cell_txt_date);
        imgForecastIcon = itemView.findViewById(R.id.cell_img_forecast);
        txtForecastTemperature = itemView.findViewById(R.id.cell_txt_temperature);
        txtForecastHumidity = itemView.findViewById(R.id.cell_txt_avg_humidity);
    }

    public void setData(ForecastDay forecastDay) {
        Picasso.get().load("http://" + forecastDay.getDay().getCondition().getIcon().substring(2)).into(imgForecastIcon);
        txtForecastDate.setText(Helper.getDatetime(forecastDay.getDateEpoch(), false));
        txtForecastTemperature.setText(String.format("%s℃", forecastDay.getDay().getAvgTempCelsius().intValue()));
        txtForecastHumidity.setText(String.format("%s%%", forecastDay.getDay().getAvgHumidity().intValue()));
    }
}
