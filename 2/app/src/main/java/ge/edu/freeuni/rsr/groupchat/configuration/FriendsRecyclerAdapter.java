package ge.edu.freeuni.rsr.groupchat.configuration;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import ge.edu.freeuni.rsr.R;
import ge.edu.freeuni.rsr.common.entity.User;


/*
 * created by tgeldiashvili on 5/31/2019
 */

public class FriendsRecyclerAdapter extends RecyclerView.Adapter<FriendViewHolder> {

    private List<User> data = new ArrayList<>();
    private OnItemClickListener onItemClickListener;
    private List<Integer> highlights = new ArrayList<>();

    public FriendsRecyclerAdapter(GroupChatConfigActivity.OnItemClickListenerImpl onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public interface OnItemClickListener {
        void onFriendSelected(User friend, int position);
    }

    @NonNull
    @Override
    public FriendViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cell_friend, parent, false);
        return new FriendViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FriendViewHolder holder, int position) {
        holder.setData(data.get(position), onItemClickListener, highlights.contains(position));
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public void setData(List<User> data) {
        this.data.clear();
        this.data.addAll(data);
        notifyDataSetChanged();
    }

    public void highlight(int position) {
        highlights.add(position);
        notifyItemChanged(position);
    }

    public void unhighlight(int position) {
        highlights.remove((Integer) position);
        notifyItemChanged(position);
    }
}
