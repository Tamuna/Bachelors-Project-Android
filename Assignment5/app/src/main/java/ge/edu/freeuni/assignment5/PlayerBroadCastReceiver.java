package ge.edu.freeuni.assignment5;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import static ge.edu.freeuni.assignment5.Constants.START_FOREGROUND_ACTION;

public class PlayerBroadCastReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Intent startIntent = new Intent(context, PlayerService.class);
        startIntent.setAction(START_FOREGROUND_ACTION);
        context.startService(startIntent);
    }
}
