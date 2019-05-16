package ge.edu.freeuni.rsr.individual.game;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.method.ScrollingMovementMethod;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import ge.edu.freeuni.rsr.R;
import ge.edu.freeuni.rsr.individual.game.entity.Answer;
import ge.edu.freeuni.rsr.individual.game.entity.Question;

public class IndividualGameActivity extends AppCompatActivity implements IndividualGameContract.IndividualGameView {

    private long timeOnSingleQuestion = 120000;
    private CountDownTimer timer;
    public static final String TOTAL_QUESTIONS = "totalQuestions";

    private IndividualGamePresenterImpl presenter;

    @BindView(R.id.tvTime)
    TextView tvTime;

    @BindView(R.id.tvQuestionContent)
    TextView tvQuestionContent;

    @BindView(R.id.tvNumberOutOf)
    TextView tvNumberOutOf;

    @BindView(R.id.etAnswerInput)
    EditText etAnswerInput;


    public static void start(Context previous, int totalQuestions) {
        Intent intent = new Intent(previous, IndividualGameActivity.class);
        intent.putExtra(TOTAL_QUESTIONS, totalQuestions);
        previous.startActivity(intent);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_individual_game);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        ButterKnife.bind(this);

        tvQuestionContent.setMovementMethod(new ScrollingMovementMethod());
        createTimer();

        presenter = new IndividualGamePresenterImpl(new IndividualGameInteractorImpl(), this, getIntent().getIntExtra(TOTAL_QUESTIONS, 0));

    }

    private void createTimer() {
        timer = new CountDownTimer(timeOnSingleQuestion, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timeOnSingleQuestion = millisUntilFinished;
                tvTime.setText(String.format("%d", timeOnSingleQuestion / 1000));
            }

            @Override
            public void onFinish() {
                presenter.checkAnswer(1, String.valueOf(etAnswerInput.getText()));
            }
        };
    }

    @Override
    public void loadNextQuestion(Question question, String numberOutOf) {
        timer.start();
        tvQuestionContent.setText(question.getQuestionContent());
        tvNumberOutOf.setText(numberOutOf);
    }

    @Override
    public void renderCorrectAnswerScreen() {
        presenter.loadNextQuestion();
    }

    @Override
    public void renderWrongAnswerScreen(List<Answer> correctAnswers) {
        presenter.loadNextQuestion();
    }

    @Override
    public void loadFinishScreen(int correctAnswers) {

    }
}
