package ge.edu.freeuni.rsr.groupchat.chat.recycler;

import android.graphics.drawable.GradientDrawable;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import butterknife.BindView;
import butterknife.ButterKnife;
import ge.edu.freeuni.rsr.AppUser;
import ge.edu.freeuni.rsr.R;
import ge.edu.freeuni.rsr.groupchat.chat.entity.Message;


/*
 * created by tgeldiashvili on 6/6/2019
 */

public class MessageViewHolder extends RecyclerView.ViewHolder {
    @BindView(R.id.tv_message)
    TextView tvMessage;

    @BindView(R.id.layout_msg_container)
    LinearLayout layoutContainer;

    @BindView(R.id.tv_sender_id)
    TextView tvSender;


    public MessageViewHolder(@NonNull View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public void setData(Message message) {
        tvSender.setText(message.getSender().getUserName());
        if (isSentMessage(message)) {
            layoutContainer.setGravity(Gravity.END);
            tvMessage.setBackgroundResource(R.drawable.bg_sent_message);
            tvMessage.setTextColor(itemView.getContext().getResources().getColor(R.color.light_gray));
            GradientDrawable drawable = (GradientDrawable) tvMessage.getBackground();
            drawable.setColor(itemView.getContext().getResources().getColor(R.color.text_color));
        } else {
            layoutContainer.setGravity(Gravity.START);
            tvMessage.setBackgroundResource(R.drawable.bg_received_message);
            tvMessage.setTextColor(itemView.getContext().getResources().getColor(R.color.dark_blue));
            GradientDrawable drawable = (GradientDrawable) tvMessage.getBackground();
            drawable.setColor(itemView.getContext().getResources().getColor(R.color.light_gray));
        }
        if (message.isQuestion()) {
            GradientDrawable drawable = (GradientDrawable) tvMessage.getBackground();
            drawable.setColor(itemView.getContext().getResources().getColor(R.color.light_red));
        } else if (message.isAnswer()) {
            GradientDrawable drawable = (GradientDrawable) tvMessage.getBackground();
            drawable.setColor(itemView.getContext().getResources().getColor(R.color.green));
        }
        tvMessage.setText(message.getMessage());
    }

    private boolean isSentMessage(Message message) {
        return message.getSender().getId().equals(AppUser.getInstance().getUser().getId());
    }
}
