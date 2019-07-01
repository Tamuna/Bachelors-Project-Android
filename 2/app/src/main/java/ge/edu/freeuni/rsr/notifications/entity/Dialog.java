package ge.edu.freeuni.rsr.notifications.entity;

import java.util.List;

import ge.edu.freeuni.rsr.common.entity.User;

public class Dialog {

    private String dialogId;

    private List<User> occupants;

    public Dialog(String dialogId, List<User> occupantIds) {
        this.dialogId = dialogId;
        this.occupants = occupantIds;
    }

    public String getDialogId() {
        return dialogId;
    }


    public List<User> getOccupants() {
        return occupants;
    }
}
