package ge.edu.freeuni.rsr.network;

import ge.edu.freeuni.rsr.auth.entity.Credentials;
import ge.edu.freeuni.rsr.auth.entity.AuthResult;
import ge.edu.freeuni.rsr.common.entity.RsrResponse;
import ge.edu.freeuni.rsr.auth.entity.UserResult;
import ge.edu.freeuni.rsr.groupchat.FriendsResult;
import ge.edu.freeuni.rsr.individual.game.entity.CorrectAnswers;
import ge.edu.freeuni.rsr.individual.game.entity.IndGameResponse;
import ge.edu.freeuni.rsr.individual.game.entity.Question;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface Api {
    @GET("api/individual/get-random-question")
    Call<IndGameResponse> loadNextQuestion();

    @GET("api/individual/check-answer/{questionId}/{currentAnswer}")
    Call<CorrectAnswers> checkAnswer(@Path("questionId") int questionId, @Path("currentAnswer") String currentAnswer);

    @POST("api/individual/finish-game/{numberOfCorrect}")
    Call<Question> finishGame(@Path("numberOfCorrect") int numberOfQuestions);

    @POST("api/auth/register")
    Call<RsrResponse<AuthResult>> register(@Body Credentials credentials);

    @POST("api/auth/login")
    Call<RsrResponse<AuthResult>> login(@Body Credentials credentials);

    @POST("api/auth/logout")
    Call<RsrResponse> logout(@Body Credentials credentials);

    @GET("api/auth/user")
    Call<RsrResponse<UserResult>> getUser();

    @POST("api/auth/register-chat-id")
    Call<Object> registerChatId(@Body Credentials credentials);

    @GET("api/profile/get-friends-list")
    Call<RsrResponse<FriendsResult>> getFriends();

}
