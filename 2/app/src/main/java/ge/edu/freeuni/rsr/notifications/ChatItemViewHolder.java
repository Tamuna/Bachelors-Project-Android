package ge.edu.freeuni.rsr.notifications;

import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import butterknife.BindView;
import butterknife.ButterKnife;
import ge.edu.freeuni.rsr.R;
import ge.edu.freeuni.rsr.common.entity.User;
import ge.edu.freeuni.rsr.notifications.entity.Dialog;

public class ChatItemViewHolder extends RecyclerView.ViewHolder {
    @BindView(R.id.layout_chat_users)
    LinearLayout layout;

    public ChatItemViewHolder(@NonNull View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public void setData(Dialog dialog, NotificationRecyclerAdapter.OnItemClickListener listener) {
        for (User user : dialog.getUsers()) {
            TextView textView = new TextView(itemView.getContext());
            textView.setText(user.getUserName());
            textView.setTextColor(itemView.getContext().getResources().getColor(R.color.text_color));
            textView.setGravity(Gravity.CENTER);
            layout.addView(textView);
            itemView.setOnClickListener(v -> listener.onDialogSelected(dialog.getDialog_id()));
        }
    }
}
