package ge.edu.freeuni.rsr.home;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.viewpager.widget.ViewPager;

import com.rd.PageIndicatorView;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import ge.edu.freeuni.rsr.R;
import ge.edu.freeuni.rsr.common.animation.ZoomOutPageTransformer;
import ge.edu.freeuni.rsr.common.component.CustomTwoButtonDialog;

public class HomeActivity extends AppCompatActivity implements CustomTwoButtonDialog.AnswerDecisionListener, HomeContract.HomeView {
    ViewPager pager;
    GameTypesPagerAdapter adapter;
    List<GameTypeCardModel> models;
    private HomeContract.HomePresenter presenter;

    private static final String FRIEND_USERNAME = "friend_username";

    public static void start(Activity previous, String friendUsername) {
        Intent intent = new Intent(previous, HomeActivity.class);
        intent.putExtra(FRIEND_USERNAME, friendUsername);
        previous.startActivity(intent);
    }

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

        models.add(new GameTypeCardModel(R.drawable.header_chempionship, getString(R.string.tournir), getString(R.string.game_desc_tournir)));
        models.add(new GameTypeCardModel(R.drawable.header_individual, getString(R.string.individual_practice), getString(R.string.game_desc_individual)));
        models.add(new GameTypeCardModel(R.drawable.header_group, getString(R.string.group_practice), getString(R.string.game_desc_group)));

        adapter = new GameTypesPagerAdapter(models, this);

        pager = findViewById(R.id.vp_game_type);
        pager.setAdapter(adapter);
        pager.setPadding(70, 0, 70, 0);
        pager.setCurrentItem(1);
        pager.setPageTransformer(true, new ZoomOutPageTransformer());
        pager.setClipToPadding(false);
        pager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                pageIndicatorView.setSelection(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });

        if (getIntent().getStringExtra(FRIEND_USERNAME) != null) {
//            if (getLifecycle().getCurrentState().isAtLeast(Lifecycle.State.STARTED)) {
                CustomTwoButtonDialog.newInstance(getIntent().getStringExtra(FRIEND_USERNAME), true)
                        .show(getSupportFragmentManager(), "alert");
//            }
        }
        presenter = new HomePresenterImpl(this, new HomeInteractorImpl());
    }

    @Override
    public void onPositiveDecision(DialogFragment dialog) {
        presenter.addFriend(getIntent().getStringExtra(FRIEND_USERNAME));
        dialog.dismissAllowingStateLoss();
    }

    @Override
    public void onNegativeDecision(DialogFragment dialog) {
        dialog.dismissAllowingStateLoss();
    }

    @Override
    public void onFriendAdded() {
        Toast.makeText(this, "მოაზროვნე წარმატებით დაემატა თქვენს მეგობრებში", Toast.LENGTH_SHORT).show();
    }
}
