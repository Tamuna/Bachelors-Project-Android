package ge.edu.freeuni.rsr.notifications;

import java.util.List;

import ge.edu.freeuni.rsr.common.entity.User;
import ge.edu.freeuni.rsr.notifications.entity.Dialog;

public interface NotificationsContract {
    interface NotificationsView {
        void onUserChatLoaded(Dialog dialogs);
    }

    interface NotificationsPresenter {
        void getUserChats();
    }

    interface NotificationsInteractor {
        interface OnFinishListener {
            void onOccupantsLoaded(List<User> occupants);
        }

        void getChatOccupants(OnFinishListener listener, List<Integer> chatIds);
    }
}

