package ge.edu.freeuni.rsr.groupchat.chat;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.connectycube.chat.ConnectycubeChatService;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import ge.edu.freeuni.rsr.R;
import ge.edu.freeuni.rsr.groupchat.chat.entity.Message;

public class GroupChatActivity extends AppCompatActivity implements GroupChatContract.GroupChatView {

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
        presenter.sendMessage(etMessage.getText().toString().trim());
        etMessage.setText("");
    }

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

        if (ConnectycubeChatService.getInstance().isLoggedIn()) {
            presenter.getHistory(chatId);
        } else {
            presenter.loginToChat(chatId);
        }
    }


    @Override
    public void onChatHistoryLoaded(List<Message> history) {
        adapter.setData(history);
        loaderView.setVisibility(View.GONE);
    }

    @Override
    public void displaySingleMessage(Message message) {
        adapter.setSingleData(message, false, false);
        rvMessages.scrollToPosition(adapter.getItemCount() - 1);
    }

    @Override
    public void showMessageSentError() {
        Toast.makeText(this, getString(R.string.cannot_send_message), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void capAlreadyAcquired(String name) {
        AfterRoleAcquiredDialog.newInstance(name, "კაპიტანი").show(getSupportFragmentManager(), "alert");
    }

    @Override
    public void hostAlreadyAcquired(String name) {
        AfterRoleAcquiredDialog.newInstance(name, "წამყვანი").show(getSupportFragmentManager(), "alert");
    }

    @Override
    public void displayHostCheckBox(boolean visibility) {
        cbRoleContorller.setVisibility(visibility ? View.VISIBLE : View.GONE);
        if (visibility) {
            cbRoleContorller.setTextColor(getResources().getColor(R.color.light_red));
            cbRoleContorller.setText("კითხვის დასმა და წამზომის ჩართვა");
        }
    }

    @Override
    public void displayCapCheckBox(boolean visibility) {
        cbRoleContorller.setVisibility(visibility ? View.VISIBLE : View.GONE);
        if (visibility) {
            cbRoleContorller.setTextColor(getResources().getColor(R.color.green));
            cbRoleContorller.setText("კითხვაზე პასუხის დაფიქსირება");
        }
    }
}
