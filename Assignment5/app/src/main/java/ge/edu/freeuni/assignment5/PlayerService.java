package ge.edu.freeuni.assignment5;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.widget.Toast;

import androidx.core.app.NotificationCompat;

import static ge.edu.freeuni.assignment5.Constants.FOREGROUND_SERVICE;
import static ge.edu.freeuni.assignment5.Constants.MAIN_ACTION;
import static ge.edu.freeuni.assignment5.Constants.NOTIFICATION_CHANNEL_ID;
import static ge.edu.freeuni.assignment5.Constants.PREV_ACTION;
import static ge.edu.freeuni.assignment5.Constants.NEXT_ACTION;

public class PlayerService extends Service {

    @androidx.annotation.Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if (intent.getAction().equals(Constants.START_FOREGROUND_ACTION)) {

            Intent mainNotificationIntent = new Intent(this, MainActivity.class);
            mainNotificationIntent.setAction(MAIN_ACTION);
            mainNotificationIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            PendingIntent mainPangingIntent = PendingIntent.getActivity(this, 0, mainNotificationIntent, 0);


            Intent previouseIntent = new Intent(this, PlayerService.class);
            previouseIntent.setAction(PREV_ACTION);
            PendingIntent pendingIntent = PendingIntent.getService(this,
                    0,
                    previouseIntent,
                    0);


            Intent nextIntent = new Intent(this, PlayerService.class);
            nextIntent.setAction(NEXT_ACTION);
            PendingIntent pendingIntent3 = PendingIntent.getService(this,
                    0,
                    nextIntent,
                    0);


            Notification notification = new NotificationCompat.Builder(this, NOTIFICATION_CHANNEL_ID)
                    .setAutoCancel(false)
                    .setContentTitle("Awesome title")
                    .setTicker("Awesome ticker")
                    .setContentText("Indicator that the service is running")
                    .setOngoing(true)
                    .setContentIntent(mainPangingIntent)
                    .setSmallIcon(R.drawable.ic_launcher_foreground)
                    .addAction(R.drawable.ic_launcher_background, "Next", pendingIntent3)
                    .addAction(R.drawable.ic_launcher_background, "previous", pendingIntent)
                    .build();


            startForeground(FOREGROUND_SERVICE, notification);


        } else if (intent.getAction().equals(Constants.PREV_ACTION)) {

            Toast.makeText(this, "Previous clicked", Toast.LENGTH_SHORT).show();


        } else if (intent.getAction().equals(Constants.NEXT_ACTION)) {

            Toast.makeText(this, "Next clicked", Toast.LENGTH_SHORT).show();

        } else if (intent.getAction().equals(Constants.STOP_FOREGROUND_ACTION)) {

            stopForeground(true);
            stopSelf();
        }


        return START_STICKY;
    }
}
