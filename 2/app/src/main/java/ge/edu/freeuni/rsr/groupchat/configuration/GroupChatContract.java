package ge.edu.freeuni.rsr.groupchat.configuration;

/*
 * created by tgeldiashvili on 5/31/2019
 */

import java.util.List;

import ge.edu.freeuni.rsr.groupchat.configuration.entity.User;

public interface GroupChatContract {
    interface GroupChatView {
        void onDataLoaded(List<User> friends);
    }

    interface GroupChatPresenter {
        void getFriends();
    }

    interface GroupChatInteractor {
        interface OnFinishListener {
            void onFriendsLoaded(List<User> friends);
        }

        void getFriends(OnFinishListener onFinishListener, int userId);
    }
}
