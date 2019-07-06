package ge.edu.freeuni.rsr.tournaments.create

import ge.edu.freeuni.rsr.AppUser

class TournamentCreatePresenterImpl(private val view: TournamentCreateContract.TournamentCreateView,
                                    private val interactor: TournamentCreateContract.TournamentCreateInteractor
) : TournamentCreateContract.TournamentCreatePresenter {
    private var tourId = -1


    override fun saveSingleQuestion(question: String, answers: List<String>) {
        interactor.saveSingleQuestion(question, answers, AppUser.getInstance().user.id, tourId, OnFinishListenerImpl())
    }


    override fun saveTournament(name: String, startTime: String) {
        interactor.saveTournament(name, startTime, AppUser.getInstance().user.id, OnFinishListenerImpl())
    }

    inner class OnFinishListenerImpl : TournamentCreateContract.TournamentCreateInteractor.OnFinishListener {
        override fun onQuestionAddedToTournament() {
            view.notifyQuestionSaved()
        }

        override fun onTournamentSaved(tourId: Int) {
            view.goToNextStep()
            this@TournamentCreatePresenterImpl.tourId = tourId
        }

        override fun onError() {
            view.errorCreation("გთხოვთ მიუთითოთ ვალიდური მონაცემები")
        }

    }

}