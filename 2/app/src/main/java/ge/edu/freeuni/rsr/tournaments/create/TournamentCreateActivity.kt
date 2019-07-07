package ge.edu.freeuni.rsr.tournaments.create

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.WindowManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import butterknife.BindView
import butterknife.ButterKnife
import ge.edu.freeuni.rsr.R
import ge.edu.freeuni.rsr.tournaments.create.viewpager.QuestionInputFragment
import ge.edu.freeuni.rsr.tournaments.create.viewpager.TournamentConfigFragment
import ge.edu.freeuni.rsr.tournaments.create.viewpager.ViewPagerAdapter
import ge.edu.freeuni.rsr.tournaments.create.viewpager.ViewPagerAdapter.Companion.FRAGMENT_QUESTION
import ge.edu.freeuni.rsr.tournaments.game.TournamentGameActivity

class TournamentCreateActivity :
        AppCompatActivity(),
        TournamentConfigFragment.OnFragmentInteractionListener,
        TournamentCreateContract.TournamentCreateView,
        QuestionInputFragment.OnFragmentInteractionListener {
    override fun onSaveQuestionClick(question: String, answers: List<String>) {
        presenter?.saveSingleQuestion(question, answers)
    }

    override fun onFinishCreatingClick() {
        Toast.makeText(this, "ტურნირის შექმნა წამატებით დასრულდა!", Toast.LENGTH_SHORT).show()
        TournamentGameActivity.start(this)
    }

    override fun notifyQuestionSaved() {
        Toast.makeText(this, "კითხვა წარმატებით დაემატა ტურნირში!", Toast.LENGTH_SHORT).show()
    }

    private var presenter: TournamentCreateContract.TournamentCreatePresenter? = null
    @BindView(R.id.vpTournamentConfig)
    lateinit var vpTournamentConfig: ViewPager

    companion object {
        @JvmStatic
        fun start(previous: Context) {
            val intent = Intent(previous, TournamentCreateActivity::class.java)
            previous.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
        setContentView(R.layout.activity_tournament_create)
        ButterKnife.bind(this)
        presenter = TournamentCreatePresenterImpl(this, TournamentCreateInteractorImpl())
        vpTournamentConfig.adapter = ViewPagerAdapter(supportFragmentManager)
    }

    override fun goToNextStep() {
        vpTournamentConfig.currentItem = FRAGMENT_QUESTION
    }

    override fun errorCreation(error: String) {
        Toast.makeText(this, error, Toast.LENGTH_SHORT).show()
    }

    override fun onNextClick(dateTime: String, tourName: String) {
        presenter?.saveTournament(tourName, dateTime)
    }
}
