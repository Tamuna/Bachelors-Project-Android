package ge.edu.freeuni.rsr.groupchat.chat;

/*
 * created by tgeldiashvili on 6/3/2019
 */

import android.os.Bundle;

import com.connectycube.chat.ConnectycubeChatService;
import com.connectycube.chat.ConnectycubeRestChatService;
import com.connectycube.chat.exception.ChatException;
import com.connectycube.chat.listeners.ChatDialogMessageListener;
import com.connectycube.chat.model.ConnectycubeChatDialog;
import com.connectycube.chat.model.ConnectycubeChatMessage;
import com.connectycube.chat.request.MessageGetBuilder;
import com.connectycube.core.EntityCallback;
import com.connectycube.core.exception.ResponseException;
import com.connectycube.users.ConnectycubeUsers;
import com.connectycube.users.model.ConnectycubeUser;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import ge.edu.freeuni.rsr.AppUser;
import ge.edu.freeuni.rsr.common.entity.User;
import ge.edu.freeuni.rsr.groupchat.chat.entity.Message;

public class GroupChatPresenterImpl implements GroupChatContract.GroupChatPresenter {

    private GroupChatContract.GroupChatView view;
    private GroupChatContract.GroupChatInteractor interactor;
    private HashMap<Integer, User> occupants;
    private ConnectycubeChatDialog createdDialog;
    private User host;
    private User captain;

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
            if (message != null && message.length() != 0) {
                ConnectycubeChatMessage chatMessage = new ConnectycubeChatMessage();
                chatMessage.setBody(message);
                chatMessage.setSaveToHistory(true);
                createdDialog.sendMessage(chatMessage);
            }
        } catch (Exception e) {
            view.showMessageSentError();
        }
    }

    private static String PRE_BECOME_HOST = "~~HOSTING:";
    private static String PRE_BECOME_CAP = "~~CAPTAINING:";

    @Override
    public void sendBecomeHostMessage() {
        sendMessage(PRE_BECOME_HOST + AppUser.getInstance().getUser().getUserName());
    }

    @Override
    public void sendBecomeCapMessage() {
        sendMessage(PRE_BECOME_CAP + AppUser.getInstance().getUser().getUserName());
    }

    @Override
    public void loginToChat(String chatId) {
        ConnectycubeUser user = new ConnectycubeUser("username" + AppUser.getInstance().getUser().getId(), "username" + AppUser.getInstance().getUser().getId());
        user.setId(AppUser.getInstance().getUser().getChatUserId());
        ConnectycubeChatService.getInstance().login(user, new EntityCallback() {
            @Override
            public void onSuccess(Object o, Bundle bundle) {
                ConnectycubeUsers.signIn(user).performAsync(new EntityCallback<ConnectycubeUser>() {
                    @Override
                    public void onSuccess(ConnectycubeUser user, Bundle args) {
                        getHistory(chatId);
                    }

                    @Override
                    public void onError(ResponseException error) {
                    }
                });
            }

            @Override
            public void onError(ResponseException errors) {
            }
        });
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
                String message = connectycubeChatMessage.getBody();
                if (message.startsWith(PRE_BECOME_HOST)) {
                    if(AppUser.getInstance().getUser().getChatUserId().equals(connectycubeChatMessage.getSenderId())){
                        view.displayHostCheckBox();
                    }
                    host = occupants.get(connectycubeChatMessage.getSenderId());
                    view.hostAlreadyAcquired(host.getUserName());
                } else if (message.startsWith(PRE_BECOME_CAP)) {
                    if(AppUser.getInstance().getUser().getChatUserId().equals(connectycubeChatMessage.getSenderId())){
                        view.displayCapCheckBox();
                    }
                    captain = occupants.get(connectycubeChatMessage.getSenderId());
                    view.capAlreadyAcquired(captain.getUserName());
                } else {
                    boolean isQuestion = host != null && connectycubeChatMessage.getSenderId().equals(host.getChatUserId());
                    view.displaySingleMessage(new Message(occupants.get(connectycubeChatMessage.getSenderId()), message,
                            isQuestion, false));
                }
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
                Collections.reverse(history);
                for (ConnectycubeChatMessage msg : messages) {
                    String message = msg.getBody();
                    if (message.startsWith(PRE_BECOME_HOST)) {
                        host = occupants.get(msg.getSenderId());
                    } else if (message.startsWith(PRE_BECOME_CAP)) {
                        captain = occupants.get(msg.getSenderId());
                    } else {
                        boolean isQuestion = host != null && msg.getSenderId().equals(host.getChatUserId());
                        history.add(new Message(occupants.get(msg.getSenderId()), message, isQuestion, false));
                    }
                }
                view.onChatHistoryLoaded(history);
            }

            @Override
            public void onError(ResponseException error) {

            }
        });
    }
}
