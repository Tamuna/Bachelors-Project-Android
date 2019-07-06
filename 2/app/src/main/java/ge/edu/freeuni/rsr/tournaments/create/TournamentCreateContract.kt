package ge.edu.freeuni.rsr.tournaments.create

interface TournamentCreateContract {
    interface TournamentCreateView {
        fun goToNextStep()
        fun errorCreation(error: String)
        fun notifyQuestionSaved()
    }

    interface TournamentCreatePresenter {
        fun saveTournament(name: String, startTime: String)
        fun saveSingleQuestion(question: String, answers: List<String>)
    }

    interface TournamentCreateInteractor {
        interface OnFinishListener {
            fun onTournamentSaved(tourId: Int)
            fun onError()
            fun onQuestionAddedToTournament()
        }

        fun saveTournament(name: String, startTime: String, userId: Int, listener: OnFinishListener)
        fun saveSingleQuestion(question: String, answers: List<String>, userId: Int, tourId: Int, listener: OnFinishListener)
    }
}