package ge.edu.freeuni.rsr.tournaments.game.ratings

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import butterknife.BindView
import butterknife.ButterKnife
import ge.edu.freeuni.rsr.R

class RatingViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    @BindView(R.id.tv_points)
    lateinit var points: TextView

    @BindView(R.id.tv_username)
    lateinit var username: TextView

    init {
        ButterKnife.bind(this, itemView)
    }

    fun bindData(rating: Rating) {
        username.text = rating.username
        points.text = "" + rating.point
    }
}