package ge.edu.freeuni.rsr.tournaments.game.game

import ge.edu.freeuni.rsr.tournaments.entity.Tournament

interface TournamentGameContract {
    interface TournamentGameView {
        fun showLoader(show: Boolean)
        fun showTournamentEndedInfo(numCorrect: Int)
        fun showPreTournamentView(startTime: Long)
        fun renderCorrectAnswer()
        fun renderWrongAnswer(correctAnswer: String)
        fun displayQuestion(questionContent: String)
        fun expired()
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
        fun updateTournamentResults(userId: Int, tourId: Int, points: Int, onFinishListener: OnFinishListener)
    }
}