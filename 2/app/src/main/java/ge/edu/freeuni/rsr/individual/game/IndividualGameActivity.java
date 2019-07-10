package ge.edu.freeuni.rsr.individual.game;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import ge.edu.freeuni.rsr.R;
import ge.edu.freeuni.rsr.common.component.CustomDialogFragment;
import ge.edu.freeuni.rsr.individual.game.entity.Answer;
import ge.edu.freeuni.rsr.individual.game.entity.Question;

public class IndividualGameActivity extends AppCompatActivity implements IndividualGameContract.IndividualGameView, CustomDialogFragment.AnswerSubmissionDialogListener {

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


    @BindView(R.id.imgSendAnswer)
    ImageView imgSendAnswer;

    @BindView(R.id.loader_view)
    LinearLayout loaderView;

    @OnClick(R.id.imgSendAnswer)
    void onSendAnswerClick(View view) {
        hideKeyboard();
        presenter.checkAnswer(etAnswerInput.getText().toString());
        etAnswerInput.setText("");
        timer.cancel();
        view.setEnabled(false);
    }


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
        presenter.loadNextQuestion();
    }

    private String toTime(long seconds) {
        return "" + seconds / 60 + ":" + seconds % 60;
    }

    private void createTimer() {
        timer = new CountDownTimer(timeOnSingleQuestion, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timeOnSingleQuestion = millisUntilFinished;
                tvTime.setText(toTime(timeOnSingleQuestion / 1000));
            }

            @Override
            public void onFinish() {
                presenter.checkAnswer(String.valueOf(etAnswerInput.getText()));
                timer.cancel();
            }
        };
    }

    @Override
    public void displayQuestion(Question question, String numberOutOf) {
        tvQuestionContent.setText(question.getQuestionContent());
        tvNumberOutOf.setText(numberOutOf);
        timer.start();
    }

    @Override
    public void renderCorrectAnswerScreen() {
        CustomDialogFragment.newInstance(true, "", false).show(getSupportFragmentManager(), "");
    }

    @Override
    public void renderWrongAnswerScreen(Answer correctAnswer) {
        CustomDialogFragment.newInstance(false, correctAnswer.getAnswer(), false).show(getSupportFragmentManager(), "");
    }

    @Override
    public void loadFinishScreen(int correctAnswers) {
        presenter.finishGame(correctAnswers);
        finish();
        FinishActivity.start(this, correctAnswers);
    }

    @Override
    public void showLoader(boolean isLoading) {
        if (isLoading) {
            loaderView.setVisibility(View.VISIBLE);
        } else {
            loaderView.setVisibility(View.GONE);
        }
    }

    @Override
    public void OnNextQuestionClicked(DialogFragment dialog) {
        dialog.dismiss();
        presenter.loadNextQuestion();
        imgSendAnswer.setEnabled(true);
    }

    private void hideKeyboard() {
        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }
}
