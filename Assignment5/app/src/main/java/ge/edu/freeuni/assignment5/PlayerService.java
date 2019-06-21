package ge.edu.freeuni.assignment5;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.content.res.Resources;
import android.media.MediaPlayer;
import android.os.IBinder;

import androidx.core.app.NotificationCompat;

import static ge.edu.freeuni.assignment5.Constants.FOREGROUND_SERVICE;
import static ge.edu.freeuni.assignment5.Constants.MAIN_ACTION;
import static ge.edu.freeuni.assignment5.Constants.NEXT_ACTION;
import static ge.edu.freeuni.assignment5.Constants.NOTIFICATION_CHANNEL_ID;
import static ge.edu.freeuni.assignment5.Constants.PREV_ACTION;

public class PlayerService extends Service {

    private static int audioIndex = 1;
    private static final int NUM_AUDIOS = 2;
    private MediaPlayer mp;

    @androidx.annotation.Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        switch (intent.getAction()) {
            case Constants.START_FOREGROUND_ACTION:
                Intent mainNotificationIntent = new Intent(this, MainActivity.class);
                mainNotificationIntent.setAction(MAIN_ACTION);
                mainNotificationIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                PendingIntent mainPandingIntent = PendingIntent.getActivity(this, 0, mainNotificationIntent, 0);

                Intent previousIntent = new Intent(this, PlayerService.class);
                previousIntent.setAction(PREV_ACTION);
                PendingIntent playPreviousIntent = PendingIntent.getService(this,
                        0,
                        previousIntent,
                        0);

                Intent nextIntent = new Intent(this, PlayerService.class);
                nextIntent.setAction(NEXT_ACTION);
                PendingIntent playNextIntent = PendingIntent.getService(this,
                        0,
                        nextIntent,
                        0);

                Notification notification = new NotificationCompat.Builder(this, NOTIFICATION_CHANNEL_ID)
                        .setAutoCancel(false)
                        .setContentTitle("Weird Music Player")
                        .setTicker("Awesome ticker")
                        .setContentText("Play weird tracks by clicking buttons below...")
                        .setOngoing(true)
                        .setContentIntent(mainPandingIntent)
                        .setSmallIcon(R.drawable.ic_launcher_foreground)
                        .addAction(R.drawable.ic_launcher_background, "Previous", playPreviousIntent)
                        .addAction(R.drawable.ic_launcher_background, "Next", playNextIntent)
                        .build();
                startForeground(FOREGROUND_SERVICE, notification);
                playAudio();
                break;
            case Constants.PREV_ACTION:
                playPrevAudio();
                break;
            case Constants.NEXT_ACTION:
                playNextAudio();
                break;
            case Constants.STOP_FOREGROUND_ACTION:
                stopAudio();
                break;
        }
        return START_STICKY;
    }

    private void playNextAudio() {
        audioIndex += 1;
        audioIndex %= NUM_AUDIOS;
        stopAudio();
        playAudio();
    }

    private void playPrevAudio() {
        if (audioIndex > 0) {
            audioIndex -= 1;
        } else {
            audioIndex = NUM_AUDIOS - 1;
        }
        stopAudio();
        playAudio();
    }

    private void playAudio() {
        String url = "audio" + audioIndex;
        Resources res = getResources();
        int resIdSound = res.getIdentifier(url, "raw", this.getPackageName());
        mp = MediaPlayer.create(this, resIdSound);
        mp.start();
    }

    private void stopAudio() {
        if (mp != null) {
            mp.stop();
            mp.release();
            mp = null;
        }
    }
}
