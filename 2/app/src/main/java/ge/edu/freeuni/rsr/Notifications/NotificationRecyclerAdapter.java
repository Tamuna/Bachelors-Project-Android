package ge.edu.freeuni.rsr.Notifications;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import ge.edu.freeuni.rsr.Notifications.entity.Dialog;
import ge.edu.freeuni.rsr.R;

public class NotificationRecyclerAdapter extends RecyclerView.Adapter<ChatItemViewHolder> {
    List<Dialog> dialogList = new ArrayList<>();

    @NonNull
    @Override
    public ChatItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cell_chat, parent, false);
        return new ChatItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ChatItemViewHolder holder, int position) {
        holder.setData(dialogList.get(position));
    }

    @Override
    public int getItemCount() {
        return dialogList.size();
    }

    public void setSingleItem(Dialog dialog) {
        dialogList.add(dialog);
        notifyItemChanged(dialogList.size() - 1);
    }
}
