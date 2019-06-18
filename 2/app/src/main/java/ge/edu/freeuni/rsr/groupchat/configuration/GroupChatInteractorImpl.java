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
        int id = 2;
        List<User> users = new ArrayList<>();
        users.add(new User(id, "თამუნა"));
        users.add(new User(id, "ლევანი"));
        users.add(new User(id, "ანდროიდი"));
        users.add(new User(id, "ვიღაც კაცი"));
        users.add(new User(id, "ვიღაც ქალი"));
        users.add(new User(id, "თამუნა"));
        users.add(new User(id, "ლევანი"));
        users.add(new User(id, "ანდროიდი"));
        users.add(new User(id, "ვიღაც კაცი"));
        users.add(new User(id, "ვიღაც ქალი"));
        users.add(new User(id, "თამუნა"));
        users.add(new User(id, "ლევანი"));
        users.add(new User(id, "ანდროიდი"));
        users.add(new User(id, "ვიღაც კაცი"));
        users.add(new User(id, "ვიღაც ქალი"));
        users.add(new User(id, "თამუნა"));
        users.add(new User(id, "ლევანი"));
        users.add(new User(id, "ანდროიდი"));
        users.add(new User(id, "ვიღაც კაცი"));
        users.add(new User(id, "ვიღაც ქალი"));
        onFinishListener.onFriendsLoaded(users);
    }
}
