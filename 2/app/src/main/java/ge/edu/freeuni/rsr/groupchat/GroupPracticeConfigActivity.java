package ge.edu.freeuni.rsr.groupchat;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import butterknife.ButterKnife;
import ge.edu.freeuni.rsr.R;

public class GroupPracticeConfigActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group_practice_config);

        ButterKnife.bind(this);
    }
}
