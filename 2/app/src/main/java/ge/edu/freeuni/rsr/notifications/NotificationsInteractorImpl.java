package ge.edu.freeuni.rsr.notifications;

import java.util.List;

import ge.edu.freeuni.rsr.common.entity.RsrResponse;
import ge.edu.freeuni.rsr.groupchat.FriendsResult;
import ge.edu.freeuni.rsr.groupchat.configuration.NotificationBody;
import ge.edu.freeuni.rsr.network.Api;
import ge.edu.freeuni.rsr.network.RetrofitInstance;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class NotificationsInteractorImpl implements NotificationsContract.NotificationsInteractor {


    private Api api;
    private Retrofit retrofit;

    public NotificationsInteractorImpl() {
        retrofit = RetrofitInstance.getRetrofitInstance();
        api = retrofit.create(Api.class);
    }

    @Override
    public void getChatOccupants(OnFinishListener listener, List<Integer> chatIds) {
        api.getChatOccupants(new NotificationBody(chatIds)).enqueue(new Callback<RsrResponse<FriendsResult>>() {
            @Override
            public void onResponse(Call<RsrResponse<FriendsResult>> call, Response<RsrResponse<FriendsResult>> response) {
                listener.onOccupantsLoaded(response.body().getResult().getFriends());
            }

            @Override
            public void onFailure(Call<RsrResponse<FriendsResult>> call, Throwable t) {

            }
        });
    }
}
