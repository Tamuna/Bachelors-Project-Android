package ge.edu.freeuni.rsr.notifications;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import ge.edu.freeuni.rsr.R;
import ge.edu.freeuni.rsr.notifications.entity.Dialog;

public class NotificationRecyclerAdapter extends RecyclerView.Adapter<ChatItemViewHolder> {
    private List<Dialog> dialogs = new ArrayList<>();

    public void bindData(List<Dialog> dialogs) {
        this.dialogs.clear();
        this.dialogs.addAll(dialogs);
        notifyDataSetChanged();
    }

    public interface OnItemClickListener {
        void onDialogSelected(String dialogId);
    }

    private OnItemClickListener listener;

    public NotificationRecyclerAdapter(OnItemClickListener listener) {
        this.listener = listener;
    }

    @NonNull
    @Override

    public ChatItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cell_chat, parent, false);
        return new ChatItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ChatItemViewHolder holder, int position) {
        holder.setData(dialogs.get(position), listener);
    }

    @Override
    public int getItemCount() {
        return dialogs.size();
    }
}
