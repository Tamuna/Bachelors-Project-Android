package ge.edu.freeuni.rsr;

/*
 * created by tgeldiashvili on 6/6/2019
 */

import android.app.Application;
import android.util.Log;

import com.connectycube.auth.session.ConnectycubeSettings;
import com.connectycube.chat.ConnectycubeChatService;
import com.google.firebase.FirebaseApp;
import com.google.firebase.iid.FirebaseInstanceId;

public class App extends Application {
    private static final String APP_ID = "769";
    private static final String AUTH_KEY = "62ZOdpsta6t6d-f";
    private static final String AUTH_SECRET = "yE9AqCx4O4hwkEp";
    private static final String ACCOUNT_KEY = "s19cvj_s-pHTuiyfh26n";

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


//        FirebaseMessaging.getInstance().subscribeToTopic("weather");
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
