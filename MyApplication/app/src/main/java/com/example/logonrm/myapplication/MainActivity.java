package com.example.logonrm.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import java.util.List;
import br.com.fiap.entity.Ator;
import br.com.fiap.util.StarWarsAllAtors;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    private String nomes="";
    private TextView tela;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        retornaStringAtores();
    }

    public void mudaString(View v){
        retornaStringAtores();
        tela = (TextView) findViewById(R.id.tela);
        tela.setText(nomes);
    }

    public void zeraString(View v){
        nomes="";
        tela.setText("");
        retornaStringAtores();
    }

    public String retornaStringAtores(){

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://demo9256607.mockable.io/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        StarWarsAllAtors api = retrofit.create(StarWarsAllAtors.class);

        api.getAllAtors().enqueue(new Callback<List<Ator>>() {
            @Override
            public void onResponse(Call<List<Ator>> call, Response<List<Ator>> response) {

                List<Ator> listaAtores = response.body();
                for (Ator a : listaAtores) {
                    nomes += a.getName().toString()+" | ";
                }
                    nomes +="\n";
                    Log.i("Nomes",nomes);
            }

            @Override
            public void onFailure(Call<List<Ator>> call, Throwable t) {
                Log.i("Erro", t.getMessage());
            }
        });
        return nomes;
    }
}

