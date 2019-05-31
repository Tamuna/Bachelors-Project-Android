package ge.edu.freeuni.rsr.groupchat.configuration;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import ge.edu.freeuni.rsr.R;
import ge.edu.freeuni.rsr.groupchat.configuration.entity.User;

public class GroupPracticeConfigActivity extends AppCompatActivity implements GroupChatContract.GroupChatView {

    @BindView(R.id.rv_friends)
    RecyclerView rvFriends;

    private FriendsRecyclerAdapter adapter;
    private GroupChatContract.GroupChatPresenter presenter;

    @OnClick(R.id.txt_start_chat)
    void startChat() {

    }

    public static void start(Context previous) {
        Intent intent = new Intent(previous, GroupPracticeConfigActivity.class);
        previous.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group_practice_config);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        ButterKnife.bind(this);

        presenter = new GroupChatPresenterImpl(this, new GroupChatInteractorImpl());
        adapter = new FriendsRecyclerAdapter();
        presenter.getFriends();

    }

    @Override
    public void onDataLoaded(List<User> friends) {
        adapter.setData(friends);
        rvFriends.setAdapter(adapter);
        rvFriends.setLayoutManager(new GridLayoutManager(this, 2));
    }
}
