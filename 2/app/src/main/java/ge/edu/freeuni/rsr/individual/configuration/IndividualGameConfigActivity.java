package ge.edu.freeuni.rsr.individual.configuration;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;

import ge.edu.freeuni.rsr.R;
import ge.edu.freeuni.rsr.individual.game.IndividualGameActivity;

public class IndividualGameConfigActivity extends AppCompatActivity implements QuestionCountCard.IndividualGameItemListener {

    private QuestionCountCard item1;
    private QuestionCountCard item2;
    private QuestionCountCard item3;
    private QuestionCountCard item4;

    public static void start(Context previous) {
        Intent intent = new Intent(previous, IndividualGameConfigActivity.class);
        previous.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_individual_game_config);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        item1 = findViewById(R.id.view);
        item2 = findViewById(R.id.view2);
        item3 = findViewById(R.id.view3);
        item4 = findViewById(R.id.view4);
        item1.setIndividualGameItemListener(this);
        item2.setIndividualGameItemListener(this);
        item3.setIndividualGameItemListener(this);
        item4.setIndividualGameItemListener(this);
    }

    @Override
    public void onIndividualGameItemClicked(int questionCount) {
        IndividualGameActivity.start(this);
    }
}
