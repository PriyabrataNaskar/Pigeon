package tech.hashincludebrain.pigeon.rests;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import tech.hashincludebrain.pigeon.Constants;

/**
 * Created by Priyabrata Naskar on 19-04-2021.
 */
public class APIClient {
    public static final String BASE_URL = "";
    private static Retrofit retrofit = null;

    public static Retrofit getRetrofit(){
        if (retrofit==null){
            retrofit = new Retrofit.Builder().
                    baseUrl(Constants.BASE_URL).
                    addConverterFactory(GsonConverterFactory.create()).build();
        }
        return retrofit;
    }
}
