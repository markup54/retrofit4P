package pl.zabrze.zs10.retrofitpytania;


import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface JsonPlaceHolderApi {

    @GET("pytania")
    public Call<List<Pytanie>>getPytanie();
}
