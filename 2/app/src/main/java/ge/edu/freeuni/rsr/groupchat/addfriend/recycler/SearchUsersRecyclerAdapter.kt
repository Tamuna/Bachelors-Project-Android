package ge.edu.freeuni.rsr.groupchat.addfriend.recycler

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ge.edu.freeuni.rsr.R
import ge.edu.freeuni.rsr.common.entity.User

class SearchUsersRecyclerAdapter(
        private val listener: OnItemActionListener
) : RecyclerView.Adapter<UserViewHolder>() {
    private var data: MutableList<User> = ArrayList()

    interface OnItemActionListener {
        fun onFriendAddClicked(username: String)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.cell_user, parent, false)
        return UserViewHolder(view)
    }

    override fun getItemCount(): Int = data.size


    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.bindData(data[position], listener)
    }

    fun bindData(data: MutableList<User>) {
        this.data.clear()
        this.data.addAll(data)
        notifyDataSetChanged()
    }

}