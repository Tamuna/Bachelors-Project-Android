package ge.edu.freeuni.rsr.groupchat.chat;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import butterknife.BindView;
import butterknife.ButterKnife;
import ge.edu.freeuni.rsr.R;
import ge.edu.freeuni.rsr.groupchat.chat.entity.Message;


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
        //if sender is currently logged in send
        tvMessage.setText(message.getMessage());
    }
}
