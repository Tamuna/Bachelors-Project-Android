package ge.edu.freeuni.assignment2.ui.forecast;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import ge.edu.freeuni.assignment2.R;
import ge.edu.freeuni.assignment2.model.ForecastDay;


public class ForecastRecyclerAdapter extends RecyclerView.Adapter<ForecastViewHolder> {
    private ArrayList<ForecastDay> data = new ArrayList<>();

    @NonNull
    @Override
    public ForecastViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cell_forecast_day, parent, false);
        return new ForecastViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ForecastViewHolder holder, int position) {
        holder.setData(data.get(position));
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public void setData(List<ForecastDay> data) {
        this.data.clear();
        this.data.addAll(data);
        notifyDataSetChanged();
    }
}
