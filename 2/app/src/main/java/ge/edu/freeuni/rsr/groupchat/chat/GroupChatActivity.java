package ge.edu.freeuni.rsr.groupchat.chat;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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
import com.connectycube.chat.ConnectycubeRestChatService;
import com.connectycube.chat.exception.ChatException;
import com.connectycube.chat.listeners.ChatDialogMessageListener;
import com.connectycube.chat.model.ConnectycubeChatDialog;
import com.connectycube.chat.model.ConnectycubeChatMessage;
import com.connectycube.chat.model.ConnectycubeDialogType;
import com.connectycube.chat.request.MessageGetBuilder;
import com.connectycube.core.EntityCallback;
import com.connectycube.core.exception.ResponseException;
import com.connectycube.users.ConnectycubeUsers;
import com.connectycube.users.model.ConnectycubeUser;

import java.util.ArrayList;
import java.util.Collections;
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

    private MessagesRecyclerAdapter adapter;

    private ConnectycubeChatDialog createdDialog;
    private ConnectycubeChatService chatService;

    private boolean joined = false;

    @OnClick(R.id.imgSend)
    void onSendMessageClicked() {
        try {
            ConnectycubeChatMessage chatMessage = new ConnectycubeChatMessage();
            chatMessage.setBody(etMessage.getText().toString());
            etMessage.setText("");
            chatMessage.setSaveToHistory(true);
            createdDialog.sendMessage(chatMessage);
        } catch (Exception e) {
            Toast.makeText(this, getString(R.string.cannot_send_message), Toast.LENGTH_SHORT).show();
        }
    }


    protected List<ConnectycubeChatMessage> messagesList;


    public static final String CHAT_ID = "CHAT_ID";

    public static void start(Context previous, int chatId) {
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

        int chatId = getIntent().getIntExtra(CHAT_ID, 0);


        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setStackFromEnd(true);
        rvMessages.setLayoutManager(layoutManager);

        adapter = new MessagesRecyclerAdapter();
        rvMessages.setAdapter(adapter);

        final ConnectycubeUser user = new ConnectycubeUser("tamuna", "12345678");
        user.setId(128171);
//        user.setLogin("tamuna1");
//        user.setPassword("12345678");
//
//        ConnectycubeUsers.signUp(user).performAsync(new EntityCallback<ConnectycubeUser>() {
//            @Override
//            public void onSuccess(ConnectycubeUser userHere, Bundle args) {
//                user.setId(userHere.getId());
//                }
//
//
//            @Override
//            public void onError(ResponseException error) {
//                Log.d("tamuna","error1");
//            }
//        });


        chatService = ConnectycubeChatService.getInstance();
        if (chatService.isLoggedIn()) {
            createDialog();
        } else {
            chatService.login(user, new EntityCallback() {

                @Override
                public void onSuccess(Object o, Bundle bundle) {
                    createDialog();
                }

                @Override
                public void onError(ResponseException errors) {
                }
            });
        }

//        TODO as user is registered register him in connectycube too
        ConnectycubeUsers.signIn(user).performAsync(new EntityCallback<ConnectycubeUser>() {
            @Override
            public void onSuccess(ConnectycubeUser user, Bundle args) {

            }

            @Override
            public void onError(ResponseException error) {
                createDialog();
            }
        });
    }

    private void createDialog() {
        ArrayList<Integer> occupantIds = new ArrayList<>();
        occupantIds.add(128171);
        occupantIds.add(128174);
        occupantIds.add(128175);

        ConnectycubeChatDialog dialog = new ConnectycubeChatDialog();
        dialog.setType(ConnectycubeDialogType.GROUP);
        dialog.setOccupantsIds(occupantIds);
        dialog.setName("Rsr group chat");


        ConnectycubeRestChatService.getChatDialogById("5cf945f0ca8bf474993f021e").performAsync(new EntityCallback<ConnectycubeChatDialog>() {
            @Override
            public void onSuccess(ConnectycubeChatDialog createdDialog, Bundle params) {
                GroupChatActivity.this.createdDialog = createdDialog;
                MessageGetBuilder messageGetBuilder = new MessageGetBuilder();
                messageGetBuilder.sortDesc("date_sent");


                ConnectycubeRestChatService.getDialogMessages(createdDialog, messageGetBuilder).performAsync(new EntityCallback<ArrayList<ConnectycubeChatMessage>>() {
                    @Override
                    public void onSuccess(ArrayList<ConnectycubeChatMessage> messages, Bundle bundle) {
                        List<Message> history = new ArrayList<>();
                        for (ConnectycubeChatMessage msg : messages) {
                            history.add(new Message(null, msg.getBody(), false, false));
                        }
                        Collections.reverse(history);
                        adapter.setData(history);
                        loaderView.setVisibility(View.GONE);
                    }

                    @Override
                    public void onError(ResponseException error) {

                    }
                });

                createdDialog.addMessageListener(new ChatDialogMessageListener() {
                    @Override
                    public void processMessage(String s, ConnectycubeChatMessage connectycubeChatMessage, Integer integer) {
                        adapter.setSingleData(new Message(null, connectycubeChatMessage.getBody(), false, false));
                        rvMessages.scrollToPosition(adapter.getItemCount() - 1);
//
                    }

                    @Override
                    public void processError(String s, ChatException e, ConnectycubeChatMessage connectycubeChatMessage, Integer integer) {

                    }
                });

//                GroupChatActivity.this.createdDialog = createdDialog;
                join();
//                IncomingMessagesManager incomingMessagesManager = chatService.getIncomingMessagesManager();
//
//                incomingMessagesManager.addDialogMessageListener(new ChatDialogMessageListener() {
//                    @Override
//                    public void processMessage(String dialogId, ConnectycubeChatMessage message, Integer senderId) {
//                        adapter.setSingleData(new Message(null, message.getBody(), false, false));
//                        rvMessages.scrollToPosition(adapter.getItemCount() - 1);
//                    }
//
//                    @Override
//                    public void processError(String dialogId, ChatException exception, ConnectycubeChatMessage message, Integer senderId) {
//
//                    }
//                });
            }

            @Override
            public void onError(ResponseException exception) {
                Log.d("tamuna", exception.getMessage());
            }
        });


    }

    private void join() {
        createdDialog.join(new EntityCallback() {
            @Override
            public void onSuccess(Object o, Bundle bundle) {

            }

            @Override
            public void onError(ResponseException e) {

            }
        });
    }
}
