package ge.edu.freeuni.rsr.common.component;

import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;

import androidx.appcompat.widget.Toolbar;

import butterknife.ButterKnife;
import butterknife.OnClick;
import ge.edu.freeuni.rsr.Notifications.NotificationsActivity;
import ge.edu.freeuni.rsr.R;
import ge.edu.freeuni.rsr.home.HomeActivity;
import ge.edu.freeuni.rsr.profile.ProfileActivity;

public class CustomToolbar extends Toolbar {


    public CustomToolbar(Context context) {
        super(context);
        init(null);
    }

    public CustomToolbar(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    private void init(AttributeSet attrs) {
        inflate(getContext(), R.layout.toolbar_custom, this);
        ButterKnife.bind(this);
        setContentInsetsAbsolute(0, 0);
    }

    @OnClick(R.id.img_toolbar_profile)
    public void onProfileClick() {
        ProfileActivity.start(getContext());
    }

    @OnClick(R.id.img_toolbar_notifications)
    public void onNotificationsClick() {
        NotificationsActivity.start(getContext());
    }

    @OnClick(R.id.txt_name)
    public void onAppNameClick() {
        HomeActivity.start((Activity) getContext());
    }
}
