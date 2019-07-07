package ge.edu.freeuni.rsr.tournaments.game.tourslist

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import butterknife.BindView
import butterknife.ButterKnife
import ge.edu.freeuni.rsr.R
import ge.edu.freeuni.rsr.tournaments.game.entity.Tournament

class TournirViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    @BindView(R.id.txtNumQuestions)
    lateinit var numQuestions: TextView

    @BindView(R.id.txtTime)
    lateinit var time: TextView

    @BindView(R.id.txtTourName)
    lateinit var header: TextView


    init {
        ButterKnife.bind(this, itemView)
    }

    fun bindData(tournament: Tournament, listener: TournamentsRecyclerAdapter.OnItemClickListener) {
        time.text = tournament.start_time
        numQuestions.text = "" + tournament.question_count
        itemView.setOnClickListener { listener.onItemClicked(tournament.id) }
    }
}