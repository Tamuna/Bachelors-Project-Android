package ge.edu.freeuni.rsr;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FinishActivity extends AppCompatActivity {
    public static final String NUM_POINTS = "numPoints";

    @BindView(R.id.tvNumCorrect)
    TextView tvNumCorrect;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finish);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        ButterKnife.bind(this);

        int numCorrect = getIntent().getIntExtra(NUM_POINTS, 0);
        tvNumCorrect.setText("+" + numCorrect + " ");
    }

    public static void start(Context previuous, int numPoints) {
        Intent intent = new Intent(previuous, FinishActivity.class);
        intent.putExtra(NUM_POINTS, numPoints);
        previuous.startActivity(intent);
    }
}