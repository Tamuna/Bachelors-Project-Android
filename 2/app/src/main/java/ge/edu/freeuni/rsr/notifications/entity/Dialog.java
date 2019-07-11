package ge.edu.freeuni.rsr.notifications.entity;

import java.util.List;

import ge.edu.freeuni.rsr.common.entity.User;

public class Dialog {

    private String dialog_id;

    private List<User> users;

    public Dialog(String dialog_id, List<User> users) {
        this.dialog_id = dialog_id;
        this.users = users;
    }

    public String getDialog_id() {
        return dialog_id;
    }


    public List<User> getUsers() {
        return users;
    }
}
