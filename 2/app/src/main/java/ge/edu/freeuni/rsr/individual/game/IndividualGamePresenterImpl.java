package ge.edu.freeuni.rsr.individual.game;

import ge.edu.freeuni.rsr.individual.game.entity.CorrectAnswers;
import ge.edu.freeuni.rsr.individual.game.entity.Question;

public class IndividualGamePresenterImpl implements IndividualGameContract.IndividualGamePresenter {

    private IndividualGameContract.IndividualGameInteractor interactor;
    private IndividualGameContract.IndividualGameView view;
    private int totalQuestions;
    private int numQuestion = 0;
    private int numCorrect = 0;
    private int curQuestionId;

    public IndividualGamePresenterImpl(IndividualGameContract.IndividualGameInteractor interactor, IndividualGameContract.IndividualGameView view, int totalQuestions) {
        this.interactor = interactor;
        this.view = view;
        this.totalQuestions = totalQuestions;
    }

    @Override
    public void loadNextQuestion() {
        view.showLoader(true);
        numQuestion++;
        if (numQuestion > totalQuestions) {
            view.loadFinishScreen(numCorrect);
        } else {
            interactor.loadNextQuestion(new OnFinishListenerImpl());
        }
    }

    @Override
    public void checkAnswer(String answer) {
        if(answer.length() == 0) answer = "~";
        interactor.checkAnswer(new OnFinishListenerImpl(), curQuestionId, answer);
    }

    @Override
    public void finishGame(int correctAnswers) {
        interactor.finishIndividualGame(new OnFinishListenerImpl(), numCorrect);
    }

    class OnFinishListenerImpl implements IndividualGameContract.IndividualGameInteractor.OnFinishListener {

        @Override
        public void onNextQuestionLoaded(Question question) {
            curQuestionId = question.getId();
            view.showLoader(false);
            view.displayQuestion(question, numQuestion + "/" + totalQuestions);
        }

        @Override
        public void onCorrectAnswerLoaded(CorrectAnswers answers) {
            if (answers != null && answers.isCorrect()) {
                numCorrect++;
                view.renderCorrectAnswerScreen();
            } else {
                view.renderWrongAnswerScreen(answers.getAnswers().get(0));
            }

        }

        @Override
        public void onGameFinishSaved() {
            view.loadFinishScreen(numCorrect);
        }
    }
}
