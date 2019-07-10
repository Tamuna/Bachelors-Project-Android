package ge.edu.freeuni.rsr.groupchat.addfriend

import ge.edu.freeuni.rsr.common.entity.User

interface AddFriendContract {
    interface AddFriendView {
        fun onUsersLoaded(result: List<User>)
        fun onFriendRequestSent(result: String)
    }

    interface AddFriendPresenter {
        fun loadUsers(s: String)
        fun sendFriendRequest(message: String, friendUsername: String)
    }

    interface AddFriendInteractor {
        interface OnFinishListener {
            fun OnDataLoaded(result: List<User>)
            fun onFriendRequestSent(result: String)
        }

        fun loadUsers(s: String, onFinishListener: OnFinishListener)
        fun sendFriendRequest(message: String, friendUsername: String, onFinishListener: OnFinishListener)
    }
}