package ge.edu.freeuni.rsr.tournaments.create

interface TournamentCreateContract {
    interface TournamentCreateView {
        fun goToNextStep()
        fun errorCreation(error: String)
    }

    interface TournamentCreatePresenter {
        fun saveTournament(name: String, startTime: String)
    }

    interface TournamentCreateInteractor {
        interface OnFinishListener {
            fun OnTournamentSaved()
            fun OnError()
        }

        fun saveTournament(name: String, startTime: String, userId: Int, listener: OnFinishListener)
    }
}