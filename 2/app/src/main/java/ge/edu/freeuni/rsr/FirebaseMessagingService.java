package ge.edu.freeuni.rsr;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.Intent;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.google.firebase.messaging.RemoteMessage;

import ge.edu.freeuni.rsr.auth.AuthActivity;
import ge.edu.freeuni.rsr.groupchat.chat.GroupChatActivity;
import ge.edu.freeuni.rsr.home.HomeActivity;

import static ge.edu.freeuni.rsr.App.NOTIFICATION_CHANNEL_ID;

public class FirebaseMessagingService extends com.google.firebase.messaging.FirebaseMessagingService {
    private static final int FOREGROUND_SERVICE = 101;

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        String dialogId = remoteMessage.getData().get("dialog_id");
        String friendUsername = remoteMessage.getData().get("friend_username");
        if (friendUsername == null)
            showChatInviteNotification(remoteMessage.getData().get("message"), dialogId);
        else
            showFriendRequestNotification(remoteMessage.getData().get("message"), friendUsername);
    }

    private void showFriendRequestNotification(String body, String friendUsername) {

        Intent i;
        if (AppUser.getInstance().getUser() == null) {
            i = new Intent(this, AuthActivity.class);
        } else {
            i = new Intent(this, HomeActivity.class);
        }

        i.putExtra("friend_username", friendUsername);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, i, PendingIntent.FLAG_UPDATE_CURRENT);
        showNotification("მეგობრობის თხოვნა", body, pendingIntent);
    }

    private void showChatInviteNotification(String body, String dialogId) {
        Intent i;
        if (AppUser.getInstance().getUser() == null) {
            i = new Intent(this, AuthActivity.class);
        } else {
            i = new Intent(this, GroupChatActivity.class);
        }
        i.putExtra("dialog_id", dialogId);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, i, PendingIntent.FLAG_UPDATE_CURRENT);
        showNotification("ჩატში მოწვევა", body, pendingIntent);
    }

    private void showNotification(String title, String body, PendingIntent pendingIntent) {
        Notification notification = new NotificationCompat.Builder(this, NOTIFICATION_CHANNEL_ID)
                .setAutoCancel(true)
                .setContentTitle(title)
                .setContentText(body)
                .setOngoing(true)
                .setBadgeIconType(NotificationCompat.BADGE_ICON_SMALL)
                .setColor(getResources().getColor(R.color.text_color))
                .setContentIntent(pendingIntent)
                .setSmallIcon(R.drawable.ic_small_owl)
                .setDefaults(Notification.DEFAULT_SOUND | Notification.DEFAULT_VIBRATE)
                .build();
        NotificationManagerCompat.from(this).notify(101, notification);
    }
}
