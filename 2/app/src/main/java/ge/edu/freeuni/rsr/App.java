package ge.edu.freeuni.rsr;

/*
 * created by tgeldiashvili on 6/6/2019
 */

import android.app.Application;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Build;

import com.connectycube.auth.session.ConnectycubeSettings;
import com.connectycube.chat.ConnectycubeChatService;
import com.google.firebase.FirebaseApp;
import com.google.firebase.iid.FirebaseInstanceId;

public class App extends Application {
    private static final String APP_ID = "769";
    private static final String AUTH_KEY = "62ZOdpsta6t6d-f";
    private static final String AUTH_SECRET = "yE9AqCx4O4hwkEp";
    private static final String ACCOUNT_KEY = "s19cvj_s-pHTuiyfh26n";
    static final String NOTIFICATION_CHANNEL_ID = "ge.edu.freeuni.rsr.Notification";

    private static App instance;

    public String getDeviceToken() {
        return deviceToken;
    }

    private String deviceToken = null;

    @Override
    public void onCreate() {
        super.onCreate();
        initApplication();
        initConnectyCube();

        FirebaseApp.initializeApp(this);

        FirebaseInstanceId.getInstance().getInstanceId().addOnSuccessListener(
                instanceIdResult -> deviceToken = instanceIdResult.getToken());

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "Chat Invites";
            int importance = NotificationManager.IMPORTANCE_HIGH;
            NotificationChannel channel = new NotificationChannel(NOTIFICATION_CHANNEL_ID, name, importance);
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }

    private void initConnectyCube() {
        ConnectycubeSettings.getInstance().init(this, APP_ID, AUTH_KEY, AUTH_SECRET);
        ConnectycubeSettings.getInstance().setAccountKey(ACCOUNT_KEY);

        ConnectycubeChatService.ConfigurationBuilder chatServiceConfigurationBuilder = new ConnectycubeChatService.ConfigurationBuilder();
        chatServiceConfigurationBuilder.setSocketTimeout(0);
        chatServiceConfigurationBuilder.setKeepAlive(true);
        chatServiceConfigurationBuilder.setUseTls(true);
        ConnectycubeChatService.setConfigurationBuilder(chatServiceConfigurationBuilder);
    }


    public static App getInstance() {
        return instance;
    }

    private void initApplication() {
        instance = this;
    }
}
