package ge.edu.freeuni.rsr.tournaments.game.list

import ge.edu.freeuni.rsr.tournaments.entity.Tournament

class TournamentListPresenterImpl(
        private val view: TournamentListContract.TournamentListView,
        private val interactor: TournamentListContract.TournamentListInteractor
) : TournamentListContract.TournamentListPresenter {
    override fun getAllTournaments() {
        interactor.getAllTournaments(OnFinishListenerImpl())
    }

    inner class OnFinishListenerImpl : TournamentListContract.TournamentListInteractor.OnFinishListener {
        override fun onTournamentsLoaded(tournaments: List<Tournament>) {
            view.displayAllTournamments(tournaments)
        }
    }
}