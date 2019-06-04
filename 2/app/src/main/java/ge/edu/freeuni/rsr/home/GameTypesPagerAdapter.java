package ge.edu.freeuni.rsr.home;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;
import ge.edu.freeuni.rsr.R;
import ge.edu.freeuni.rsr.groupchat.configuration.GroupChatConfigActivity;
import ge.edu.freeuni.rsr.individual.configuration.IndividualGameConfigActivity;

public class GameTypesPagerAdapter extends PagerAdapter {

    private List<GameTypeCardModel> models;
    private LayoutInflater inflater;
    private Context context;

    private ImageView img;

    public GameTypesPagerAdapter(List<GameTypeCardModel> models, Context context) {
        this.models = models;
        this.context = context;

    }

    @Override
    public int getCount() {
        return models.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view.equals(object);
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, final int position) {
        inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.card_game_type, container, false);

        img = view.findViewById(R.id.img_game_type_header);
        img.setImageResource(models.get(position).getImage());

        TextView header = view.findViewById(R.id.txt_game_type_header);
        header.setText(models.get(position).getHeader());

        TextView description = view.findViewById(R.id.txt_game_description);
        description.setText(models.get(position).getDescription());

        container.addView(view, 0);

        view.findViewById(R.id.btn_start_game).setOnClickListener(v -> {
            if (position == 1) {
                IndividualGameConfigActivity.start(view.getContext());
            }
            if(position == 2){
                GroupChatConfigActivity.start(view.getContext());
            }
        });
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }

}
