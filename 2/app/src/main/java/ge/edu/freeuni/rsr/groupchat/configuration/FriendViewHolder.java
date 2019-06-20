package ge.edu.freeuni.rsr.groupchat.configuration;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import butterknife.BindView;
import butterknife.ButterKnife;
import ge.edu.freeuni.rsr.R;
import ge.edu.freeuni.rsr.auth.entity.User;



/*
 * created by tgeldiashvili on 5/31/2019
 */

public class FriendViewHolder extends RecyclerView.ViewHolder {
    @BindView(R.id.txt_full_name)
    TextView txtFullName;

    public FriendViewHolder(@NonNull View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public void setData(User friend, FriendsRecyclerAdapter.OnItemClickListener onItemClickListener, boolean highlighted) {
        if (highlighted) {
            itemView.setBackgroundResource(R.drawable.bg_oval_blue);
        } else {
            itemView.setBackgroundResource(R.drawable.bg_oval);
        }
        txtFullName.setText(friend.getUserName());

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClickListener.onFriendSelected(friend, getAdapterPosition());
            }
        });

    }
}
