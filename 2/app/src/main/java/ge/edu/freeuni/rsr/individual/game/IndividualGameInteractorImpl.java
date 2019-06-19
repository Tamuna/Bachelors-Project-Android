package ge.edu.freeuni.rsr.individual.game;

import ge.edu.freeuni.rsr.individual.game.entity.CorrectAnswers;
import ge.edu.freeuni.rsr.individual.game.entity.IndGameResponse;
import ge.edu.freeuni.rsr.individual.game.entity.Question;
import ge.edu.freeuni.rsr.network.Api;
import ge.edu.freeuni.rsr.network.RetrofitInstance;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
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
        api.loadNextQuestion().enqueue(new Callback<IndGameResponse>() {
            @Override
            public void onResponse(Call<IndGameResponse> call, Response<IndGameResponse> response) {
                listener.onNextQuestionLoaded(response.body().getResult().getQuestions().get(0));
            }

            @Override
            public void onFailure(Call<IndGameResponse> call, Throwable t) {
                System.out.println("here");
            }
        });
    }

    @Override
    public void checkAnswer(OnFinishListener listener, int questionId, String answer) {
        api.checkAnswer(questionId, answer).enqueue(new Callback<CorrectAnswers>() {
            @Override
            public void onResponse(Call<CorrectAnswers> call, Response<CorrectAnswers> response) {
                listener.onCorrectAnswerLoaded(response.body());
            }

            @Override
            public void onFailure(Call<CorrectAnswers> call, Throwable t) {

            }
        });
    }

    @Override
    public void finishIndividualGame(OnFinishListener listener, int correctAnswers) {
        api.finishGame(correctAnswers).enqueue(new Callback<Question>() {
            @Override
            public void onResponse(Call<Question> call, Response<Question> response) {

            }

            @Override
            public void onFailure(Call<Question> call, Throwable t) {

            }
        });
    }
}
