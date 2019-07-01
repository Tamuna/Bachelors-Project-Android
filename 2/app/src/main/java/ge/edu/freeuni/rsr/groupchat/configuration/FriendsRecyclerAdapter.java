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
    private List<User> filtered = new ArrayList<>();
    private OnItemClickListener onItemClickListener;
    private List<User> highlights = new ArrayList<>();

    public FriendsRecyclerAdapter(GroupChatConfigActivity.OnItemClickListenerImpl onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public void filterUsers(CharSequence text) {
        filtered.clear();
        for (int i = 0; i < data.size(); i++) {
            if (data.get(i).getUserName().contains(text))
                filtered.add(data.get(i));
        }
        notifyDataSetChanged();
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
        holder.setData(filtered.get(position), onItemClickListener, highlights.contains(filtered.get(position)));
    }

    @Override
    public int getItemCount() {
        return filtered.size();
    }

    public void setData(List<User> data) {
        this.data.clear();
        this.data.addAll(data);
        filtered.clear();
        filtered.addAll(data);
        notifyDataSetChanged();
    }

    public void highlight(User user) {
        highlights.add(user);
        notifyItemChanged(filtered.indexOf(user));
    }

    public void unhighlight(User user) {
        highlights.remove(user);
        notifyItemChanged(filtered.indexOf(user));
    }
}