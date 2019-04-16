package ge.edu.freeuni.rsr.individual;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

import androidx.constraintlayout.widget.ConstraintLayout;
import butterknife.ButterKnife;
import ge.edu.freeuni.rsr.R;

public class QuestionNumberSelectionCard extends ConstraintLayout {

    public QuestionNumberSelectionCard(Context context) {
        super(context);
        View.inflate(context, R.layout.card_question_number_selector, this);
        ButterKnife.bind(this);
    }

    public QuestionNumberSelectionCard(Context context, AttributeSet attrs) {
        super(context, attrs);
        View.inflate(context, R.layout.card_question_number_selector, this);
        ButterKnife.bind(this);
    }

    public QuestionNumberSelectionCard(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        View.inflate(context, R.layout.card_question_number_selector, this);
        ButterKnife.bind(this);
    }


}
