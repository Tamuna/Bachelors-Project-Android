package ge.edu.freeuni.rsr.groupchat.chat;

/*
 * created by tgeldiashvili on 6/3/2019
 */

import java.util.HashMap;
import java.util.List;

import ge.edu.freeuni.rsr.common.entity.User;
import ge.edu.freeuni.rsr.groupchat.chat.entity.Message;

public interface GroupChatContract {
    interface GroupChatView {
        void onChatHistoryLoaded(List<Message> history);

        void displaySingleMessage(Message message);

        void showMessageSentError();

        void capAlreadyAcquired(String name);

        void hostAlreadyAcquired(String message);

        void displayHostCheckBox(boolean visibility);

        void displayCapCheckBox(boolean visibility);

        void startTimer();

        void showAnswerDialog(String s);
    }

    interface GroupChatPresenter {
        void getHistory(String chatId);

        void sendMessage(String toString, boolean roleCbChecked);

        void sendBecomeHostMessage();

        void sendBecomeCapMessage();

        void loginToChat(String chatId);
    }

    interface GroupChatInteractor {
        interface OnFinishListener {
            void onOccupantsLoaded(HashMap<Integer, User> history);
        }

        void getChatOccupants(List<Integer> chatIds, OnFinishListener listener);
    }
}
