package ge.edu.freeuni.rsr.tournaments.game.list

import android.view.View
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import butterknife.BindView
import butterknife.ButterKnife
import ge.edu.freeuni.rsr.R
import ge.edu.freeuni.rsr.tournaments.entity.Tournament

class TournamentViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    @BindView(R.id.container)
    lateinit var container: ConstraintLayout

    @BindView(R.id.txtNumQuestions)
    lateinit var numQuestions: TextView

    @BindView(R.id.txtTime)
    lateinit var time: TextView

    @BindView(R.id.txtTourName)
    lateinit var header: TextView

    @BindView(R.id.txtInfoEnded)
    lateinit var txtInfoEnded: TextView


    init {
        ButterKnife.bind(this, itemView)
    }

    fun bindData(tournament: Tournament, listener: TournamentsRecyclerAdapter.OnItemClickListener) {
        time.text = tournament.start_time.substring(0, tournament.start_time.length - 3)
        numQuestions.text = "" + tournament.question_count
        header.text = tournament.tournament_name
        if (tournament.expired) {
            container.setBackgroundResource(R.color.light_gray)
            itemView.setOnClickListener { listener.onExpiredItemClick(tournament.id) }
            txtInfoEnded.visibility = View.VISIBLE

        } else {
            txtInfoEnded.visibility = View.GONE
            container.setBackgroundResource(R.color.white)
            itemView.setOnClickListener { listener.onItemClicked(tournament.id) }
        }
    }
}