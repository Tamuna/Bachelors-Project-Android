package ge.edu.freeuni.rsr.groupchat.configuration;

/*
 * created by tgeldiashvili on 5/31/2019
 */

import java.util.List;

import ge.edu.freeuni.rsr.groupchat.configuration.entity.User;

public class GroupChatPresenterImpl implements GroupChatContract.GroupChatPresenter {
    private GroupChatContract.GroupChatView view;
    private GroupChatContract.GroupChatInteractor interactor;
    private int id = 5;

    public GroupChatPresenterImpl(GroupChatContract.GroupChatView view, GroupChatContract.GroupChatInteractor interactor) {
        this.view = view;
        this.interactor = interactor;
    }

    @Override
    public void getFriends() {
        interactor.getFriends(new OnFinishListenerImpl(), id);
    }

    class OnFinishListenerImpl implements GroupChatContract.GroupChatInteractor.OnFinishListener {

        @Override
        public void onFriendsLoaded(List<User> friends) {
            view.onDataLoaded(friends);
        }
    }
}
