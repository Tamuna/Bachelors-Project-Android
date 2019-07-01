package ge.edu.freeuni.rsr.groupchat.configuration;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnTextChanged;
import ge.edu.freeuni.rsr.R;
import ge.edu.freeuni.rsr.common.entity.User;
import ge.edu.freeuni.rsr.groupchat.chat.GroupChatActivity;

public class GroupChatConfigActivity extends AppCompatActivity implements GroupChatConfigurationContract.GroupChatView {

    @BindView(R.id.rv_friends)
    RecyclerView rvFriends;

    @BindView(R.id.loader_view)
    LinearLayout loaderView;

    @BindView(R.id.et_search_users)
    EditText etSearchUsers;

    @OnTextChanged(R.id.et_search_users)
    void onTextChanged(CharSequence text) {
        adapter.filterUsers(text);
    }

    private FriendsRecyclerAdapter adapter;
    private GroupChatConfigurationContract.GroupChatPresenter presenter;
    private OnItemClickListenerImpl onItemClickListener;

    @OnClick(R.id.txt_start_chat)
    void onStartChatClick() {
        loaderView.setVisibility(View.VISIBLE);
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
        presenter = new GroupChatConfigPresenterImpl(this, new GroupChatConfigInteractorImpl());
        adapter = new FriendsRecyclerAdapter(onItemClickListener);
        presenter.getFriends();
    }

    @Override
    public void onDataLoaded(List<User> friends) {
        adapter.setData(friends);
        rvFriends.setAdapter(adapter);
        rvFriends.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    public void highlight(User user) {
        adapter.highlight(user);
    }

    @Override
    public void unhighlight(User user) {
        adapter.unhighlight(user);
    }

    @Override
    public void startChat(String dialogId) {
        loaderView.setVisibility(View.GONE);
        GroupChatActivity.start(this, dialogId);
    }

    @Override
    public void showToast(String s) {
        Toast.makeText(this, s, Toast.LENGTH_SHORT).show();
    }

    class OnItemClickListenerImpl implements FriendsRecyclerAdapter.OnItemClickListener {

        @Override
        public void onFriendSelected(User friend, int position) {
            presenter.selectFriend(friend, position);
        }
    }
}
