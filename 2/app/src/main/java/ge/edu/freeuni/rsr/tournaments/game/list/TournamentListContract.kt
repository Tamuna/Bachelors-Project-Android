package ge.edu.freeuni.rsr.tournaments.game.list

import ge.edu.freeuni.rsr.tournaments.entity.Tournament
import ge.edu.freeuni.rsr.tournaments.game.ratings.Rating

interface TournamentListContract {

    interface TournamentListView {
        fun displayAllTournamments(tournaments: List<Tournament>)
        fun displayRatings(ratings: List<Rating>)
    }

    interface TournamentListPresenter {
        fun getAllTournaments()
        fun getRatingsForTour(dialogId: Int)
    }

    interface TournamentListInteractor {
        interface OnFinishListener {
            fun onTournamentsLoaded(tournaments: List<Tournament>)
            fun onRatingsLoaded(ratings: List<Rating>)
        }

        fun getAllTournaments(listener: OnFinishListener)
        fun getRatingsForTour(dialogId: Int, listener: OnFinishListener)
    }
}