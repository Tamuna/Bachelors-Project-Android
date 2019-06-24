package ge.edu.freeuni.rsr.groupchat.chat;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.connectycube.chat.ConnectycubeChatService;
import com.connectycube.chat.model.ConnectycubeChatDialog;
import com.connectycube.chat.model.ConnectycubeChatMessage;
import com.connectycube.core.EntityCallback;
import com.connectycube.core.exception.ResponseException;
import com.connectycube.users.ConnectycubeUsers;
import com.connectycube.users.model.ConnectycubeUser;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import ge.edu.freeuni.rsr.AppUser;
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

    private MessagesRecyclerAdapter adapter;
    private GroupChatContract.GroupChatPresenter presenter;

    private ConnectycubeChatDialog createdDialog;
    private ConnectycubeChatService chatService;

    private boolean joined = false;

    @OnClick(R.id.imgSend)
    void onSendMessageClicked() {
        presenter.sendMessage(etMessage.getText().toString());
        etMessage.setText("");
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        chatId = intent.getStringExtra(CHAT_ID);
    }

    protected List<ConnectycubeChatMessage> messagesList;


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

        final ConnectycubeUser user = new ConnectycubeUser("username" + AppUser.getInstance().getUser().getId(), "username" + AppUser.getInstance().getUser().getId());
        user.setId(AppUser.getInstance().getUser().getChatUserId());

        chatService = ConnectycubeChatService.getInstance();
        if (chatService.isLoggedIn()) {
            presenter.getHistory(chatId);
        } else {
            chatService.login(user, new EntityCallback() {
                @Override
                public void onSuccess(Object o, Bundle bundle) {
                    ConnectycubeUsers.signIn(user).performAsync(new EntityCallback<ConnectycubeUser>() {
                        @Override
                        public void onSuccess(ConnectycubeUser user, Bundle args) {
                            presenter.getHistory(chatId);
                        }

                        @Override
                        public void onError(ResponseException error) {
                        }
                    });

                }

                @Override
                public void onError(ResponseException errors) {
                }
            });
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
}
