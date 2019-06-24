package ge.edu.freeuni.rsr.groupchat.chat;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import butterknife.BindView;
import butterknife.ButterKnife;
import ge.edu.freeuni.rsr.AppUser;
import ge.edu.freeuni.rsr.R;
import ge.edu.freeuni.rsr.groupchat.chat.entity.Message;

import static android.graphics.Color.rgb;


/*
 * created by tgeldiashvili on 6/6/2019
 */

public class SentMessageViewHolder extends RecyclerView.ViewHolder {
    @BindView(R.id.tv_message)
    TextView tvMessage;


    public SentMessageViewHolder(@NonNull View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public void setData(Message message) {
        if (message.getSender().getId().equals(AppUser.getInstance().getUser().getId())) {
            tvMessage.setBackgroundResource(R.drawable.bg_sent_message);
            tvMessage.setTextColor(	rgb(255,255,255));
        } else {
            tvMessage.setBackgroundResource(R.drawable.bg_received_message);
            tvMessage.setTextColor(	rgb(0,0,0));
        }
        tvMessage.setText(message.getMessage());

    }
}
