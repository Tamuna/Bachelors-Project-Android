package ge.edu.freeuni.rsr.tournaments.game.list

import ge.edu.freeuni.rsr.tournaments.entity.Tournament

interface TournamentListContract {

    interface TournamentListView {
        fun displayAllTournamments(tournaments: List<Tournament>)
    }

    interface TournamentListPresenter {
        fun getAllTournaments()
    }

    interface TournamentListInteractor {
        interface OnFinishListener {
            fun onTournamentsLoaded(tournaments: List<Tournament>)
        }

        fun getAllTournaments(listener: OnFinishListener)
    }
}