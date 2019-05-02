package ge.edu.freeuni.rsr.individual;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import ge.edu.freeuni.rsr.R;
import ticker.views.com.ticker.widgets.circular.timer.callbacks.CircularViewCallback;
import ticker.views.com.ticker.widgets.circular.timer.view.CircularView;

public class IndividualGameActivity extends AppCompatActivity {

    CircularView circularViewWithTimer;

    public static void start(Context previous) {
        Intent intent = new Intent(previous, IndividualGameActivity.class);
        previous.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_individual_game);

        circularViewWithTimer = findViewById(R.id.circular_view_with_timer);
        circularViewWithTimer.startTimer();
        CircularView.OptionsBuilder builderWithTimer =
                new CircularView.OptionsBuilder()
                        .setCounterInSeconds(60)
                        .setCircularViewCallback(new CircularViewCallback() {
                            @Override
                            public void onTimerFinish() {
                                Toast.makeText(IndividualGameActivity.this, "CircularCallback: Timer Finished ", Toast.LENGTH_SHORT).show();
                            }

                            @Override
                            public void onTimerCancelled() {}
                        });

        circularViewWithTimer.setOptions(builderWithTimer);

    }
}
