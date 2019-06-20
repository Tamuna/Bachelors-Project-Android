package ge.edu.freeuni.rsr.groupchat.chat;

/*
 * created by tgeldiashvili on 6/3/2019
 */

import java.util.List;

import ge.edu.freeuni.rsr.auth.entity.User;

public interface GroupChatContract {
    interface GroupChatView {

    }

    interface GroupChatPresented {
        void getMembers(int chatId);
    }

    interface GroupChatInteractor {
        interface OnFinishListener {
            void onFriendsLoaded(List<User> friends);
        }

        void getMembers(int chatId, OnFinishListener listener);
    }
}
