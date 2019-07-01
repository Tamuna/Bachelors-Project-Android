package ge.edu.freeuni.rsr.groupchat.configuration;

/*
 * created by tgeldiashvili on 5/31/2019
 */

import android.os.Bundle;

import com.connectycube.chat.ConnectycubeChatService;
import com.connectycube.chat.ConnectycubeRestChatService;
import com.connectycube.chat.model.ConnectycubeChatDialog;
import com.connectycube.chat.model.ConnectycubeDialogType;
import com.connectycube.core.EntityCallback;
import com.connectycube.core.exception.ResponseException;
import com.connectycube.users.ConnectycubeUsers;
import com.connectycube.users.model.ConnectycubeUser;

import java.util.ArrayList;
import java.util.List;

import ge.edu.freeuni.rsr.AppUser;
import ge.edu.freeuni.rsr.common.entity.User;

public class GroupChatConfigPresenterImpl implements GroupChatConfigurationContract.GroupChatPresenter {
    private GroupChatConfigurationContract.GroupChatView view;
    private GroupChatConfigurationContract.GroupChatInteractor interactor;
    private List<User> highlightedFriends = new ArrayList<>();

    public GroupChatConfigPresenterImpl(GroupChatConfigurationContract.GroupChatView view, GroupChatConfigurationContract.GroupChatInteractor interactor) {
        this.view = view;
        this.interactor = interactor;
    }

    @Override
    public void getFriends() {
        interactor.getFriends(new OnFinishListenerImpl());
    }

    @Override
    public void selectFriend(User friend, int position) {
        if (highlightedFriends.contains(friend)) {
            highlightedFriends.remove(friend);
            view.unhighlight(friend);
        } else {
            highlightedFriends.add(friend);
            view.highlight(friend);
        }
    }

    @Override
    public void sendNotifications() {
        signInChat();
    }

    private void signInChat() {
        String username = "username" + AppUser.getInstance().getUser().getId();
        final ConnectycubeUser user = new ConnectycubeUser(username, username);
        user.setId(AppUser.getInstance().getUser().getChatUserId());
        if (ConnectycubeChatService.getInstance().isLoggedIn()) {
            createDialog(user);
        } else {
            ConnectycubeChatService.getInstance().login(user, new EntityCallback() {
                @Override
                public void onSuccess(Object o, Bundle bundle) {
                    createDialog(user);
                }

                @Override
                public void onError(ResponseException errors) {
                }
            });
        }

    }

    private void createDialog(ConnectycubeUser user) {
        ConnectycubeUsers.signIn(user).performAsync(new EntityCallback<ConnectycubeUser>() {
            @Override
            public void onSuccess(ConnectycubeUser user, Bundle args) {
                ConnectycubeChatDialog dialog = new ConnectycubeChatDialog();
                dialog.setType(ConnectycubeDialogType.GROUP);
                List<Integer> occupantIds = new ArrayList<>();
                List<String> userNames = new ArrayList<>();
                for (User friend : highlightedFriends) {
                    occupantIds.add(friend.getChatUserId());
                    userNames.add(friend.getUserName());
                }
                dialog.setOccupantsIds(occupantIds);
                dialog.setName("rsr group chat");

                ConnectycubeRestChatService.createChatDialog(dialog).performAsync(new EntityCallback<ConnectycubeChatDialog>() {
                    @Override
                    public void onSuccess(ConnectycubeChatDialog connectycubeChatDialog, Bundle bundle) {
                        view.startChat(connectycubeChatDialog.getDialogId());
                        interactor.sendNotifications(userNames, connectycubeChatDialog.getDialogId(), new OnFinishListenerImpl());
                    }

                    @Override
                    public void onError(ResponseException e) {

                    }
                });
            }

            @Override
            public void onError(ResponseException error) {

            }
        });

    }

    class OnFinishListenerImpl implements GroupChatConfigurationContract.GroupChatInteractor.OnFinishListener {

        @Override
        public void onFriendsLoaded(List<User> friends) {
            view.onDataLoaded(friends);
        }

        @Override
        public void onNotificationsSent() {
            view.showToast("თქვნ წარმატებით მოიწვიეთ მეგობრები ინტელექტუალურ ჩატში");
        }
    }
}
