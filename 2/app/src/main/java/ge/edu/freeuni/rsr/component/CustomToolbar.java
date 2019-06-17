package ge.edu.freeuni.rsr.component;

import android.content.Context;
import android.util.AttributeSet;

import androidx.appcompat.widget.Toolbar;
import butterknife.ButterKnife;
import butterknife.OnClick;
import ge.edu.freeuni.rsr.R;
import ge.edu.freeuni.rsr.auth.AuthActivity;

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
    public void onProfileClick(){
        AuthActivity.start(getContext());
    }
}
