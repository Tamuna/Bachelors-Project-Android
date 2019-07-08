package ge.edu.freeuni.rsr.tournaments.game.game

import ge.edu.freeuni.rsr.tournaments.game.entity.Tournament

interface TournamentGameContract {
    interface TournamentGameView {
        fun showLoader(show: Boolean)
        fun showTournamentEndedInfo(numCorrect: Int)
        fun showPreTournamentView(startTime: String)
        fun renderCorrectAnswer()
        fun renderWrongAnswer(correctAnswer: String)
        fun displayQuestion(questionContent: String)
    }

    interface TournamentGamePresenter {
        fun getTournamentData(id: Int)
        fun sendAnswer(answer: String)
        fun checkSentAnswer()
        fun getNextQuestion()
    }

    interface TournamentGameInteractor {
        interface OnFinishListener {
            fun onDataLoaded(tournament: Tournament)
            fun onTourExpired()
            fun onResultsSaved()
        }

        fun loadSingleTournament(id: Int, onFinishListener: OnFinishListener)
        fun updateTournamentResults(id: Int?, id1: Int, numCorrect: Int, onFinishListener: OnFinishListener)
    }
}