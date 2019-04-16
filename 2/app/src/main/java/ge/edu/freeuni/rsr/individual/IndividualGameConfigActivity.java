package ge.edu.freeuni.rsr.individual;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;
import ge.edu.freeuni.rsr.R;

public class IndividualGameConfigActivity extends AppCompatActivity {

    public static void start(Context previous) {
        Intent intent = new Intent(previous, IndividualGameConfigActivity.class);
        previous.startActivity(intent);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_individual_game_config);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,  WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }
}
