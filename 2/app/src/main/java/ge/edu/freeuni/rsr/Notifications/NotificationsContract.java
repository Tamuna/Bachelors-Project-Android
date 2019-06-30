package ge.edu.freeuni.rsr.Notifications;

import java.util.List;

import ge.edu.freeuni.rsr.Notifications.entity.Dialog;
import ge.edu.freeuni.rsr.common.entity.User;

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

