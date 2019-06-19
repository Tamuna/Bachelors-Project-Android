package ge.edu.freeuni.rsr.auth;

import ge.edu.freeuni.rsr.auth.entity.AuthResponse;
import ge.edu.freeuni.rsr.auth.entity.Credentials;
import ge.edu.freeuni.rsr.network.Api;
import ge.edu.freeuni.rsr.network.RetrofitInstance;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class AuthInteractorImpl implements AuthContract.AuthInteractor {
    private Api api;
    private Retrofit retrofit;

    public AuthInteractorImpl() {
        retrofit = RetrofitInstance.getRetrofitInstance();
        api = retrofit.create(Api.class);
    }

    @Override
    public void login(String username, String password, OnFinishListener onFinishListener) {
        //TODO api call
    }

    @Override
    public void register(String username, String password, String email, OnFinishListener onFinishListener) {
        api.register(new Credentials(username, email,password, password, 2)).enqueue(new Callback<AuthResponse>() {
            @Override
            public void onResponse(Call<AuthResponse> call, Response<AuthResponse> response) {
                if(response.body().getError() != null){
                    onFinishListener.onRegistered(false, "გადაამოწმეთ შეყვანილი მონაცემები");
                }else {
                    //set token to interceptor
                    onFinishListener.onRegistered(true, "");
                }
            }

            @Override
            public void onFailure(Call<AuthResponse> call, Throwable t) {

            }
        });
    }
}
