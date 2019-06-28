package ge.edu.freeuni.rsr.groupchat.chat;

import android.graphics.drawable.GradientDrawable;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

import butterknife.BindView;
import butterknife.ButterKnife;
import ge.edu.freeuni.rsr.AppUser;
import ge.edu.freeuni.rsr.R;
import ge.edu.freeuni.rsr.groupchat.chat.entity.Message;

import static android.graphics.Color.rgb;


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
        if (message.getSender().getId().equals(AppUser.getInstance().getUser().getId())) {
            layoutContainer.setGravity(Gravity.END);
            tvMessage.setBackgroundResource(R.drawable.bg_sent_message);
            tvMessage.setTextColor(itemView.getContext().getResources().getColor(R.color.light_gray));
        } else {
            layoutContainer.setGravity(Gravity.START);
            tvMessage.setBackgroundResource(R.drawable.bg_received_message);
            tvMessage.setTextColor(itemView.getContext().getResources().getColor(R.color.dark_blue));
        }
        if (message.isQuestion()) {
            GradientDrawable drawable = (GradientDrawable) tvMessage.getBackground();
            drawable.setColor(itemView.getContext().getResources().getColor(R.color.light_red));
        }
        tvMessage.setText(message.getMessage());
    }
}
