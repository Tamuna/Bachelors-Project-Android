package ge.edu.freeuni.rsr.tournaments.game.game

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
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
import ge.edu.freeuni.rsr.common.component.AfterAnswerSubmissionDialog
import ge.edu.freeuni.rsr.home.HomeActivity


class TournamentGameActivity : AppCompatActivity(), TournamentGameContract.TournamentGameView {

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

    @BindView(R.id.txtStart)
    lateinit var txtAction: TextView

    @OnClick(R.id.txtStart)
    fun onStartClick() {
        presenter?.getTournamentData(tournamentId)
    }


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
        createTimer()
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

    override fun showPreTournamentView(startTime: String) {
        containerPreTournament.visibility = View.VISIBLE
        containerGameHolder.visibility = View.GONE
        txtStartTime.text = startTime.substring(0, startTime.length - 3)
    }

    override fun displayQuestion(questionContent: String) {
        containerGameHolder.visibility = View.VISIBLE
        containerPreTournament.visibility = View.GONE
        tvQuestionContent.text = questionContent
        etAnswerInput.inputType = InputType.TYPE_CLASS_TEXT
        timer?.start()
    }

    override fun renderCorrectAnswer() {
        val dialog = AfterAnswerSubmissionDialog.newInstance(true, "", true)
        dialog.show(supportFragmentManager, "alert")
        dismissAfterFiveSec(dialog)
    }

    override fun renderWrongAnswer(correctAnswer: String) {
        val dialog = AfterAnswerSubmissionDialog.newInstance(false, correctAnswer, true)
        dialog.show(supportFragmentManager, "alert")
        dismissAfterFiveSec(dialog)
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
        txtAction.text = "მთავარი გვერდი"
        txtAction.setOnClickListener {
            HomeActivity.start(this)
            finish()
        }
        txtStartTime.visibility = View.GONE

    }


    private fun dismissAfterFiveSec(dialog: DialogFragment) {
        object : CountDownTimer(5000, 1000) {
            override fun onTick(millisUntilFinished: Long) {

            }

            override fun onFinish() {
                dialog.dismiss()
                presenter?.getNextQuestion()
                imgSendAnswer.isEnabled = true
            }
        }.start()
    }

    private fun toTime(seconds: Long): String {
        return "" + seconds / 60 + ":" + seconds % 60
    }

    private fun createTimer() {
        timer = object : CountDownTimer(timeOnSingleQuestion, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                timeOnSingleQuestion = millisUntilFinished
                tvTime.text = toTime(timeOnSingleQuestion / 1000)
            }

            override fun onFinish() {
                presenter?.checkSentAnswer()
                timer?.cancel()
            }
        }
    }
}
