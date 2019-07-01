package ge.edu.freeuni.rsr.notifications;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import butterknife.BindView;
import butterknife.ButterKnife;
import ge.edu.freeuni.rsr.R;
import ge.edu.freeuni.rsr.groupchat.chat.GroupChatActivity;
import ge.edu.freeuni.rsr.notifications.entity.Dialog;

public class NotificationsActivity extends AppCompatActivity implements NotificationsContract.NotificationsView {

    @BindView(R.id.rv_chats)
    RecyclerView rvChats;

    @BindView(R.id.loader_view)
    LinearLayout loaderView;

    private NotificationRecyclerAdapter adapter;

    private NotificationsContract.NotificationsPresenter presenter;


    public static void start(Context previous) {
        Intent intent = new Intent(previous, NotificationsActivity.class);
        previous.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_notifications);
        ButterKnife.bind(this);

        adapter = new NotificationRecyclerAdapter(new OnItemClickListenerImpl());
        rvChats.setAdapter(adapter);
        rvChats.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));

        presenter = new NotificationPresenterImpl(this, new NotificationsInteractorImpl());
        presenter.getUserChats();
    }


    @Override
    public void onUserChatLoaded(Dialog dialog) {
        adapter.setSingleItem(dialog);
        loaderView.setVisibility(View.GONE);
    }

    class OnItemClickListenerImpl implements NotificationRecyclerAdapter.OnItemClickListener {

        @Override
        public void onDialogSelected(String dialogId) {
            GroupChatActivity.start(NotificationsActivity.this, dialogId);
        }
    }
}
