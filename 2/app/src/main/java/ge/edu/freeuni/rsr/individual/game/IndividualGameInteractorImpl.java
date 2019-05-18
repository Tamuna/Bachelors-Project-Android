package ge.edu.freeuni.rsr.individual.game;

import java.util.ArrayList;

import ge.edu.freeuni.rsr.individual.game.entity.CorrectAnswers;
import ge.edu.freeuni.rsr.individual.game.entity.Question;
import ge.edu.freeuni.rsr.network.Api;
import ge.edu.freeuni.rsr.network.RetrofitInstance;
import retrofit2.Retrofit;

public class IndividualGameInteractorImpl implements IndividualGameContract.IndividualGameInteractor {
    private Api api;
    private Retrofit retrofit;

    public IndividualGameInteractorImpl() {
        retrofit = RetrofitInstance.getRetrofitInstance();
        api = retrofit.create(Api.class);
    }

    @Override
    public void loadNextQuestion(OnFinishListener listener) {
        Question question = new Question();
        question.setId(1);
        question.setQuestionContent("რა არის თბილისი?");
        listener.onNextQuestionLoaded(question);
//        api.loadNextQuestion(1).enqueue(new Callback<List<Question>>() {
//            @Override
//            public void onResponse(Call<List<Question>> call, Response<List<Question>> response) {
//                Question question = new Question();
//                question.setId(1);
//                question.setQuestionContent("რა არის თბილისი?");
//                listener.onNextQuestionLoaded(response.body().get(0));
//            }
//
//            @Override
//            public void onFailure(Call<List<Question>> call, Throwable t) {
//                Log.d("rsr", "data_fail");
//            }
//        });
    }

    @Override
    public void checkAnswer(OnFinishListener listener, int questionId, String answer) {
        CorrectAnswers answers = new CorrectAnswers();
        answers.setCorrect(answer.equals("ქალაქი"));
        answers.setAnswers(new ArrayList<>());
        listener.onCorrectAnswerLoaded(answers);
//        api.checkAnswer(questionId, answer).enqueue(new Callback<CorrectAnswers>() {
//            @Override
//            public void onResponse(Call<CorrectAnswers> call, Response<CorrectAnswers> response) {
//                listener.onCorrectAnswerLoaded(response.body());
//            }
//
//            @Override
//            public void onFailure(Call<CorrectAnswers> call, Throwable t) {
//
//            }
//        });
    }

    @Override
    public void finishIndividualGame(OnFinishListener listener, int correctAnswers) {
        api.finishGame(1, correctAnswers).enqueue(null);
    }
}
