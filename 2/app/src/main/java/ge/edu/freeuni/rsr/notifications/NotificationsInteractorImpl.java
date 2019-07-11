package ge.edu.freeuni.rsr.notifications;

import java.util.List;

import ge.edu.freeuni.rsr.common.entity.RsrResponse;
import ge.edu.freeuni.rsr.network.Api;
import ge.edu.freeuni.rsr.network.RetrofitInstance;
import ge.edu.freeuni.rsr.notifications.entity.Dialog;
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
    public void getChatOccupants(OnFinishListener listener) {
        api.getDialogs().enqueue(new Callback<RsrResponse<List<Dialog>>>() {
            @Override
            public void onResponse(Call<RsrResponse<List<Dialog>>> call, Response<RsrResponse<List<Dialog>>> response) {
                if (response.body() != null && response.body().getResult() != null)
                    listener.onDialogsLoaded(response.body().getResult());
            }

            @Override
            public void onFailure(Call<RsrResponse<List<Dialog>>> call, Throwable t) {

            }
        });
    }
}
