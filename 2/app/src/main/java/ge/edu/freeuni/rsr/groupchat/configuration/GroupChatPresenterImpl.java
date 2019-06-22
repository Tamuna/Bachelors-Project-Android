package ge.edu.freeuni.rsr.groupchat.configuration;

/*
 * created by tgeldiashvili on 5/31/2019
 */

import java.util.ArrayList;
import java.util.List;

import ge.edu.freeuni.rsr.common.entity.User;

public class GroupChatPresenterImpl implements GroupChatConfigurationContract.GroupChatPresenter {
    private GroupChatConfigurationContract.GroupChatView view;
    private GroupChatConfigurationContract.GroupChatInteractor interactor;
    private List<String> highlightedFriends = new ArrayList<String>();

    public GroupChatPresenterImpl(GroupChatConfigurationContract.GroupChatView view, GroupChatConfigurationContract.GroupChatInteractor interactor) {
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
            view.unhighlight(position);
        } else {
            highlightedFriends.add(friend.getUserName());
            view.highlight(position);
        }
    }

    @Override
    public void sendNotifications() {
        interactor.sendNotifications(highlightedFriends, new OnFinishListenerImpl());
    }

    class OnFinishListenerImpl implements GroupChatConfigurationContract.GroupChatInteractor.OnFinishListener {

        @Override
        public void onFriendsLoaded(List<User> friends) {
            view.onDataLoaded(friends);
        }

        @Override
        public void onNotificationsSent() {
            view.startChat("თქვნ წარმატებით მოიწვიეთ მეგობრები ინტელექტუალურ ჩატში");
        }
    }
}
