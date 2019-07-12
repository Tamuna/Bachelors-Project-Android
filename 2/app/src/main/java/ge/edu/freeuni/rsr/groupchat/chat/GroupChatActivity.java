package ge.edu.freeuni.rsr.groupchat.chat;

import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.view.WindowManager;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.widget.CompoundButtonCompat;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.connectycube.chat.ConnectycubeChatService;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import ge.edu.freeuni.rsr.R;
import ge.edu.freeuni.rsr.common.component.CustomTwoButtonDialog;
import ge.edu.freeuni.rsr.groupchat.chat.entity.Message;
import ge.edu.freeuni.rsr.groupchat.chat.recycler.MessagesRecyclerAdapter;
import ge.edu.freeuni.rsr.home.HomeActivity;

public class GroupChatActivity extends AppCompatActivity implements GroupChatContract.GroupChatView, CustomTwoButtonDialog.AnswerDecisionListener {

    @BindView(R.id.rv_messages)
    RecyclerView rvMessages;

    @BindView(R.id.tv_become_cap)
    TextView tvBecomeCap;

    @BindView(R.id.tv_become_host)
    TextView tvBecomeHost;

    @BindView(R.id.et_message)
    EditText etMessage;

    @BindView(R.id.loader_view)
    LinearLayout loaderView;

    @BindView(R.id.cb_role_checker)
    CheckBox cbRoleContorller;

    @BindView(R.id.tvTime)
    TextView tvTime;


    @OnClick(R.id.tv_become_host)
    void onBecomeHost() {
        presenter.sendBecomeHostMessage();
    }

    @OnClick(R.id.tv_become_cap)
    void onBecomeCap() {
        presenter.sendBecomeCapMessage();
    }

    private MessagesRecyclerAdapter adapter;
    private GroupChatContract.GroupChatPresenter presenter;

    @OnClick(R.id.imgSend)
    void onSendMessageClicked() {
        presenter.sendMessage(etMessage.getText().toString().trim(), cbRoleContorller.isChecked());
        etMessage.setText("");
        if (cbRoleContorller.isChecked()) cbRoleContorller.setChecked(false);
    }

    private CountDownTimer timer;

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        chatId = intent.getStringExtra(CHAT_ID);
    }

    public static final String CHAT_ID = "dialog_id";
    private String chatId;

    public static void start(Context previous, String chatId) {
        Intent intent = new Intent(previous, GroupChatActivity.class);
        intent.putExtra(CHAT_ID, chatId);
        previous.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group_chat);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        ButterKnife.bind(this);

        presenter = new GroupChatPresenterImpl(this, new GroupChatInteractorImpl());

        chatId = getIntent().getStringExtra(CHAT_ID);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setStackFromEnd(true);
        rvMessages.setLayoutManager(layoutManager);

        adapter = new MessagesRecyclerAdapter();
        rvMessages.setAdapter(adapter);
        loaderView.setVisibility(View.VISIBLE);
        if (ConnectycubeChatService.getInstance().isLoggedIn()) {
            presenter.getHistory(chatId);
        } else {
            presenter.loginToChat(chatId);
        }
    }


    @Override
    public void onChatHistoryLoaded(List<Message> history) {
        loaderView.setVisibility(View.INVISIBLE);
        adapter.setData(history);
    }

    @Override
    public void displaySingleMessage(Message message) {
        adapter.setSingleData(message);
        rvMessages.scrollToPosition(adapter.getItemCount() - 1);
    }

    @Override
    public void showMessageSentError() {
        Toast.makeText(this, getString(R.string.cannot_send_message), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void capAlreadyAcquired(String name) {
//        if (getLifecycle().getCurrentState().isAtLeast(Lifecycle.State.STARTED)) {
        AfterRoleAcquiredDialog.newInstance(name, "კაპიტანი").show(getSupportFragmentManager(), "alert");
//        }
    }

    @Override
    public void hostAlreadyAcquired(String name) {
//        if (getLifecycle().getCurrentState().isAtLeast(Lifecycle.State.STARTED)) {
        AfterRoleAcquiredDialog.newInstance(name, "წამყვანი").show(getSupportFragmentManager(), "alert");
//        }
    }

    @Override
    public void displayHostCheckBox(boolean visibility) {
        cbRoleContorller.setVisibility(visibility ? View.VISIBLE : View.GONE);

        if (visibility) {
            CompoundButtonCompat.setButtonTintList(cbRoleContorller, ColorStateList.valueOf(getResources().getColor(R.color.light_red)));
            cbRoleContorller.setTextColor(getResources().getColor(R.color.light_red));
            cbRoleContorller.setText("კითხვის დასმა");
        } else {
            cbRoleContorller.setChecked(false);
        }
    }

    @Override
    public void displayCapCheckBox(boolean visibility) {
        cbRoleContorller.setVisibility(visibility ? View.VISIBLE : View.GONE);

        if (visibility) {
            CompoundButtonCompat.setButtonTintList(cbRoleContorller, ColorStateList.valueOf(getResources().getColor(R.color.green)));
            cbRoleContorller.setTextColor(getResources().getColor(R.color.green));
            cbRoleContorller.setText("პასუხის დაფიქსირება");
        } else {
            cbRoleContorller.setChecked(false);
        }
    }

    @Override
    public void startTimer() {
        tvTime.setVisibility(View.VISIBLE);
        if (timer != null) {
            timer.cancel();
        }
        createTimer();
        timer.start();
    }

    @Override
    public void showAnswerDialog(String answer) {
//        if (getLifecycle().getCurrentState().isAtLeast(Lifecycle.State.STARTED)) {
        CustomTwoButtonDialog.newInstance(answer, false).show(getSupportFragmentManager(), "alert");
//        }
    }

    @Override
    public void timerCancel() {
        if (timer != null) {
            timer.cancel();
        }
    }

    private String toTime(long seconds) {
        return "" + seconds / 60 + ":" + seconds % 60;
    }

    private long timeOnSingleQuestion = 120000;

    private void createTimer() {
        timeOnSingleQuestion = 120000;
        timer = new CountDownTimer(timeOnSingleQuestion, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timeOnSingleQuestion = millisUntilFinished;
                tvTime.setText(toTime(timeOnSingleQuestion / 1000));
            }

            @Override
            public void onFinish() {
                if (timer != null) {
                    timer.cancel();
                }
                presenter.sendMessage("-", true);
            }
        };
    }

    @Override
    public void onPositiveDecision(DialogFragment dialog) {
        dialog.dismissAllowingStateLoss();
        presenter.sendMessage("სწორია", false);
    }

    @Override
    public void onNegativeDecision(DialogFragment dialog) {
        dialog.dismissAllowingStateLoss();
        presenter.sendMessage("არასწორია", false);
    }

    @Override
    public void onBackPressed() {
        HomeActivity.start(this, null);
        finish();
    }
}
