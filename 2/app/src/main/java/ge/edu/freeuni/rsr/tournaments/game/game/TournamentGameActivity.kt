package ge.edu.freeuni.rsr.tournaments.game.game

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import android.os.PersistableBundle
import android.text.InputType
import android.view.View
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.DialogFragment
import butterknife.BindView
import butterknife.ButterKnife
import butterknife.OnClick
import ge.edu.freeuni.rsr.R
import ge.edu.freeuni.rsr.common.component.CustomDialogFragment
import ge.edu.freeuni.rsr.tournaments.game.list.TournamentListActivity


class TournamentGameActivity : AppCompatActivity(), TournamentGameContract.TournamentGameView {
    override fun expired() {
        Toast.makeText(this, "ტურნირი უკვე დაიწყო", Toast.LENGTH_SHORT).show()
        finish()
    }

    private var timeOnSingleQuestion: Long = 120000
    private var timer: CountDownTimer? = null
    private var presenter: TournamentGameContract.TournamentGamePresenter? = null
    private var tournamentId: Int = -1

    @BindView(R.id.tvTime)
    lateinit var tvTime: TextView

    @BindView(R.id.tvQuestionContent)
    lateinit var tvQuestionContent: TextView

    @BindView(R.id.tvNumberOutOf)
    lateinit var tvNumberOutOf: TextView

    @BindView(R.id.etAnswerInput)
    lateinit var etAnswerInput: EditText

    @BindView(R.id.imgSendAnswer)
    lateinit var imgSendAnswer: ImageView

    @BindView(R.id.loader_view)
    lateinit var loaderView: LinearLayout

    @BindView(R.id.containerPreTournament)
    lateinit var containerPreTournament: ConstraintLayout

    @BindView(R.id.containerGameHolder)
    lateinit var containerGameHolder: ConstraintLayout

    @BindView(R.id.txtStartTime)
    lateinit var txtStartTime: TextView

    @BindView(R.id.txtInfo)
    lateinit var txtInfo: TextView


    @OnClick(R.id.imgSendAnswer)
    fun onSendAnswerClick(view: View) {
        hideKeyboard()
        presenter?.sendAnswer(etAnswerInput.text.toString())
        etAnswerInput.setText("")
        etAnswerInput.inputType = InputType.TYPE_NULL
        view.isEnabled = false
        Toast.makeText(this, "პასუხი დაფიქსირებულია, სწორ პასუხს გაიგებთ დროის ამოწურვის შემდეგ!", Toast.LENGTH_LONG).show()
    }

    companion object {
        private const val TOURNAMENT_ID: String = "tournamentId"
        private const val TIMER_TYPE_QUESTION: Int = 1
        private const val TIMER_TYPE_TIME_LEFT: Int = 2
        fun start(previous: Context, tournamentId: Int) {
            val intent = Intent(previous, TournamentGameActivity::class.java)
            intent.putExtra(TOURNAMENT_ID, tournamentId)
            previous.startActivity(intent)
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tournament_game)
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
        ButterKnife.bind(this)
        presenter = TournamentGamePresenterImpl(this, TournamentGameInteractorImpl())
        tournamentId = intent.getIntExtra(TOURNAMENT_ID, 0)
        presenter?.getTournamentData(tournamentId)
    }

    private fun hideKeyboard() {
        val view = this.currentFocus
        if (view != null) {
            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(view.windowToken, 0)
        }
    }


    override fun showLoader(show: Boolean) {
        if (show)
            loaderView.visibility = View.VISIBLE
        else
            loaderView.visibility = View.GONE
    }

    override fun showPreTournamentView(timeLeft: Long) {
        containerPreTournament.visibility = View.VISIBLE
        containerGameHolder.visibility = View.GONE
        createTimer(TIMER_TYPE_TIME_LEFT, timeLeft)
        timer?.start()
    }

    override fun displayQuestion(questionContent: String) {

        containerGameHolder.visibility = View.VISIBLE
        containerPreTournament.visibility = View.GONE
        tvQuestionContent.text = questionContent
        etAnswerInput.inputType = InputType.TYPE_CLASS_TEXT
        createTimer(TIMER_TYPE_QUESTION, 120000)
        timer?.start()
    }

    override fun onSaveInstanceState(outState: Bundle?, outPersistentState: PersistableBundle?) {

    }

    override fun renderCorrectAnswer() {
//        if (lifecycle.currentState.isAtLeast(Lifecycle.State.STARTED)) {
        val dialog = CustomDialogFragment.newInstance(true, "", true)
        dialog.show(supportFragmentManager, "alert")
        dismissAfterFiveSec(dialog)
//        }
    }

    override fun renderWrongAnswer(correctAnswer: String) {
//        if (lifecycle.currentState.isAtLeast(Lifecycle.State.STARTED)) {
        val dialog = CustomDialogFragment.newInstance(false, correctAnswer, true)
        dialog.show(supportFragmentManager, "alert")
        dismissAfterFiveSec(dialog)
//        }
    }

    override fun showTournamentEndedInfo(numCorrect: Int) {
        val info: String = if (numCorrect > 0) {
            "თქვენ წარმატებით დაასრულეთ ტურნირი და დააგროვეთ $numCorrect სწორი პასუხი"
        } else {
            "თქვენ წარმატებით დაასრულეთ ტურნირი"
        }
        txtInfo.text = info
        containerPreTournament.visibility = View.VISIBLE
        containerGameHolder.visibility = View.GONE
        txtStartTime.visibility = View.GONE
    }


    private fun dismissAfterFiveSec(dialog: DialogFragment) {
        object : CountDownTimer(5000, 1000) {
            override fun onTick(millisUntilFinished: Long) {

            }

            override fun onFinish() {
//                if (lifecycle.currentState.isAtLeast(Lifecycle.State.STARTED)) {
                dialog.dismissAllowingStateLoss()
                presenter?.getNextQuestion()
                imgSendAnswer.isEnabled = true
//                }
            }
        }.start()
    }

    private fun toTime(seconds: Long): String {
        return "" + seconds / 60 + ":" + seconds % 60
    }


    private fun createTimer(timerType: Int, timeToCountDown: Long) {
        timeOnSingleQuestion = timeToCountDown
        if (timerType == TIMER_TYPE_TIME_LEFT) {
            timeOnSingleQuestion *= 1000
        }
        timer = object : CountDownTimer(timeOnSingleQuestion, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                timeOnSingleQuestion = millisUntilFinished
                if (timerType == TIMER_TYPE_QUESTION) {
                    tvTime.text = toTime(timeOnSingleQuestion / 1000)
                } else {
                    txtStartTime.text = toDayAndTime(timeOnSingleQuestion / 1000)
                }
            }

            override fun onFinish() {
                if (timerType == TIMER_TYPE_QUESTION) {
                    presenter?.checkSentAnswer()
                    timer?.cancel()
                } else if (timerType == TIMER_TYPE_TIME_LEFT) {
                    presenter?.getNextQuestion()
                }
            }
        }
    }

    private fun toDayAndTime(timeInSecs: Long): String {
        var remainingTime = timeInSecs
        val days = (remainingTime / (24 * 60 * 60)).toInt()
        remainingTime -= (days * 24 * 60 * 60)
        val hours = (remainingTime / (3600)).toInt()
        remainingTime -= hours * 3600
        val mins = (remainingTime / 60).toInt()
        remainingTime -= mins * 60
        val seconds = remainingTime

        return "$days დღე, $hours საათი, $mins:$seconds"
    }

    override fun onBackPressed() {
        finish()
        TournamentListActivity.start(this)
    }
}
