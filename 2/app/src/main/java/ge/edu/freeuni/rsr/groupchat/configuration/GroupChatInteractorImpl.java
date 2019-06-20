package ge.edu.freeuni.rsr.groupchat.configuration;

/*
 * created by tgeldiashvili on 5/31/2019
 */


import ge.edu.freeuni.rsr.auth.entity.RsrResponse;
import ge.edu.freeuni.rsr.groupchat.FriendsResult;
import ge.edu.freeuni.rsr.network.Api;
import ge.edu.freeuni.rsr.network.RetrofitInstance;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class GroupChatInteractorImpl implements GroupChatConfigurationContract.GroupChatInteractor {


    private Api api;
    private Retrofit retrofit;

    public GroupChatInteractorImpl() {
        retrofit = RetrofitInstance.getRetrofitInstance();
        api = retrofit.create(Api.class);
    }

    @Override
    public void getFriends(OnFinishListener onFinishListener, int userId) {
        api.getFriends().enqueue(new Callback<RsrResponse<FriendsResult>>() {
            @Override
            public void onResponse(Call<RsrResponse<FriendsResult>> call, Response<RsrResponse<FriendsResult>> response) {
                if (response.body().getError() == null) {
                    onFinishListener.onFriendsLoaded(response.body().getResult().getFriends());
                }
            }

            @Override
            public void onFailure(Call<RsrResponse<FriendsResult>> call, Throwable t) {

            }
        });

    }
}
