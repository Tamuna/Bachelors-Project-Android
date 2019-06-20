package ge.edu.freeuni.rsr.groupchat.configuration;

/*
 * created by tgeldiashvili on 5/31/2019
 */

import java.util.List;

import ge.edu.freeuni.rsr.auth.entity.User;

public interface GroupChatConfigurationContract {
    interface GroupChatView {
        void onDataLoaded(List<User> friends);

        void highlight(int position);

        void unhighlight(int position);
    }

    interface GroupChatPresenter {
        void getFriends();

        void selectFriend(User friend, int position);
    }

    interface GroupChatInteractor {
        interface OnFinishListener {
            void onFriendsLoaded(List<User> friends);
        }

        void getFriends(OnFinishListener onFinishListener, int userId);
    }
}
