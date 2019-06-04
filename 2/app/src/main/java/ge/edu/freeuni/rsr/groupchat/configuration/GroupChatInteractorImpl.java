package ge.edu.freeuni.rsr.groupchat.configuration;

/*
 * created by tgeldiashvili on 5/31/2019
 */

import java.util.ArrayList;
import java.util.List;

import ge.edu.freeuni.rsr.groupchat.configuration.entity.User;

public class GroupChatInteractorImpl implements GroupChatConfigurationContract.GroupChatInteractor {
    @Override
    public void getFriends(OnFinishListener onFinishListener, int userId) {
        //TODO: api call
        List<User> users = new ArrayList<>();
        users.add(new User("თამუნა"));
        users.add(new User("ლევანი"));
        users.add(new User("ანდროი"));
        users.add(new User("ვიღაც კაცი"));
        users.add(new User("ვიღაც ქალი"));
        users.add(new User("თამუნა"));
        users.add(new User("ლევანი"));
        users.add(new User("ანდროი"));
        users.add(new User("ვიღაც კაცი"));
        users.add(new User("ვიღაც ქალი"));
        users.add(new User("თამუნა"));
        users.add(new User("ლევანი"));
        users.add(new User("ანდროი"));
        users.add(new User("ვიღაც კაცი"));
        users.add(new User("ვიღაც ქალი"));
        users.add(new User("თამუნა"));
        users.add(new User("ლევანი"));
        users.add(new User("ანდროი"));
        users.add(new User("ვიღაც კაცი"));
        users.add(new User("ვიღაც ქალი"));
        onFinishListener.onFriendsLoaded(users);
    }
}
