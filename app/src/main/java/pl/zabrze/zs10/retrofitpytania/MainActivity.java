package pl.zabrze.zs10.retrofitpytania;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    private TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = findViewById(R.id.textView);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://my-json-server.typicode.com/markup54/retro_proba/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        JsonPlaceHolderApi jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi.class);
        Call<List<Pytanie>> call = jsonPlaceHolderApi.getPytanie();
        call.enqueue(new Callback<List<Pytanie>>() {
            @Override
            public void onResponse(Call<List<Pytanie>> call, Response<List<Pytanie>> response) {
                if(!response.isSuccessful()) {
                    textView.setText(response.code());
                    return;
                }
                textView.setText("");
                List<Pytanie> pytania = response.body();
                textView.setText(pytania.get(0).getTresc());
            }

            @Override
            public void onFailure(Call<List<Pytanie>> call, Throwable t) {
                textView.setText(t.getMessage());
            }
        });


    }
}