package ge.edu.freeuni.rsr.groupchat.chat;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import ge.edu.freeuni.rsr.R;
import ge.edu.freeuni.rsr.groupchat.chat.entity.Message;


/*
 * created by tgeldiashvili on 6/6/2019
 */

public class MessagesRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<Message> data = new ArrayList<Message>();

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cell_message, parent, false);
        return new SentMessageViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ((SentMessageViewHolder) holder).setData(data.get(position));
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public void setData(List<Message> data) {
        this.data.clear();
        this.data.addAll(data);
        notifyDataSetChanged();
    }

    public void setSingleData(Message message, boolean isQuestion, boolean isAnswer) {
        this.data.add(message);
        notifyItemChanged(data.size() - 1);
    }
}
