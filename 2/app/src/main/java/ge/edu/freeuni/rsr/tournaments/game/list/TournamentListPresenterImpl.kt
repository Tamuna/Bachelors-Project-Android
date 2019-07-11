package ge.edu.freeuni.rsr.tournaments.game.list

import ge.edu.freeuni.rsr.tournaments.entity.Tournament
import ge.edu.freeuni.rsr.tournaments.game.ratings.Rating

class TournamentListPresenterImpl(
        private val view: TournamentListContract.TournamentListView,
        private val interactor: TournamentListContract.TournamentListInteractor
) : TournamentListContract.TournamentListPresenter {
    override fun getRatingsForTour(dialogId: Int) {
        interactor.getRatingsForTour(dialogId, OnFinishListenerImpl())
    }

    override fun getAllTournaments() {
        interactor.getAllTournaments(OnFinishListenerImpl())
    }

    inner class OnFinishListenerImpl : TournamentListContract.TournamentListInteractor.OnFinishListener {
        override fun onRatingsLoaded(ratings: List<Rating>) {
            view.displayRatings(ratings)
        }

        override fun onTournamentsLoaded(tournaments: List<Tournament>) {
            view.displayAllTournamments(tournaments)
        }
    }
}