package ge.edu.freeuni.rsr.auth;

public interface AuthContract {
    interface AuthView {
        void redirectToHome();

        void showError(String error);

        void showLoader(boolean isLoading);
    }

    interface AuthPresenter {
        void login(String username, String password);

        void register(String username, String password, String email, String confirmPassword);
    }

    interface AuthInteractor {
        interface OnFinishListener {
            void onLoggedIn(boolean success, String error);

            void onRegistered(boolean success, String error);
        }

        void login(String username, String password, OnFinishListener onFinishListener);

        void register(String username, String password, String email, OnFinishListener onFinishListener);
    }
}
