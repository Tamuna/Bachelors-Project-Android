package ge.edu.freeuni.rsr.network;

import ge.edu.freeuni.rsr.individual.game.entity.CorrectAnswers;
import ge.edu.freeuni.rsr.individual.game.entity.Question;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface Api {
    @GET("api/individual/loadNextQuestion/{userId}/{numberOfQuestions?}")
    Call<Question> loadNextQuestion(@Path("userId") int userId, @Path("numberOfQuestions") int numberOfQuestions);

    @GET("api/individual/answer/{questionId}/{currentAnswer}")
    Call<CorrectAnswers> checkAnswer(@Path("questionId") int questionId, @Path("currentAnswer") String currentAnswer);

    @POST("api/individual/finishGame/{userId}/{numberOfCorrectAnswers}")
    Call<Question> finishGame(@Path("userId") int userId, @Path("numberOfQuestions") int numberOfQuestions);
}
