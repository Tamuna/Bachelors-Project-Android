package ge.edu.freeuni.rsr.tournaments.game.tourslist

import ge.edu.freeuni.rsr.tournaments.game.TournamentGameContract
import ge.edu.freeuni.rsr.tournaments.game.entity.Tournament

class TournamentGamePresenterImpl(
        private val view: TournamentGameContract.TournamentGameView,
        private val interactor: TournamentGameContract.TournamentGameInteractor
) : TournamentGameContract.TournamentGamePresenter {
    override fun getAllTournaments() {
        interactor.getAllTournaments(OnFinishListenerImpl())
    }

    inner class OnFinishListenerImpl : TournamentGameContract.TournamentGameInteractor.OnFinishListener {
        override fun onTournamentsLoaded(tournaments: List<Tournament>) {
            view.displayAllTournamments(tournaments)
        }
    }
}