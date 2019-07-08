package ge.edu.freeuni.rsr.tournaments.config

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import butterknife.ButterKnife
import butterknife.OnClick
import ge.edu.freeuni.rsr.R
import ge.edu.freeuni.rsr.tournaments.create.TournamentCreateActivity
import ge.edu.freeuni.rsr.tournaments.game.list.TournamentListActivity

class TournamentConfigActivity : AppCompatActivity() {

    companion object {
        @JvmStatic
        fun start(previous: Context) {
            val intent = Intent(previous, TournamentConfigActivity::class.java)
            previous.startActivity(intent)
        }
    }

    @OnClick(R.id.btn_add_tour)
    fun onAddTourClick() {
        TournamentCreateActivity.start(this)
    }

    @OnClick(R.id.btn_enter_tours)
    fun onEnterToursClick() {
        TournamentListActivity.start(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
        setContentView(R.layout.activity_tournament_config)
        ButterKnife.bind(this)
    }


}
