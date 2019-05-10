package ge.edu.freeuni.rsr.network;

import ge.edu.freeuni.rsr.individual.model.Question;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface Api {

    @GET("api/questions/{number}")
    Call<Question> getRandomQuestions(@Path("number") int number);
}
