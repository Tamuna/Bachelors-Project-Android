package ge.edu.freeuni.rsr.groupchat.chat;

/*
 * created by tgeldiashvili on 6/3/2019
 */

import android.os.Bundle;

import com.connectycube.chat.ConnectycubeRestChatService;
import com.connectycube.chat.exception.ChatException;
import com.connectycube.chat.listeners.ChatDialogMessageListener;
import com.connectycube.chat.model.ConnectycubeChatDialog;
import com.connectycube.chat.model.ConnectycubeChatMessage;
import com.connectycube.chat.request.MessageGetBuilder;
import com.connectycube.core.EntityCallback;
import com.connectycube.core.exception.ResponseException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import ge.edu.freeuni.rsr.common.entity.User;
import ge.edu.freeuni.rsr.groupchat.chat.entity.Message;

public class GroupChatPresenterImpl implements GroupChatContract.GroupChatPresenter {

    private GroupChatContract.GroupChatView view;
    private GroupChatContract.GroupChatInteractor interactor;
    private HashMap<Integer, User> occupants;
    private ConnectycubeChatDialog createdDialog;

    public GroupChatPresenterImpl(GroupChatContract.GroupChatView view, GroupChatContract.GroupChatInteractor interactor) {
        this.view = view;
        this.interactor = interactor;
    }

    @Override
    public void getHistory(String chatId) {
        ConnectycubeRestChatService.getChatDialogById(chatId).performAsync(new EntityCallback<ConnectycubeChatDialog>() {
            @Override
            public void onSuccess(ConnectycubeChatDialog createdDialog, Bundle params) {
                GroupChatPresenterImpl.this.createdDialog = createdDialog;
                List<Integer> occupants = createdDialog.getOccupants();
                interactor.getChatOccupants(occupants, new OnFinishListenerImpl());
            }

            @Override
            public void onError(ResponseException exception) {
            }
        });

    }

    @Override
    public void sendMessage(String message) {
        try {
            ConnectycubeChatMessage chatMessage = new ConnectycubeChatMessage();
            chatMessage.setBody(message);
            chatMessage.setSaveToHistory(true);
            createdDialog.sendMessage(chatMessage);
        } catch (Exception e) {
            view.showMessageSentError();
        }
    }


    class OnFinishListenerImpl implements GroupChatContract.GroupChatInteractor.OnFinishListener {

        @Override
        public void onOccupantsLoaded(HashMap<Integer, User> occupants) {
            loadHistory();
            addIncomingMessageListener();
            joinChat();
            GroupChatPresenterImpl.this.occupants = occupants;
        }
    }

    private void joinChat() {
        createdDialog.join(new EntityCallback() {
            @Override
            public void onSuccess(Object o, Bundle bundle) {

            }

            @Override
            public void onError(ResponseException e) {

            }
        });
    }

    private void addIncomingMessageListener() {
        createdDialog.addMessageListener(new ChatDialogMessageListener() {
            @Override
            public void processMessage(String s, ConnectycubeChatMessage connectycubeChatMessage, Integer integer) {
                view.displaySingleMessage(new Message(occupants.get(connectycubeChatMessage.getSenderId()), connectycubeChatMessage.getBody(), false, false));
            }

            @Override
            public void processError(String s, ChatException e, ConnectycubeChatMessage connectycubeChatMessage, Integer integer) {

            }
        });
    }

    private void loadHistory() {

        MessageGetBuilder messageGetBuilder = new MessageGetBuilder();
        messageGetBuilder.sortDesc("date_sent");


        ConnectycubeRestChatService.getDialogMessages(createdDialog, messageGetBuilder).performAsync(new EntityCallback<ArrayList<ConnectycubeChatMessage>>() {
            @Override
            public void onSuccess(ArrayList<ConnectycubeChatMessage> messages, Bundle bundle) {
                List<Message> history = new ArrayList<>();
                for (ConnectycubeChatMessage msg : messages) {
                    history.add(new Message(occupants.get(msg.getSenderId()), msg.getBody(), false, false));
                }
                Collections.reverse(history);
                view.onChatHistoryLoaded(history);
            }

            @Override
            public void onError(ResponseException error) {

            }
        });
    }
}
