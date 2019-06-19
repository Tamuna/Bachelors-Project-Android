package ge.edu.freeuni.rsr.network;

import java.util.List;

import ge.edu.freeuni.rsr.auth.entity.Credentials;
import ge.edu.freeuni.rsr.auth.entity.AuthResponse;
import ge.edu.freeuni.rsr.individual.game.entity.CorrectAnswers;
import ge.edu.freeuni.rsr.individual.game.entity.Question;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface Api {
    @GET("api/individual/getRandomQuestion/{userId}")
    Call<List<Question>> loadNextQuestion(@Path("userId") int userId);

    @GET("api/individual/answer/{questionId}/{currentAnswer}")
    Call<CorrectAnswers> checkAnswer(@Path("questionId") int questionId, @Path("currentAnswer") String currentAnswer);

    @POST("api/individual/finishGame/{userId}/{numberOfCorrectAnswers}")
    Call<Question> finishGame(@Path("userId") int userId, @Path("numberOfQuestions") int numberOfQuestions);

    @POST("api/auth/register")
    Call<AuthResponse> register(@Body Credentials credentials);

    @POST("api/auth/login")
    Call<AuthResponse> login(@Body Credentials credentials);

}
