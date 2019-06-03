package ge.edu.freeuni.rsr.groupchat.chat;

/*
 * created by tgeldiashvili on 6/3/2019
 */

import android.content.Context;

import com.connectycube.auth.session.ConnectycubeSettings;

public class GroupChatPresenterImpl implements GroupChatContract.GroupChatPresented {

    private static final String APP_ID = "769";
    private static final String AUTH_KEY = "62ZOdpsta6t6d-f";
    private static final String AUTH_SECRET = "yE9AqCx4O4hwkEp";
    private static final String ACCOUNT_KEY = "s19cvj_s-pHTuiyfh26n";

    public GroupChatPresenterImpl(Context context) {
        ConnectycubeSettings.getInstance().init(context, APP_ID, AUTH_KEY, AUTH_SECRET);
        ConnectycubeSettings.getInstance().setAccountKey(ACCOUNT_KEY);
    }

    @Override
    public void getMembers(int chatId) {

    }
}
