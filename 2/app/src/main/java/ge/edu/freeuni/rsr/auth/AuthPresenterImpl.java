package ge.edu.freeuni.rsr.auth;

import ge.edu.freeuni.rsr.common.utils.ErrorUtils;
import ge.edu.freeuni.rsr.network.RequestInterceptor;

public class AuthPresenterImpl implements AuthContract.AuthPresenter {

    private AuthContract.AuthInteractor interactor;
    private AuthContract.AuthView view;

    public AuthPresenterImpl(AuthContract.AuthInteractor interactor, AuthContract.AuthView view) {
        this.interactor = interactor;
        this.view = view;
    }

    @Override
    public void login(String username, String password) {
        if (username.isEmpty() || password.isEmpty()) {
            view.showError("შეავსეთ ცარიელი ველები!");
        } else {
            view.showLoader(true);
            interactor.login(username, password, new OnFinishListenerImpl());
        }
    }

    @Override
    public void register(String username, String password, String email, String confirmPassword) {
        if (password.isEmpty() || username.isEmpty() || email.isEmpty() || confirmPassword.isEmpty()) {
            view.showError("შეავსეთ ცარიელი ველები!");
        } else if (!password.equals(confirmPassword)) {
            view.showError("პაროლის ველებში შეყვანილი ტექსტები ერთმანეთს არ ემთხვევა!");
        } else {
            view.showLoader(true);
            interactor.register(username, password, email, new OnFinishListenerImpl());
        }
    }

    private class OnFinishListenerImpl implements AuthContract.AuthInteractor.OnFinishListener {

        @Override
        public void onLoggedIn(boolean success, String info) {
            view.showLoader(false);
            if (success) {
                RequestInterceptor.setToken(info);
                view.redirectToHome();
            } else {
                view.showError(new ErrorUtils().processError(info));
            }
        }

        @Override
        public void onRegistered(boolean success, String info) {
            view.showLoader(false);
            if (success) {
                view.redirectToHome();
            } else {
                view.showError(new ErrorUtils().processError(info));
            }
        }
    }


}
