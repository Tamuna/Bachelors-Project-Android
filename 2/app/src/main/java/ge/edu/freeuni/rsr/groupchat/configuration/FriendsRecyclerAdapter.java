package ge.edu.freeuni.rsr.groupchat.configuration;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import ge.edu.freeuni.rsr.R;
import ge.edu.freeuni.rsr.groupchat.configuration.entity.User;


/*
 * created by tgeldiashvili on 5/31/2019
 */

public class FriendsRecyclerAdapter extends RecyclerView.Adapter<FriendViewHolder> {

    private List<User> data = new ArrayList<>();

    @NonNull
    @Override
    public FriendViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cell_friend, parent, false);
        return new FriendViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FriendViewHolder holder, int position) {
        holder.setData(data.get(position));
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
}
