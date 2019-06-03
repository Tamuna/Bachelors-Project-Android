package ge.edu.freeuni.rsr.groupchat.chat;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.WindowManager;

import com.connectycube.auth.session.ConnectycubeSettings;
import com.connectycube.chat.ConnectycubeChatService;
import com.connectycube.chat.ConnectycubeRestChatService;
import com.connectycube.chat.model.ConnectycubeChatDialog;
import com.connectycube.chat.model.ConnectycubeChatMessage;
import com.connectycube.chat.model.ConnectycubeDialogType;
import com.connectycube.chat.utils.DialogUtils;
import com.connectycube.core.EntityCallback;
import com.connectycube.core.exception.ResponseException;
import com.connectycube.core.helper.StringifyArrayList;
import com.connectycube.users.ConnectycubeUsers;
import com.connectycube.users.model.ConnectycubeUser;

import java.util.ArrayList;
import java.util.List;

import ge.edu.freeuni.rsr.R;
import ge.edu.freeuni.rsr.groupchat.configuration.GroupPracticeConfigActivity;

public class GroupChatActivity extends AppCompatActivity implements GroupChatContract.GroupChatView {
    private static final String APP_ID = "769";
    private static final String AUTH_KEY = "62ZOdpsta6t6d-f";
    private static final String AUTH_SECRET = "yE9AqCx4O4hwkEp";
    private static final String ACCOUNT_KEY = "s19cvj_s-pHTuiyfh26n";


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

        int chatId = getIntent().getIntExtra(CHAT_ID, 0);


        ConnectycubeSettings.getInstance().init(this, APP_ID, AUTH_KEY, AUTH_SECRET);
        ConnectycubeSettings.getInstance().setAccountKey(ACCOUNT_KEY);


//
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


        ConnectycubeChatService.ConfigurationBuilder chatServiceConfigurationBuilder = new ConnectycubeChatService.ConfigurationBuilder();
        chatServiceConfigurationBuilder.setSocketTimeout(60);
        chatServiceConfigurationBuilder.setKeepAlive(true);
        chatServiceConfigurationBuilder.setUseTls(true); //By default TLS is disabled.
        ConnectycubeChatService.setConfigurationBuilder(chatServiceConfigurationBuilder);

        ConnectycubeChatService chatService = ConnectycubeChatService.getInstance();



        chatService.login(user, new EntityCallback() {

            @Override
            public void onSuccess(Object o, Bundle bundle) {

            }

            @Override
            public void onError(ResponseException errors) {

            }
        });

        ConnectycubeUsers.signIn(user).performAsync(new EntityCallback<ConnectycubeUser>() {
            @Override
            public void onSuccess(ConnectycubeUser user, Bundle args) {
                createDialog();
            }

            @Override
            public void onError(ResponseException error) {
                createDialog();
            }
        });


//        ArrayList<ConnectycubeUser> selectedUsers = new ArrayList<>();
//        selectedUsers.add(new ConnectycubeUser(1));
//        selectedUsers.add(new ConnectycubeUser(2));
//
//        ConnectycubeChatDialog dialog = DialogUtils.buildDialog(DialogUtils.createChatNameFromUserList(selectedUsers.toArray(new ConnectycubeUser[0])),
//                ConnectycubeDialogType.GROUP, DialogUtils.getUserIds(selectedUsers));
//
//        ConnectycubeRestChatService.createChatDialog(dialog).performAsync(new EntityCallback<ConnectycubeChatDialog>() {
//            @Override
//            public void onSuccess(ConnectycubeChatDialog createdDialog, Bundle params) {
//                Log.d("123", "123");
//            }
//
//            @Override
//            public void onError(ResponseException exception) {
//                Log.d("123", "123");
//            }
//        });


    }

    private void createDialog() {
        ArrayList<Integer> occupantIds = new ArrayList<>();
        occupantIds.add(128171);
        occupantIds.add(128174);
        occupantIds.add(128175);

        ConnectycubeChatDialog dialog = new ConnectycubeChatDialog();
        dialog.setType(ConnectycubeDialogType.GROUP);
        dialog.setOccupantsIds(occupantIds);
        dialog.setName("Hawaii party");

        ConnectycubeRestChatService.createChatDialog(dialog).performAsync(new EntityCallback<ConnectycubeChatDialog>() {
            @Override
            public void onSuccess(ConnectycubeChatDialog createdDialog, Bundle params) {
                Log.d("tamuna", "succ");
            }

            @Override
            public void onError(ResponseException exception) {
                Log.d("tamuna", exception.getMessage());
            }
        });
    }


}
