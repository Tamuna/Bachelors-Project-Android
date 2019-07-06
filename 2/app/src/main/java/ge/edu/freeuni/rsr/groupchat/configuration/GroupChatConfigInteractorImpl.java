package ge.edu.freeuni.rsr.groupchat.configuration;

/*
 * created by tgeldiashvili on 5/31/2019
 */


import java.util.List;

import ge.edu.freeuni.rsr.AppUser;
import ge.edu.freeuni.rsr.common.entity.RsrResponse;
import ge.edu.freeuni.rsr.groupchat.FriendsResult;
import ge.edu.freeuni.rsr.network.Api;
import ge.edu.freeuni.rsr.network.RetrofitInstance;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class GroupChatConfigInteractorImpl implements GroupChatConfigurationContract.GroupChatInteractor {


    private Api api;
    private Retrofit retrofit;

    public GroupChatConfigInteractorImpl() {
        retrofit = RetrofitInstance.getRetrofitInstance();
        api = retrofit.create(Api.class);
    }

    @Override
    public void sendNotifications(List<String> highlightedFriends, String dialogId, List<Integer> chatUserIds, OnFinishListener onFinishListener) {

        api.sendNotifications(new NotificationBody(dialogId, AppUser.getInstance().getUser().getUserName() + " გიწვევთ ინტელექტუალურ ჩატში!", highlightedFriends, chatUserIds)).enqueue(new Callback<RsrResponse<String>>() {
            @Override
            public void onResponse(Call<RsrResponse<String>> call, Response<RsrResponse<String>> response) {
                if (response.body() != null) {
                    onFinishListener.onNotificationsSent();
                }
            }

            @Override
            public void onFailure(Call<RsrResponse<String>> call, Throwable t) {

            }
        });
    }

    @Override
    public void getFriends(OnFinishListener onFinishListener) {
        api.getFriends().enqueue(new Callback<RsrResponse<FriendsResult>>() {
            @Override
            public void onResponse(Call<RsrResponse<FriendsResult>> call, Response<RsrResponse<FriendsResult>> response) {
                if (response.body() != null && response.body().getError() == null) {
                    onFinishListener.onFriendsLoaded(response.body().getResult().getFriends());
                }
            }

            @Override
            public void onFailure(Call<RsrResponse<FriendsResult>> call, Throwable t) {

            }
        });

    }
}
