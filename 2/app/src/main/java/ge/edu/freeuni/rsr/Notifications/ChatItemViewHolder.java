package ge.edu.freeuni.rsr.Notifications;

import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import butterknife.BindView;
import butterknife.ButterKnife;
import ge.edu.freeuni.rsr.Notifications.entity.Dialog;
import ge.edu.freeuni.rsr.R;
import ge.edu.freeuni.rsr.common.entity.User;

public class ChatItemViewHolder extends RecyclerView.ViewHolder {
    @BindView(R.id.layout_chat_users)
    LinearLayout layout;

    public ChatItemViewHolder(@NonNull View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public void setData(Dialog dialog) {
        for (User user : dialog.getOccupants()) {
            TextView textView = new TextView(itemView.getContext());
            textView.setText(user.getUserName());
            textView.setTextColor(itemView.getContext().getResources().getColor(R.color.text_color));
            textView.setGravity(Gravity.CENTER);
            layout.addView(textView);
        }
    }
}
