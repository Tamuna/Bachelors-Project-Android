package ge.edu.freeuni.rsr.auth;

import android.os.Bundle;

import com.connectycube.core.EntityCallback;
import com.connectycube.core.exception.ResponseException;
import com.connectycube.users.ConnectycubeUsers;
import com.connectycube.users.model.ConnectycubeUser;

import ge.edu.freeuni.rsr.auth.entity.RsrResponse;
import ge.edu.freeuni.rsr.auth.entity.Credentials;
import ge.edu.freeuni.rsr.auth.entity.Result;
import ge.edu.freeuni.rsr.auth.entity.User;
import ge.edu.freeuni.rsr.auth.entity.UserResult;
import ge.edu.freeuni.rsr.network.Api;
import ge.edu.freeuni.rsr.network.RequestInterceptor;
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
        api.login(new Credentials(username, password)).enqueue(new Callback<RsrResponse<Result>>() {
            @Override
            public void onResponse(Call<RsrResponse<Result>> call, Response<RsrResponse<Result>> response) {
                if (response.body().getError() != null) {
                    onFinishListener.onLoggedIn(false, response.body().getError());
                } else {
                    onFinishListener.onLoggedIn(true, response.body().getResult().getToken());
                }
            }

            @Override
            public void onFailure(Call<RsrResponse<Result>> call, Throwable t) {

            }
        });
    }

    @Override
    public void register(String username, String password, String email, OnFinishListener onFinishListener) {
        api.register(new Credentials(username, email, password, password)).enqueue(new Callback<RsrResponse<Result>>() {
            @Override
            public void onResponse(Call<RsrResponse<Result>> call, Response<RsrResponse<Result>> response) {
                if (response.body().getError() != null) {
                    onFinishListener.onRegistered(false, response.body().getError());
                } else {
                    RequestInterceptor.setToken(response.body().getResult().getToken());
                    registerInConnectyCube(onFinishListener, password);
                }
            }

            @Override
            public void onFailure(Call<RsrResponse<Result>> call, Throwable t) {

            }
        });
    }

    private void registerInConnectyCube(OnFinishListener onFinishListener, String password) {
        api.getUser().enqueue(new Callback<RsrResponse<UserResult>>() {
            @Override
            public void onResponse(Call<RsrResponse<UserResult>> call, Response<RsrResponse<UserResult>> response) {
                if (response.body().getError() == null) {
                    User user = response.body().getResult().getUser();

                    final ConnectycubeUser chatUser = new ConnectycubeUser("default_username" + user.getId(), user.getUserName());
                    chatUser.setPassword(password);

                    ConnectycubeUsers.signUp(chatUser).performAsync(new EntityCallback<ConnectycubeUser>() {
                        @Override
                        public void onSuccess(ConnectycubeUser userHere, Bundle args) {
                            setChatId(userHere.getId(), onFinishListener);
                        }

                        @Override
                        public void onError(ResponseException error) {
                        }
                    });
                }
            }

            @Override
            public void onFailure(Call<RsrResponse<UserResult>> call, Throwable t) {

            }
        });
    }

    private void setChatId(Integer id, OnFinishListener onFinishListener) {
        api.registerChatId(new Credentials(id)).enqueue(new Callback<Object>() {
            @Override
            public void onResponse(Call<Object> call, Response<Object> response) {
                onFinishListener.onRegistered(true, "");
            }

            @Override
            public void onFailure(Call<Object> call, Throwable t) {

            }
        });
    }
}
