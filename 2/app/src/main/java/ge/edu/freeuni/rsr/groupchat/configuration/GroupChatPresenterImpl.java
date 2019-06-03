package ge.edu.freeuni.rsr.groupchat.configuration;

/*
 * created by tgeldiashvili on 5/31/2019
 */

import java.util.ArrayList;
import java.util.List;

import ge.edu.freeuni.rsr.groupchat.configuration.entity.User;

public class GroupChatPresenterImpl implements GroupChatConfigurationContract.GroupChatPresenter {
    private GroupChatConfigurationContract.GroupChatView view;
    private GroupChatConfigurationContract.GroupChatInteractor interactor;
    private List<User> highlightedFriends = new ArrayList<>();
    private int id = 5;

    public GroupChatPresenterImpl(GroupChatConfigurationContract.GroupChatView view, GroupChatConfigurationContract.GroupChatInteractor interactor) {
        this.view = view;
        this.interactor = interactor;
    }

    @Override
    public void getFriends() {
        interactor.getFriends(new OnFinishListenerImpl(), id);
    }

    @Override
    public void selectFriend(User friend, int position) {
        if (highlightedFriends.contains(friend)) {
            highlightedFriends.remove(friend);
            view.unhighlight(position);
        } else {
            highlightedFriends.add(friend);
            view.highlight(position);
        }
    }

    class OnFinishListenerImpl implements GroupChatConfigurationContract.GroupChatInteractor.OnFinishListener {

        @Override
        public void onFriendsLoaded(List<User> friends) {
            view.onDataLoaded(friends);
        }
    }
}
