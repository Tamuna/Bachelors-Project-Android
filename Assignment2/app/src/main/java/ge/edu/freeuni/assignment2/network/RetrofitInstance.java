package ge.edu.freeuni.assignment2.network;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * created by tamuna
 */
public class RetrofitInstance {
    private static RetrofitInstance retrofitInstance;

    private static Retrofit retrofitCounties;
    private static Retrofit retrofitWeathers;

    private static String BASE_URL_COUNTRIES = "https://restcountries.eu/";
    private static String BASE_URL_WEATHERS = "https://api.apixu.com/";

    public static RetrofitInstance getInstance() {
        if (retrofitInstance == null) {
            retrofitInstance = new RetrofitInstance();
            retrofitCounties = getRetrofitInstance(BASE_URL_COUNTRIES);
            retrofitWeathers = getRetrofitInstance(BASE_URL_WEATHERS);
        }
        return retrofitInstance;
    }

    public Retrofit getRetrofitCounties() {
        return retrofitCounties;
    }

    public Retrofit getRetrofitWeathers() {
        return retrofitWeathers;
    }

    private static Retrofit getRetrofitInstance(String baseUrl) {
        return new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    private RetrofitInstance() {
    }
}
