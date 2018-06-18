package hr.parkovihrvatske.network;

import android.support.annotation.NonNull;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Damjan Miloševski on 05.6.2018.
 * Project: ParkoviHrvatske
 * Copyright: Corvus Info d.o.o.
 * Buzinski prilaz 10, 10010 Zagreb, HR
 */
public class RetrofitBuilder {
    private final static OkHttpClient client = buildClient();
    private final static Retrofit retrofit = buildRetrofit(client);

    /**
     * Creates OkHttpClient
     *
     * @return OkHttpClient
     */
    private static OkHttpClient buildClient() {
        final OkHttpClient.Builder builder = new OkHttpClient.Builder()
                .addInterceptor(new Interceptor() {
                    @Override
                    public Response intercept(@NonNull Chain chain) throws IOException {
                        Request request = chain.request();
                        Request.Builder builder = request.newBuilder();
                        request = builder.build();
                        return chain.proceed(request);
                    }
                });
        return builder.build();
    }
    /**
     * Creates Retrofit call for default server ip & port
     *
     * @param client OkHttpClient to use
     * @return Retrofit object
     */
    private static Retrofit buildRetrofit(OkHttpClient client) {
        return new Retrofit.Builder()
                .baseUrl("http://capacityapi.ekupi.eu")
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }
    public static <T> T createService(Class<T> service){
        return retrofit.create(service);
    }
}
