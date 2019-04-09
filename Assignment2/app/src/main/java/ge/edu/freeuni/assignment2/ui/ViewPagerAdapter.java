package ge.edu.freeuni.assignment2.ui;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import ge.edu.freeuni.assignment2.model.location.Location;
import ge.edu.freeuni.assignment2.network.Api;
import ge.edu.freeuni.assignment2.network.RetrofitInstance;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class ViewPagerAdapter extends FragmentStatePagerAdapter {
    private List<Location> data = new ArrayList<>();

    public ViewPagerAdapter(@NonNull FragmentManager fm, MainActivity context) {
        super(fm);
        Retrofit retrofit = RetrofitInstance.getInstance().getRetrofitCounties();
        Api api = retrofit.create(Api.class);
        api.getCountries("name").enqueue(new Callback<List<Location>>() {
            @Override
            public void onResponse(Call<List<Location>> call, Response<List<Location>> response) {
                ViewPagerAdapter.this.setData(response.body());
            }

            @Override
            public void onFailure(Call<List<Location>> call, Throwable t) {

            }
        });
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        WeatherFragment fragment = WeatherFragment.newInstance(data.get(position).getName());
        return fragment;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    public void setData(List<Location> data) {
        this.data.clear();
        this.data.addAll(data);
        notifyDataSetChanged();
    }
}
