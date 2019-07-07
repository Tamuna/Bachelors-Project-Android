package ge.edu.freeuni.rsr.tournaments.game

import ge.edu.freeuni.rsr.tournaments.game.entity.Tournament

interface TournamentGameContract {

    interface TournamentGameView {
        fun displayAllTournamments(tournaments: List<Tournament>)
    }

    interface TournamentGamePresenter {
        fun getAllTournaments()
    }

    interface TournamentGameInteractor {
        interface OnFinishListener {
            fun onTournamentsLoaded(tournaments: List<Tournament>)
        }

        fun getAllTournaments(listener: OnFinishListener)
    }
}