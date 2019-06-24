package ge.edu.freeuni.rsr;

/*
 * created by tgeldiashvili on 6/18/2019
 */

import ge.edu.freeuni.rsr.common.entity.User;

public class AppUser {
    private static final AppUser instance = new AppUser();

    private User user;
    public static AppUser getInstance() {
        return instance;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }

}
