package ge.edu.freeuni.rsr.home;

import android.os.Bundle;
import android.view.WindowManager;

import com.rd.PageIndicatorView;

import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;
import butterknife.ButterKnife;
import ge.edu.freeuni.rsr.R;
import ge.edu.freeuni.rsr.utils.animation.ZoomOutPageTransformer;

public class HomeActivity extends AppCompatActivity {
    ViewPager pager;
    GameTypesPagerAdapter adapter;
    List<GameTypeCardModel> models;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        final PageIndicatorView pageIndicatorView = findViewById(R.id.indicator);
        pageIndicatorView.setCount(3);
        pageIndicatorView.setSelection(1);

        models = new ArrayList<>();
        models.add(new GameTypeCardModel(R.drawable.header_owl, getString(R.string.individual_practice), getString(R.string.game_desc_individual)));
        models.add(new GameTypeCardModel(R.drawable.header_tournament, getString(R.string.tournir), getString(R.string.game_desc_tournir)));
        models.add(new GameTypeCardModel(R.drawable.header_owls, getString(R.string.group_practice), getString(R.string.game_desc_group)));

        adapter = new GameTypesPagerAdapter(models, this);

        pager = findViewById(R.id.vp_game_type);
        pager.setAdapter(adapter);
        pager.setPadding(80, 0, 80, 0);
        pager.setCurrentItem(1);
        pager.setPageTransformer(true, new ZoomOutPageTransformer());
        pager.setClipToPadding(false);
        pager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {}

            @Override
            public void onPageSelected(int position) {
                pageIndicatorView.setSelection(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
    }

}
