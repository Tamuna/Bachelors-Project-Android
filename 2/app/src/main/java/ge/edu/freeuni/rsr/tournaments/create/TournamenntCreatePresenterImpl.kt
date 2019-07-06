package ge.edu.freeuni.rsr.tournaments.create

import ge.edu.freeuni.rsr.AppUser

class TournamenntCreatePresenterImpl(private val view: TournamentCreateContract.TournamentCreateView,
                                     private val interactor: TournamentCreateContract.TournamentCreateInteractor
) : TournamentCreateContract.TournamentCreatePresenter {
    override fun saveTournament(name: String, startTime: String) {
        interactor.saveTournament(name, startTime, AppUser.getInstance().user.id, OnFinishListenerImpl())
    }

    inner class OnFinishListenerImpl : TournamentCreateContract.TournamentCreateInteractor.OnFinishListener {
        override fun OnTournamentSaved() {
            view.goToNextStep()
        }

        override fun OnError() {
            view.errorCreation("გთხოვთ მიუთითოთ ვალიდური მონაცემები")
        }

    }

}