package ge.edu.freeuni.rsr.auth;

import android.os.Bundle;

import com.connectycube.chat.ConnectycubeChatService;
import com.connectycube.core.EntityCallback;
import com.connectycube.core.exception.ResponseException;
import com.connectycube.users.ConnectycubeUsers;
import com.connectycube.users.model.ConnectycubeUser;

import org.jivesoftware.smack.SmackException;

import ge.edu.freeuni.rsr.App;
import ge.edu.freeuni.rsr.AppUser;
import ge.edu.freeuni.rsr.auth.entity.AuthResult;
import ge.edu.freeuni.rsr.auth.entity.Credentials;
import ge.edu.freeuni.rsr.auth.entity.UserResult;
import ge.edu.freeuni.rsr.common.entity.RsrResponse;
import ge.edu.freeuni.rsr.common.entity.User;
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
        api.login(new Credentials(username, password)).enqueue(new Callback<RsrResponse<AuthResult>>() {
            @Override
            public void onResponse(Call<RsrResponse<AuthResult>> call, Response<RsrResponse<AuthResult>> response) {
                if (response.body().getError() != null) {
                    onFinishListener.onLoggedIn(false, response.body().getError());
                } else {
                    RequestInterceptor.setToken(response.body().getResult().getToken());
                    saveUserLocally(onFinishListener);
                }
            }

            @Override
            public void onFailure(Call<RsrResponse<AuthResult>> call, Throwable t) {

            }
        });
    }

    private void loginToChat() {
        if (ConnectycubeChatService.getInstance().isLoggedIn()) {
            try {
                ConnectycubeChatService.getInstance().logout();
            } catch (SmackException.NotConnectedException e) {
                e.printStackTrace();
            }
        }
        ConnectycubeUser user = new ConnectycubeUser("username" + AppUser.getInstance().getUser().getId(), "username" + AppUser.getInstance().getUser().getId());
        user.setId(AppUser.getInstance().getUser().getChatUserId());
        ConnectycubeChatService.getInstance().login(user, new EntityCallback() {
            @Override
            public void onSuccess(Object o, Bundle bundle) {
                ConnectycubeUsers.signIn(user).performAsync(new EntityCallback<ConnectycubeUser>() {
                    @Override
                    public void onSuccess(ConnectycubeUser user, Bundle args) {
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

    void saveUserLocally(OnFinishListener onFinishListener) {
        api.getUser().enqueue(new Callback<RsrResponse<UserResult>>() {
            @Override
            public void onResponse(Call<RsrResponse<UserResult>> call, Response<RsrResponse<UserResult>> response) {
                if (response.body().getError() == null) {
                    AppUser.getInstance().setUser(response.body().getResult().getUser());
                    loginToChat();
                    onFinishListener.onLoggedIn(true, "");
                }
            }

            @Override
            public void onFailure(Call<RsrResponse<UserResult>> call, Throwable t) {

            }
        });
    }

    @Override
    public void register(String username, String password, String email, OnFinishListener onFinishListener) {
        api.register(new Credentials(username, email, password, password)).enqueue(new Callback<RsrResponse<AuthResult>>() {
            @Override
            public void onResponse(Call<RsrResponse<AuthResult>> call, Response<RsrResponse<AuthResult>> response) {
                if (response.body().getError() != null) {
                    onFinishListener.onRegistered(false, response.body().getError());
                } else {
                    RequestInterceptor.setToken(response.body().getResult().getToken());
                    registerInConnectyCube(onFinishListener);
                }
            }

            @Override
            public void onFailure(Call<RsrResponse<AuthResult>> call, Throwable t) {

            }
        });
    }

    private void registerInConnectyCube(OnFinishListener onFinishListener) {
        api.getUser().enqueue(new Callback<RsrResponse<UserResult>>() {
            @Override
            public void onResponse(Call<RsrResponse<UserResult>> call, Response<RsrResponse<UserResult>> response) {
                if (response.body().getError() == null) {
                    User user = response.body().getResult().getUser();

                    final ConnectycubeUser chatUser = new ConnectycubeUser("username" + user.getId(), "username" + user.getId());

                    ConnectycubeUsers.signUp(chatUser).performAsync(new EntityCallback<ConnectycubeUser>() {
                        @Override
                        public void onSuccess(ConnectycubeUser userHere, Bundle args) {
                            setAdditionalInfo(userHere.getId(), onFinishListener);
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

    private void setAdditionalInfo(Integer id, OnFinishListener onFinishListener) {
        api.registerAdditionalInfo(new Credentials(id, App.getInstance().getDeviceToken())).enqueue(new Callback<RsrResponse<UserResult>>() {
            @Override
            public void onResponse(Call<RsrResponse<UserResult>> call, Response<RsrResponse<UserResult>> response) {
                AppUser.getInstance().setUser(response.body().getResult().getUser());
                ConnectycubeUsers.signOut();
                onFinishListener.onRegistered(true, "");
            }

            @Override
            public void onFailure(Call<RsrResponse<UserResult>> call, Throwable t) {

            }
        });
    }
}
