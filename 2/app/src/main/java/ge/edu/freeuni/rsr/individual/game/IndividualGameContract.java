package ge.edu.freeuni.rsr.individual.game;

import java.util.List;

import ge.edu.freeuni.rsr.individual.game.entity.Answer;
import ge.edu.freeuni.rsr.individual.game.entity.CorrectAnswers;
import ge.edu.freeuni.rsr.individual.game.entity.Question;

public interface IndividualGameContract {
    interface IndividualGameView {

        void displayQuestion(Question question, String numberOutOf);

        void renderCorrectAnswerScreen();

        void renderWrongAnswerScreen(List<Answer> correctAnswers);

        void loadFinishScreen(int correctAnswers);
    }

    interface IndividualGamePresenter {

        void loadNextQuestion();

        void checkAnswer(String answer);

        void finishGame(int correctAnswers);
    }

    interface IndividualGameInteractor {

        interface OnFinishListener {

            void onNextQuestionLoaded(Question question);

            void onCorrectAnswerLoaded(CorrectAnswers answers);

            void onGameFinishSaved();
        }

        void loadNextQuestion(OnFinishListener listener);

        void checkAnswer(OnFinishListener listener, int questionId, String answer);

        void finishIndividualGame(OnFinishListener listener, int correctAnswers);
    }
}
