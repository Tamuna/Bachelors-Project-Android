package ge.edu.freeuni.rsr.groupchat.configuration;

import java.util.List;

public class NotificationBody {
    private String message;
    private List<String> friend_usernames;

    public NotificationBody(String message, List<String> friend_usernames) {
        this.message = message;
        this.friend_usernames = friend_usernames;
    }
}
