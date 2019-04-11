package ge.edu.freeuni.assignment2.ui.viewpager;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import ge.edu.freeuni.assignment2.model.location.Location;

public class ViewPagerAdapter extends FragmentStatePagerAdapter {
    private List<Location> data = new ArrayList<>();

    public ViewPagerAdapter(@NonNull FragmentManager fm) {
        super(fm);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return WeatherFragment.newInstance(data.get(position).getName());
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
