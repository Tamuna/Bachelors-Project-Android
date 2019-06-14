package ge.edu.freeuni.rsr;

/*
 * created by tgeldiashvili on 6/6/2019
 */

import android.app.Application;

import com.connectycube.auth.session.ConnectycubeSettings;
import com.connectycube.chat.ConnectycubeChatService;

public class App extends Application {
    private static final String APP_ID = "769";
    private static final String AUTH_KEY = "62ZOdpsta6t6d-f";
    private static final String AUTH_SECRET = "yE9AqCx4O4hwkEp";
    private static final String ACCOUNT_KEY = "s19cvj_s-pHTuiyfh26n";

    private static App instance;

    @Override
    public void onCreate() {
        super.onCreate();
        initApplication();
        initConnectyCube();
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