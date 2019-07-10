package ge.edu.freeuni.rsr.groupchat.addfriend

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.WindowManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import butterknife.BindView
import butterknife.ButterKnife
import butterknife.OnTextChanged
import ge.edu.freeuni.rsr.AppUser
import ge.edu.freeuni.rsr.R
import ge.edu.freeuni.rsr.common.entity.User
import ge.edu.freeuni.rsr.groupchat.addfriend.recycler.SearchUsersRecyclerAdapter

class AddFriendActivity : AppCompatActivity(), AddFriendContract.AddFriendView {

    private var presenter: AddFriendContract.AddFriendPresenter? = null
    private var adapter: SearchUsersRecyclerAdapter? = null

    @BindView(R.id.rvUsers)
    lateinit var rvUsers: RecyclerView

    companion object {
        @JvmStatic
        fun start(previous: Activity) {
            val intent = Intent(previous, AddFriendActivity::class.java)
            previous.startActivity(intent)
        }
    }

    @OnTextChanged(R.id.et_search_users)
    fun onTextChanged(text: CharSequence) {
        presenter?.loadUsers(text.toString())
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_friend)
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
        ButterKnife.bind(this)

        presenter = AddFriendPresenterImpl(this, AddFriendInteractorImpl())
        adapter = SearchUsersRecyclerAdapter(OnItemActionListenerImpl())
        rvUsers.layoutManager = LinearLayoutManager(this)
        rvUsers.adapter = adapter
        presenter?.loadUsers("")
    }

    override fun onUsersLoaded(result: List<User>) {
        adapter?.bindData(result as MutableList<User>)
    }

    override fun onFriendRequestSent(result: String) {
        Toast.makeText(this@AddFriendActivity, result, Toast.LENGTH_SHORT).show()
    }

    inner class OnItemActionListenerImpl : SearchUsersRecyclerAdapter.OnItemActionListener {
        override fun onFriendAddClicked(username: String) {
            presenter?.sendFriendRequest(AppUser.getInstance().user.userName + " სურს თქვენთან დამეგობრება", username)
        }

    }
}
