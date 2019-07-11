package ge.edu.freeuni.rsr.tournaments.game.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ge.edu.freeuni.rsr.R
import ge.edu.freeuni.rsr.tournaments.entity.Tournament

class TournamentsRecyclerAdapter(private val listener: OnItemClickListener) : RecyclerView.Adapter<TournamentViewHolder>() {
    private var data: MutableList<Tournament> = ArrayList()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TournamentViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.cell_tournament, parent, false)
        return TournamentViewHolder(view)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: TournamentViewHolder, position: Int) {
        holder.bindData(data[position], listener)
    }

    fun bindData(data: List<Tournament>) {
        this.data.clear()
        this.data.addAll(data)
        notifyDataSetChanged()
    }

    interface OnItemClickListener {
        fun onItemClicked(tournamentId: Int)
        fun onExpiredItemClick(tournamentId: Int)
    }
}