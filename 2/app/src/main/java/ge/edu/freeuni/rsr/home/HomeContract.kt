package ge.edu.freeuni.rsr.home

interface HomeContract {
    interface HomeView {
        fun onFriendAdded()
    }

    interface HomePresenter {
        fun addFriend(username: String)
    }

    interface HomeInteractor {
        interface OnFinishListener {
            fun onFriendAdded()
        }

        fun addFriend(username: String, onFinishListener: OnFinishListener)
    }
}