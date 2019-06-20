package ge.edu.freeuni.rsr.groupchat;

import java.util.List;

import ge.edu.freeuni.rsr.auth.entity.User;

public class FriendsResult {
    private List<User> friends;

    public List<User> getFriends() {
        return friends;
    }

    public void setFriends(List<User> friends) {
        this.friends = friends;
    }
}
