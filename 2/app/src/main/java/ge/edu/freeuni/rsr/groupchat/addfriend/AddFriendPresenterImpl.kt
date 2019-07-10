package ge.edu.freeuni.rsr.groupchat.addfriend

import ge.edu.freeuni.rsr.common.entity.User

class AddFriendPresenterImpl(
        private var view: AddFriendContract.AddFriendView,
        private var interactor: AddFriendContract.AddFriendInteractor
) : AddFriendContract.AddFriendPresenter {
    override fun sendFriendRequest(message: String, friendUsername: String) {
        interactor.sendFriendRequest(message, friendUsername, OnFinishListenerImpl())
    }

    override fun loadUsers(s: String) {
        interactor.loadUsers(s, OnFinishListenerImpl())
    }

    inner class OnFinishListenerImpl : AddFriendContract.AddFriendInteractor.OnFinishListener {
        override fun onFriendRequestSent(result: String) {
            view.onFriendRequestSent(result)
        }

        override fun OnDataLoaded(result: List<User>) {
            view.onUsersLoaded(result)
        }

    }
}