package ge.edu.freeuni.assignment1;

import android.os.Bundle;
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
        getUiComponents();
        setUiActions();
        initRating();
    }

    private void initRating() {
        final ArrayList<ImageView> stars = new ArrayList<>();
        for (int i = 0; i < RATING_RANGE; i++) {
            ImageView star = new ImageView(this);
            star.setBackgroundResource(R.drawable.icons8_star_500);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(60, 60);
            if (i > 0) params.setMarginStart(100);
            star.setLayoutParams(params);
            final int curIdx = i;
            star.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    for (int j = 0; j < RATING_RANGE; j++) {
                        stars.get(j).setBackgroundResource(R.drawable.icons8_star_500);
                    }
                    for (int j = 0; j <= curIdx; j++) {
                        stars.get(j).setBackgroundResource(R.drawable.icons8_star_filled_500);
                    }
                }
            });
            stars.add(star);
            layoutRatingStars.addView(star);
        }
    }


    private void setUiActions() {
        btnUninstall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showSnackBar(btnUninstall, getString(R.string.uninstall_info));
            }
        });
        btnOpen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showSnackBar(btnOpen, getString(R.string.open_info));
            }
        });

        imgTravelAndLocal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showSnackBar(imgTravelAndLocal, getString(R.string.travel_and_local_info));
            }
        });
        imgSimilar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showSnackBar(imgSimilar, getString(R.string.similar_apps_info));
            }
        });
    }

    private void showSnackBar(View view, String infoString) {
        final Snackbar snackbar = Snackbar.make(findViewById(R.id.root), infoString, Snackbar.LENGTH_LONG);
        snackbar.setAction(getString(R.string.common_str_ok), new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                snackbar.dismiss();
            }
        });
        snackbar.show();
    }


    private void getUiComponents() {
        btnUninstall = findViewById(R.id.btn_uninstall);
        btnOpen = findViewById(R.id.btn_open);
        imgTravelAndLocal = findViewById(R.id.img_travel);
        imgSimilar = findViewById(R.id.img_similar);
        layoutRatingStars = findViewById(R.id.layout_rating_stars);
    }
}
