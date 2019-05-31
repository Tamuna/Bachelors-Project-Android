package ge.edu.freeuni.rsr.groupchat.configuration.entity;

/*
 * created by tgeldiashvili on 5/31/2019
 */

public class User {
    private String name;

    public User(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
