package ge.edu.freeuni.rsr.tournaments.game.game

import ge.edu.freeuni.rsr.AppUser
import ge.edu.freeuni.rsr.tournaments.entity.Tournament

class TournamentGamePresenterImpl(
        private val view: TournamentGameContract.TournamentGameView,
        private val interactor: TournamentGameContract.TournamentGameInteractor
) : TournamentGameContract.TournamentGamePresenter {


    private var tournament: Tournament? = null
    private var currQuestion: Int = -1
    private var lastAnswerState = false

    private var numCorrect = 0

    override fun getTournamentData(id: Int) {
        view.showLoader(true)
        interactor.loadSingleTournament(id, OnFinishListenerImpl())
    }

    override fun sendAnswer(answer: String) {
        for (correctAnswer in tournament!!.questions[currQuestion].answers) {
            if (answer == correctAnswer.answer) {
                lastAnswerState = true
                return
            }
        }
        lastAnswerState = false
    }

    override fun checkSentAnswer() {
        if (lastAnswerState) {
            numCorrect++
            view.renderCorrectAnswer()
        } else {
            view.renderWrongAnswer(tournament!!.questions[currQuestion].answers[0].answer)
        }
        lastAnswerState = false
    }

    override fun getNextQuestion() {
        currQuestion++
        if (currQuestion < tournament!!.questions.size) {
            view.displayQuestion(tournament!!.questions[currQuestion].question_content)
        } else {
            interactor.updateTournamentResults(AppUser.getInstance().user.id, tournament!!.id, numCorrect, OnFinishListenerImpl())
        }

    }

    inner class OnFinishListenerImpl : TournamentGameContract.TournamentGameInteractor.OnFinishListener {
        override fun onDataLoaded(tournament: Tournament) {
            this@TournamentGamePresenterImpl.tournament = tournament
            if (tournament.seconds_until > 0) {
                view.showLoader(false)
                view.showPreTournamentView(tournament.seconds_until)
            } else {
                view.showLoader(false)
                view.expired()
            }
        }

        override fun onTourExpired() {
            view.expired()
        }

        override fun onResultsSaved() {
            view.showTournamentEndedInfo(numCorrect)
        }
    }
}