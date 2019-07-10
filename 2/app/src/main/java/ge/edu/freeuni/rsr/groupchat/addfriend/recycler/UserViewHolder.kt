package ge.edu.freeuni.rsr.groupchat.addfriend.recycler

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import butterknife.BindView
import butterknife.ButterKnife
import ge.edu.freeuni.rsr.R
import ge.edu.freeuni.rsr.common.entity.User

class UserViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    @BindView(R.id.txtUserName)
    lateinit var txtUserName: TextView
    @BindView(R.id.imgAddFriend)
    lateinit var imgAddFriend: ImageView

    init {
        ButterKnife.bind(this, itemView)
    }

    fun bindData(user: User, listener: SearchUsersRecyclerAdapter.OnItemActionListener) {
        txtUserName.text = user.userName
        imgAddFriend.setOnClickListener { listener.onFriendAddClicked(user.userName) }
    }
}