package ge.edu.freeuni.rsr.Notifications;

import android.os.Bundle;

import com.connectycube.chat.ConnectycubeRestChatService;
import com.connectycube.chat.model.ConnectycubeChatDialog;
import com.connectycube.core.EntityCallback;
import com.connectycube.core.exception.ResponseException;
import com.connectycube.core.request.RequestGetBuilder;

import java.util.ArrayList;
import java.util.List;

import ge.edu.freeuni.rsr.AppUser;
import ge.edu.freeuni.rsr.Notifications.entity.Dialog;

public class NotificationPresenterImpl implements NotificationsContract.NotificationsPresenter {

    private NotificationsContract.NotificationsView view;
    private NotificationsContract.NotificationsInteractor interactor;

    public NotificationPresenterImpl(NotificationsContract.NotificationsView view,
                                     NotificationsContract.NotificationsInteractor interactor) {
        this.view = view;
        this.interactor = interactor;
    }

    @Override
    public void getUserChats() {
        RequestGetBuilder requestBuilder = new RequestGetBuilder();
        requestBuilder.sortAsc("last_message_date_sent");


        ConnectycubeRestChatService.getChatDialogs(null, requestBuilder).performAsync(new EntityCallback<ArrayList<ConnectycubeChatDialog>>() {
            @Override
            public void onSuccess(ArrayList<ConnectycubeChatDialog> connectycubeChatDialogs, Bundle bundle) {
                getUsersInDialogs(filterOnCurrentUser(connectycubeChatDialogs));

            }

            @Override
            public void onError(ResponseException e) {

            }
        });
    }

    private void getUsersInDialogs(List<ConnectycubeChatDialog> filtereDialogs) {
        for (ConnectycubeChatDialog dialog : filtereDialogs) {
            interactor.getChatOccupants(occupants -> {
                view.onUserChatLoaded(new Dialog(dialog.getDialogId(), occupants));
            }, dialog.getOccupants());
        }
    }

    private List<ConnectycubeChatDialog> filterOnCurrentUser(ArrayList<ConnectycubeChatDialog> connectycubeChatDialogs) {
        List<ConnectycubeChatDialog> userDialog = new ArrayList<>();
        for (ConnectycubeChatDialog dialog : connectycubeChatDialogs) {
            if (dialog.getOccupants().contains(AppUser.getInstance().getUser().getChatUserId())) {
                userDialog.add(dialog);
            }
        }
        return userDialog;
    }


}
