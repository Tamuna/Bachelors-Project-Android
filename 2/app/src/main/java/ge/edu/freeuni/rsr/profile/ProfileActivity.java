package ge.edu.freeuni.rsr.profile;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import ge.edu.freeuni.rsr.AppUser;
import ge.edu.freeuni.rsr.R;
import ge.edu.freeuni.rsr.auth.AuthActivity;

public class ProfileActivity extends AppCompatActivity {

    @BindView(R.id.tv_username)
    TextView tvUsername;

    @OnClick(R.id.btn_logout)
    void onLogoutClick() {
        AppUser.getInstance().setUser(null);
        finishAffinity();
        AuthActivity.start(this);
    }

    public static void start(Context previous) {
        Intent intent = new Intent(previous, ProfileActivity.class);
        previous.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_profile);

        ButterKnife.bind(this);
        tvUsername.setText(AppUser.getInstance().getUser().getUserName());
    }
}
