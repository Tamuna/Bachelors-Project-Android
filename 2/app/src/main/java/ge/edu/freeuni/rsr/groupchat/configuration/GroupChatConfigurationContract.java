package ge.edu.freeuni.rsr.groupchat.configuration;

/*
 * created by tgeldiashvili on 5/31/2019
 */

import java.util.List;

import ge.edu.freeuni.rsr.common.entity.User;

public interface GroupChatConfigurationContract {
    interface GroupChatView {
        void onDataLoaded(List<User> friends);

        void highlight(User user);

        void unhighlight(User user);

        void startChat(String s);

        void showToast(String s);
    }

    interface GroupChatPresenter {
        void getFriends();

        void selectFriend(User friend, int position);

        void sendNotifications();
    }

    interface GroupChatInteractor {
        interface OnFinishListener {
            void onFriendsLoaded(List<User> friends);

            void onNotificationsSent();
        }

        void sendNotifications(List<String> highlightedFriends, String dialogId, OnFinishListener onFinishListener);

        void getFriends(OnFinishListener onFinishListener);
    }
}
