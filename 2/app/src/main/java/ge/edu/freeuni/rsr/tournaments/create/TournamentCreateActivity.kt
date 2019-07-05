package ge.edu.freeuni.rsr.tournaments.create

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import butterknife.BindView
import butterknife.ButterKnife
import ge.edu.freeuni.rsr.R
import ge.edu.freeuni.rsr.tournaments.create.viewpager.TournamentConfigFragment
import ge.edu.freeuni.rsr.tournaments.create.viewpager.ViewPagerAdapter
import ge.edu.freeuni.rsr.tournaments.create.viewpager.ViewPagerAdapter.Companion.FRAGMENT_QUESTION

class TournamentCreateActivity : AppCompatActivity(), TournamentConfigFragment.OnFragmentInteractionListener {
    override fun onNextClick(dateTime: String, tourName: String) {
        //TODO create tournament in db get id and start adding questions
        vpTournamentConfig.currentItem = FRAGMENT_QUESTION
    }

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
        vpTournamentConfig.adapter = ViewPagerAdapter(supportFragmentManager)
    }
}
