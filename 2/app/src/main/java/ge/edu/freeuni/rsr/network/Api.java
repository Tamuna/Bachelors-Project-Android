package ge.edu.freeuni.rsr.network;

import java.util.List;

import ge.edu.freeuni.rsr.auth.entity.AuthResult;
import ge.edu.freeuni.rsr.auth.entity.Credentials;
import ge.edu.freeuni.rsr.auth.entity.UserResult;
import ge.edu.freeuni.rsr.common.FriendCredentials;
import ge.edu.freeuni.rsr.common.entity.RsrResponse;
import ge.edu.freeuni.rsr.common.entity.User;
import ge.edu.freeuni.rsr.groupchat.FriendsResult;
import ge.edu.freeuni.rsr.groupchat.configuration.NotificationBody;
import ge.edu.freeuni.rsr.individual.game.entity.CorrectAnswers;
import ge.edu.freeuni.rsr.individual.game.entity.IndGameResponse;
import ge.edu.freeuni.rsr.individual.game.entity.Question;
import ge.edu.freeuni.rsr.notifications.entity.Dialog;
import ge.edu.freeuni.rsr.tournaments.create.TournamentConfigBody;
import ge.edu.freeuni.rsr.tournaments.entity.Tournament;
import ge.edu.freeuni.rsr.tournaments.game.ratings.Rating;
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
    Call<RsrResponse<UserResult>> registerAdditionalInfo(@Body Credentials credentials);

    @GET("api/profile/get-friends-list")
    Call<RsrResponse<FriendsResult>> getFriends();

    @POST("api/group/send-notification")
    Call<RsrResponse<String>> sendNotifications(@Body NotificationBody notificationBody);

    @POST("api/group/get-chat-occupants")
    Call<RsrResponse<FriendsResult>> getChatOccupants(@Body NotificationBody notificationBody);

    @POST("api/tour/save-tour")
    Call<RsrResponse<Integer>> saveTourHeader(@Body TournamentConfigBody tournamentConfigBody);

    @POST("api/tour/add-question-to-tour")
    Call<RsrResponse<Integer>> saveSingleQuestion(@Body TournamentConfigBody tournamentConfigBody);

    @GET("api/tour/get-all-tours")
    Call<RsrResponse<List<Tournament>>> getAllTournaments();

    @GET("api/tour/get-selected-tour/{dialogId}")
    Call<RsrResponse<Tournament>> getTournament(@Path("dialogId") int dialogId);

    @POST("api/tour/save-tour-results")
    Call<RsrResponse<String>> saveTourResults(@Body TournamentConfigBody tournamentConfigBody);

    @POST("api/profile/search-user")
    Call<RsrResponse<List<User>>> getFilteredUsers(@Body Credentials body);

    @POST("api/profile/send-friend-request")
    Call<RsrResponse<String>> sendFriendRequest(@Body NotificationBody body);

    @POST("api/profile/response-friend-request")
    Call<RsrResponse<String>> confirmFriendRequest(@Body FriendCredentials body);

    @GET("api/group/get-dialogs")
    Call<RsrResponse<List<Dialog>>> getDialogs();

    @GET("api/tour/get-tournament-results/{tournamentId}")
    Call<RsrResponse<List<Rating>>> getTourRatings(@Path("tournamentId") int tournamentId);
}

