package ge.edu.freeuni.rsr.network;

import java.io.IOException;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;


/*
 * created by tgeldiashvili on 6/18/2019
 */

public class RequestInterceptor implements Interceptor {
    private static String token = "";

    @Override
    public Response intercept(Chain chain) throws IOException {
        HttpUrl url = chain.request().url().newBuilder().build();
        Request request = chain.request().newBuilder()
                .url(url)
                .header("Content-Type", "Application/json")
                .header("X-Requested-With", "XMLHttpRequest")
                .header("Authorization", "Bearer " + token)
                .build();
        Response response = chain.proceed(request);

        return response;
    }

    public static void setToken(String token) {
        RequestInterceptor.token = token;
    }
}
