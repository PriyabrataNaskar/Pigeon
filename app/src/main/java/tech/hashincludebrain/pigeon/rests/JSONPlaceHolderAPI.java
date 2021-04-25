package tech.hashincludebrain.pigeon.rests;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import tech.hashincludebrain.pigeon.model.ResponseModel;

/**
 * Created by Priyabrata Naskar on 15-04-2021.
 */
public interface JSONPlaceHolderAPI {

    @GET("top-headlines")
    Call<ResponseModel> getNews(@Query("country") String country, @Query("category") String category, @Query("apiKey") String apiKey);
}