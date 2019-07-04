package ge.edu.freeuni.rsr.tournaments.add

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import butterknife.ButterKnife
import ge.edu.freeuni.rsr.R
import ge.edu.freeuni.rsr.tournaments.config.TournamentConfigActivity

class TournamentGameActivity : AppCompatActivity() {

    companion object {
        @JvmStatic
        fun start(previous: Context) {
            val intent = Intent(previous, TournamentConfigActivity::class.java)
            previous.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
        setContentView(R.layout.activity_tournament_game)
        ButterKnife.bind(this)
    }
}
