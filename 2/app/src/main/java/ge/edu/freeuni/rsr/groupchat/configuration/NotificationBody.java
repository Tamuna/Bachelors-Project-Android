package ge.edu.freeuni.rsr.groupchat.configuration;

import java.util.List;

public class NotificationBody {
    private String message;
    private List<String> friend_usernames;
    private String dialog_id;
    private List<Integer> chat_ids;
    private List<Integer> chat_user_ids;

    public NotificationBody(String dialog_id, String message, List<String> friend_usernames, List<Integer> chat_user_ids) {
        this.dialog_id = dialog_id;
        this.message = message;
        this.friend_usernames = friend_usernames;
        this.chat_user_ids = chat_user_ids;
    }

    public NotificationBody(List<Integer> chat_ids) {
        this.chat_ids = chat_ids;
    }
}
