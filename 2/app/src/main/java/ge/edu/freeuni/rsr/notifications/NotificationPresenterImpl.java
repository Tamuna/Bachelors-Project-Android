package ge.edu.freeuni.rsr.notifications;

import java.util.List;

import ge.edu.freeuni.rsr.notifications.entity.Dialog;

public class NotificationPresenterImpl implements NotificationsContract.NotificationsPresenter {

    private NotificationsContract.NotificationsView view;
    private NotificationsContract.NotificationsInteractor interactor;

    public NotificationPresenterImpl(NotificationsContract.NotificationsView view,
                                     NotificationsContract.NotificationsInteractor interactor) {
        this.view = view;
        this.interactor = interactor;
    }


    @Override
    public void getDialogsForUser() {
        interactor.getChatOccupants(new OnFinishListenerImpl());
    }

    class OnFinishListenerImpl implements NotificationsContract.NotificationsInteractor.OnFinishListener {

        @Override
        public void onDialogsLoaded(List<Dialog> dialogs) {
            view.onDialogsLoaded(dialogs);
        }
    }

}
