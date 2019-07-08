package ge.edu.freeuni.rsr.tournaments.game.list

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import butterknife.BindView
import butterknife.ButterKnife
import ge.edu.freeuni.rsr.R
import ge.edu.freeuni.rsr.tournaments.game.entity.Tournament
import ge.edu.freeuni.rsr.tournaments.game.game.TournamentGameActivity

class TournamentListActivity : AppCompatActivity(), TournamentListContract.TournamentListView {


    @BindView(R.id.rvTournirs)
    lateinit var rvTournirs: RecyclerView
    private var presenter: TournamentListContract.TournamentListPresenter? = null
    private var adapter: TournamentsRecyclerAdapter? = null
    override fun displayAllTournamments(tournaments: List<Tournament>) {
        adapter?.bindData(tournaments)
    }


    companion object {
        @JvmStatic
        fun start(previous: Context) {
            val intent = Intent(previous, TournamentListActivity::class.java)
            previous.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
        setContentView(R.layout.activity_tournament_list)
        ButterKnife.bind(this)

        presenter = TournamentListPresenterImpl(this, TournamentGameInteractorImpl())
        adapter = TournamentsRecyclerAdapter(OnItemClickListenerImpl())
        rvTournirs.layoutManager = LinearLayoutManager(this)
        rvTournirs.adapter = adapter
        presenter?.getAllTournaments()
    }

    inner class OnItemClickListenerImpl : TournamentsRecyclerAdapter.OnItemClickListener {
        override fun onItemClicked(tournamentId: Int) {
            TournamentGameActivity.start(this@TournamentListActivity, tournamentId)
        }
    }
}
