package ge.edu.freeuni.rsr.groupchat.chat;

import java.util.HashMap;
import java.util.List;

import ge.edu.freeuni.rsr.common.entity.RsrResponse;
import ge.edu.freeuni.rsr.common.entity.User;
import ge.edu.freeuni.rsr.groupchat.FriendsResult;
import ge.edu.freeuni.rsr.groupchat.configuration.NotificationBody;
import ge.edu.freeuni.rsr.network.Api;
import ge.edu.freeuni.rsr.network.RetrofitInstance;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class GroupChatInteractorImpl implements GroupChatContract.GroupChatInteractor {
    private Api api;
    private Retrofit retrofit;

    public GroupChatInteractorImpl() {
        retrofit = RetrofitInstance.getRetrofitInstance();
        api = retrofit.create(Api.class);
    }

    @Override
    public void getChatOccupants(List<Integer> chatIds, OnFinishListener listener) {
        api.getChatOccupants(new NotificationBody(chatIds)).enqueue(new Callback<RsrResponse<FriendsResult>>() {
            @Override
            public void onResponse(Call<RsrResponse<FriendsResult>> call, Response<RsrResponse<FriendsResult>> response) {
                listener.onOccupantsLoaded(transformToMap(response.body().getResult().getFriends()));
            }

            @Override
            public void onFailure(Call<RsrResponse<FriendsResult>> call, Throwable t) {

            }
        });
    }

    private HashMap<Integer, User> transformToMap(List<User> occupants) {
        HashMap<Integer, User> chatUsers = new HashMap<>();
        for (User occupant : occupants) {
            chatUsers.put(occupant.getChatUserId(), occupant);
        }
        return chatUsers;
    }
}
