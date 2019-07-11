package ge.edu.freeuni.rsr.tournaments.game.ratings

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ge.edu.freeuni.rsr.R

class RatingRecyclerAdapter : RecyclerView.Adapter<RatingViewHolder>() {
    private var data: MutableList<Rating> = ArrayList()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RatingViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.cell_rating, parent, false)
        return RatingViewHolder(view)
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: RatingViewHolder, position: Int) {
        holder.bindData(data[position])
    }

    fun bindData(data: List<Rating>) {
        this.data.clear()
        this.data.addAll(data)
        notifyDataSetChanged()
    }
}