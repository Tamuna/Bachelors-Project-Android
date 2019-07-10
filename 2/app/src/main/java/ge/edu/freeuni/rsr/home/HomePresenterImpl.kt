package ge.edu.freeuni.rsr.home

class HomePresenterImpl(
        private val view: HomeContract.HomeView,
        private val interactor: HomeContract.HomeInteractor
) : HomeContract.HomePresenter {
    override fun addFriend(username: String) {
        interactor.addFriend(username, OnFinishListenerImpl())
    }

    inner class OnFinishListenerImpl : HomeContract.HomeInteractor.OnFinishListener {
        override fun onFriendAdded() {
            view.onFriendAdded()
        }
    }
}