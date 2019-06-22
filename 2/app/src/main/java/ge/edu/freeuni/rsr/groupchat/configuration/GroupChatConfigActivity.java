package ge.edu.freeuni.rsr.groupchat.configuration;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import ge.edu.freeuni.rsr.R;
import ge.edu.freeuni.rsr.common.entity.User;
import ge.edu.freeuni.rsr.groupchat.chat.GroupChatActivity;

public class GroupChatConfigActivity extends AppCompatActivity implements GroupChatConfigurationContract.GroupChatView {

    @BindView(R.id.rv_friends)
    RecyclerView rvFriends;

    private FriendsRecyclerAdapter adapter;
    private GroupChatConfigurationContract.GroupChatPresenter presenter;
    private OnItemClickListenerImpl onItemClickListener;

    @OnClick(R.id.txt_start_chat)
    void onStartChatClick() {
        presenter.sendNotifications();
    }

    public static void start(Context previous) {
        Intent intent = new Intent(previous, GroupChatConfigActivity.class);
        previous.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group_practice_config);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        ButterKnife.bind(this);

        rvFriends.setNestedScrollingEnabled(false);
        onItemClickListener = new OnItemClickListenerImpl();
        presenter = new GroupChatPresenterImpl(this, new GroupChatInteractorImpl());
        adapter = new FriendsRecyclerAdapter(onItemClickListener);
        presenter.getFriends();
    }

    @Override
    public void onDataLoaded(List<User> friends) {
        adapter.setData(friends);
        rvFriends.setAdapter(adapter);
        rvFriends.setLayoutManager(new GridLayoutManager(this, 2));
    }

    @Override
    public void highlight(int position) {
        adapter.highlight(position);
    }

    @Override
    public void unhighlight(int position) {
        adapter.unhighlight(position);
    }

    @Override
    public void startChat(String s) {
        Toast.makeText(this, s, Toast.LENGTH_SHORT).show();
        GroupChatActivity.start(this, 1);
    }

    class OnItemClickListenerImpl implements FriendsRecyclerAdapter.OnItemClickListener {

        @Override
        public void onFriendSelected(User friend, int position) {
            presenter.selectFriend(friend, position);
        }
    }
}
