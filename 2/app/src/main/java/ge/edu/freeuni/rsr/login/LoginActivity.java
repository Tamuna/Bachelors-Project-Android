package ge.edu.freeuni.rsr.login;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import ge.edu.freeuni.rsr.R;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    public static void start(Context previous) {
        Intent intent = new Intent();
        previous.startActivity(intent);
    }
}
