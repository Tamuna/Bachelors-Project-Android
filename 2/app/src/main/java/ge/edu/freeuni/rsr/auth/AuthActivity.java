package ge.edu.freeuni.rsr.auth;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import ge.edu.freeuni.rsr.R;
import ge.edu.freeuni.rsr.groupchat.chat.GroupChatActivity;
import ge.edu.freeuni.rsr.home.HomeActivity;


public class AuthActivity extends AppCompatActivity implements AuthContract.AuthView {

    @BindView(R.id.loader_view)
    LinearLayout loaderView;

    @BindView(R.id.layout_registration)
    LinearLayout layoutRegistration;

    @BindView(R.id.layout_registration_bottom)
    LinearLayout layoutRegistrationBottom;

    @BindView(R.id.et_reg_name)
    EditText etRegName;

    @BindView(R.id.et_reg_email)
    EditText etRegEmail;

    @BindView(R.id.et_reg_password)
    EditText etRegPassword;

    @BindView(R.id.et_reg_confirm_password)
    EditText etRegConfirmPassword;


    public static void start(Context previous) {
        Intent intent = new Intent(previous, AuthActivity.class);
        previous.startActivity(intent);
    }

    @OnClick(R.id.txt_login)
    void onLoginTxtClick() {
        layoutRegistration.setVisibility(View.GONE);
        layoutRegistrationBottom.setVisibility(View.GONE);

        layoutLogin.setVisibility(View.VISIBLE);
        layoutLoginBottom.setVisibility(View.VISIBLE);
    }

    @BindView(R.id.layout_login)
    LinearLayout layoutLogin;

    @BindView(R.id.layout_login_bottom)
    LinearLayout layoutLoginBottom;

    @BindView(R.id.et_log_name)
    EditText etLoginName;

    @BindView(R.id.et_log_pass)
    EditText etLoginPassword;

    @OnClick(R.id.txt_register)
    void onRegisterTxtClick() {
        layoutLogin.setVisibility(View.GONE);
        layoutLoginBottom.setVisibility(View.GONE);

        layoutRegistration.setVisibility(View.VISIBLE);
        layoutRegistrationBottom.setVisibility(View.VISIBLE);
    }

    @OnClick(R.id.btn_login)
    void onLoginClick() {
        presenter.login(etLoginName.getText().toString(), etLoginPassword.getText().toString());
    }

    @OnClick(R.id.btn_register)
    void onRegisterClick() {
        presenter.register(etRegName.getText().toString(), etRegPassword.getText().toString(),
                etRegEmail.getText().toString(), etRegConfirmPassword.getText().toString());

    }

    private AuthContract.AuthPresenter presenter;

    private String dialogId;
    private String friendUsername;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth);
        ButterKnife.bind(this);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        presenter = new AuthPresenterImpl(new AuthInteractorImpl(), this);
        dialogId = getIntent().getStringExtra("dialog_id");
        friendUsername = getIntent().getStringExtra("friend_username");

    }

    @Override
    protected void onResume() {
        super.onResume();
        dialogId = getIntent().getStringExtra("dialog_id");
    }

    @Override
    public void redirectToHome() {
        finish();
        if (dialogId != null) {
            GroupChatActivity.start(this, dialogId);
        } else {
            HomeActivity.start(this, friendUsername);
        }
    }

    @Override
    public void showError(String error) {
        Toast.makeText(this, error, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showLoader(boolean isLoading) {
        if (isLoading) {
            loaderView.setVisibility(View.VISIBLE);
        } else {
            loaderView.setVisibility(View.GONE);
        }
    }

    @Override
    public void registeredSuccessful() {
        layoutRegistration.setVisibility(View.GONE);
        layoutRegistrationBottom.setVisibility(View.GONE);

        layoutLogin.setVisibility(View.VISIBLE);
        layoutLoginBottom.setVisibility(View.VISIBLE);

        Toast.makeText(this, getResources().getString(R.string.confirm_email_info), Toast.LENGTH_LONG).show();
    }

}
