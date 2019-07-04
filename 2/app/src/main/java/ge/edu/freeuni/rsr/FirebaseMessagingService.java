package ge.edu.freeuni.rsr;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.Intent;

import androidx.core.app.NotificationCompat;

import com.google.firebase.messaging.RemoteMessage;

import ge.edu.freeuni.rsr.auth.AuthActivity;
import ge.edu.freeuni.rsr.groupchat.chat.GroupChatActivity;

import static ge.edu.freeuni.rsr.App.NOTIFICATION_CHANNEL_ID;

public class FirebaseMessagingService extends com.google.firebase.messaging.FirebaseMessagingService {
    private static final int FOREGROUND_SERVICE = 101;

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        String dialogId = remoteMessage.getData().get("dialog_id");
        showNotification(remoteMessage.getData().get("message"), dialogId);
    }

    private void showNotification(String message, String dialogId) {
        Intent i;
        if (AppUser.getInstance().getUser() == null) {
            i = new Intent(this, AuthActivity.class);
        } else {
            i = new Intent(this, GroupChatActivity.class);
        }

        i.putExtra("dialog_id", dialogId);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, i, PendingIntent.FLAG_UPDATE_CURRENT);

        Notification notification = new NotificationCompat.Builder(this, NOTIFICATION_CHANNEL_ID)
                .setContentTitle("მოწვევა")
                .setContentText(message)
                .setAutoCancel(true)
                .setBadgeIconType(NotificationCompat.BADGE_ICON_LARGE)
                .setColor(getResources().getColor(R.color.text_color))
                .setSmallIcon(R.mipmap.ic_launcher_round)
                .setContentIntent(pendingIntent)
                .setDefaults(Notification.DEFAULT_SOUND | Notification.DEFAULT_VIBRATE | Notification.FLAG_AUTO_CANCEL)
                .build();
        startForeground(FOREGROUND_SERVICE, notification);
    }
}
