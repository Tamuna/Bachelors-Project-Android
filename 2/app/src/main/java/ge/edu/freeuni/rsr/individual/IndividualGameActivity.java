package ge.edu.freeuni.rsr.individual;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import butterknife.BindView;
import butterknife.ButterKnife;
import ge.edu.freeuni.rsr.R;

public class IndividualGameActivity extends AppCompatActivity {

    private long mTimeLeftInMillis = 10000;

    @BindView(R.id.tvTime)
    TextView tvTime;


    public static void start(Context previous) {
        Intent intent = new Intent(previous, IndividualGameActivity.class);
        previous.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_individual_game);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        ButterKnife.bind(this);
        CountDownTimer mCountDownTimer = new CountDownTimer(mTimeLeftInMillis, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                mTimeLeftInMillis = millisUntilFinished;
                tvTime.setText(String.format("%d", mTimeLeftInMillis / 1000));
            }

            @Override
            public void onFinish() {
                Toast.makeText(IndividualGameActivity.this, "ended", Toast.LENGTH_SHORT).show();
            }
        }.start();

    }
}
