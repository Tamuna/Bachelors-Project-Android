package ge.edu.freeuni.rsr.individual.configuration;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;
import ge.edu.freeuni.rsr.R;

public class QuestionCountCard extends ConstraintLayout {

    private Integer questionCount = null;
    private String description = null;
    private IndividualGameItemListener individualGameItemListener;

    public QuestionCountCard(Context context) {
        super(context);
        init(null, 0);
    }

    public QuestionCountCard(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs, 0);
    }

    public QuestionCountCard(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs, defStyleAttr);
    }

    private void init(AttributeSet attrs, int defStyleAttr) {
        inflate(getContext(), R.layout.item_individual_game_count, this);
        TypedArray array = getContext().obtainStyledAttributes(attrs, R.styleable.QuestionCountCard, 0, 0);


        if (array != null) {
            questionCount = array.getInteger(R.styleable.QuestionCountCard_question_count, 0);
            description = array.getString(R.styleable.QuestionCountCard_description);

            TextView tvGameCount = findViewById(R.id.txt_question_count);
            TextView tvDescription = findViewById(R.id.txt_description);

            tvGameCount.setText(String.format("%d", questionCount));
            tvDescription.setText(description);
        }


        setOnClickListener(v -> {
            if(individualGameItemListener!=null){
                individualGameItemListener.onIndividualGameItemClicked(questionCount);
            }
        });

    }

    public void setIndividualGameItemListener(IndividualGameItemListener listener){
        this.individualGameItemListener = listener;
    }

    public interface IndividualGameItemListener{
        void onIndividualGameItemClicked(int questionCount);
    }
}
