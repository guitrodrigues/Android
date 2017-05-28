package com.example.guitr.enderecocomapi;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.guitr.enderecocomapi.br.com.Impl.EnderecoImpl;
import com.example.guitr.enderecocomapi.br.com.entity.Endereco;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
        private Endereco endereco;
        private EditText inputCEP;
        private TextView outputEndereco;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        inputCEP = (EditText)findViewById(R.id.TinputCep);
        outputEndereco = (TextView)findViewById(R.id.ToutputEndereco);
    }

    public void enderecoNaTela(View v){
        String cep = inputCEP.getText().toString();

        if(cep.length()<8){
            Toast.makeText(this, "O CEP deve conter 8 numeros", Toast.LENGTH_SHORT).show();
        }else{
            retornaEndereco(cep);
        }
    }

    public void retornaEndereco(String cep){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://viacep.com.br/ws/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

            EnderecoImpl api = retrofit.create(EnderecoImpl.class);

            api.getCep(cep).enqueue(new Callback<Endereco>() {

                @Override
                public void onResponse(Call<Endereco> call, Response<Endereco> response) {
                    endereco = response.body();

                    if(endereco.getCep() != null){
                        outputEndereco.setText(endereco.toString());
                    }else{
                        outputEndereco.setText("CEP não encontrado");
                    }
                }

                @Override
                public void onFailure(Call<Endereco> call, Throwable t) {
                }
            });
    }

}
