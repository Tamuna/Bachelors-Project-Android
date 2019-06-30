package ge.edu.freeuni.rsr.Notifications;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import butterknife.BindView;
import butterknife.ButterKnife;
import ge.edu.freeuni.rsr.Notifications.entity.Dialog;
import ge.edu.freeuni.rsr.R;

public class NotificationsActivity extends AppCompatActivity implements NotificationsContract.NotificationsView {

    @BindView(R.id.rv_chats)
    RecyclerView rvChats;

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

        adapter = new NotificationRecyclerAdapter();
        rvChats.setAdapter(adapter);
        rvChats.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));

        presenter = new NotificationPresenterImpl(this, new NotificationsInteractorImpl());
        presenter.getUserChats();
    }


    @Override
    public void onUserChatLoaded(Dialog dialog) {
        adapter.setSingleItem(dialog);
    }
}
