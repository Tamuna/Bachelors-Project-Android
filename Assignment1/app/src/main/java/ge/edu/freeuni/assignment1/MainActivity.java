package ge.edu.freeuni.assignment1;

import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private LinearLayout layoutRatingStars;
    private Button btnUninstall;
    private Button btnOpen;
    private ImageView imgTravelAndLocal;
    private ImageView imgSimilar;

    private static final int RATING_RANGE = 5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bindUiComponents();
        setUiActions();
    }

    private void initRatingProcess() {
        int displayWidth = getScreenWidth();
        ArrayList<ImageView> stars = new ArrayList<>();
        for (int i = 0; i < RATING_RANGE; i++) {
            ImageView star = new ImageView(this);
            star.setBackgroundResource(R.drawable.icons8_star_500);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(displayWidth / (3 * RATING_RANGE), displayWidth / (3 * RATING_RANGE));
            if (i > 0) params.setMarginStart(displayWidth / (2 * RATING_RANGE));
            star.setLayoutParams(params);
            stars.add(star);
            setOnStarClickAction(i, stars);
            layoutRatingStars.addView(star);
        }
    }

    private int getScreenWidth() {
        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        return displayMetrics.widthPixels;
    }

    private void setOnStarClickAction(final int starIdx, final ArrayList<ImageView> stars) {
        stars.get(starIdx).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (int i = 0; i < RATING_RANGE; i++) {
                    stars.get(i).setBackgroundResource(R.drawable.icons8_star_500);
                }
                for (int i = 0; i <= starIdx; i++) {
                    stars.get(i).setBackgroundResource(R.drawable.icons8_star_filled_500);
                }
            }
        });
    }

    private void setUiActions() {
        setSnackbarEvent(btnUninstall, getString(R.string.uninstall_info));
        setSnackbarEvent(btnOpen, getString(R.string.open_info));
        setSnackbarEvent(imgTravelAndLocal, getString(R.string.travel_and_local_info));
        setSnackbarEvent(imgSimilar, getString(R.string.similar_apps_info));

        initRatingProcess();
    }

    private void setSnackbarEvent(View view, final String infoString) {
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Snackbar snackbar = Snackbar.make(findViewById(R.id.root), infoString, Snackbar.LENGTH_LONG);
                snackbar.setAction(getString(R.string.common_str_ok), new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        snackbar.dismiss();
                    }
                });
                snackbar.show();
            }
        });
    }

    private void bindUiComponents() {
        btnUninstall = findViewById(R.id.btn_uninstall);
        btnOpen = findViewById(R.id.btn_open);
        imgTravelAndLocal = findViewById(R.id.img_travel);
        imgSimilar = findViewById(R.id.img_similar);
        layoutRatingStars = findViewById(R.id.layout_rating_stars);
    }
}
