package ge.edu.freeuni.rsr.tournaments.game.list

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import butterknife.BindView
import butterknife.ButterKnife
import ge.edu.freeuni.rsr.R
import ge.edu.freeuni.rsr.tournaments.entity.Tournament
import ge.edu.freeuni.rsr.tournaments.game.game.TournamentGameActivity
import ge.edu.freeuni.rsr.tournaments.game.ratings.Rating
import ge.edu.freeuni.rsr.tournaments.game.ratings.RatingRecyclerAdapter

class TournamentListActivity : AppCompatActivity(), TournamentListContract.TournamentListView {

    @BindView(R.id.rvTournirs)
    lateinit var rvTournirs: RecyclerView

    @BindView(R.id.rvRatings)
    lateinit var rvRatings: RecyclerView

    @BindView(R.id.containerRatings)
    lateinit var containerRatings: ConstraintLayout

    private var presenter: TournamentListContract.TournamentListPresenter? = null
    private var adapter: TournamentsRecyclerAdapter? = null
    private var ratingsAdapter: RatingRecyclerAdapter? = null
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
        ratingsAdapter = RatingRecyclerAdapter()
        rvTournirs.layoutManager = LinearLayoutManager(this)
        rvTournirs.adapter = adapter
        presenter?.getAllTournaments()

        rvRatings.layoutManager = LinearLayoutManager(this)
        rvRatings.adapter = ratingsAdapter
    }

    inner class OnItemClickListenerImpl : TournamentsRecyclerAdapter.OnItemClickListener {
        override fun onExpiredItemClick(tournamentId: Int) {
            presenter?.getRatingsForTour(tournamentId)
        }

        override fun onItemClicked(tournamentId: Int) {
            TournamentGameActivity.start(this@TournamentListActivity, tournamentId)
        }

    }

    override fun onBackPressed() {
        if (containerRatings.isVisible) {
            containerRatings.visibility = View.GONE
        } else {
            super.onBackPressed()
        }
    }

    override fun displayRatings(ratings: List<Rating>) {
        containerRatings.visibility = View.VISIBLE
        ratingsAdapter?.bindData(ratings)
    }
}
