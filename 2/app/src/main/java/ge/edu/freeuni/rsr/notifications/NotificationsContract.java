package ge.edu.freeuni.rsr.notifications;

import java.util.List;

import ge.edu.freeuni.rsr.notifications.entity.Dialog;

public interface NotificationsContract {
    interface NotificationsView {
        void onDialogsLoaded(List<Dialog> dialogs);
    }

    interface NotificationsPresenter {
        void getDialogsForUser();
    }

    interface NotificationsInteractor {
        interface OnFinishListener {
            void onDialogsLoaded(List<Dialog> dialogs);
        }

        void getChatOccupants(OnFinishListener listener);
    }
}

