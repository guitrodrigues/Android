package com.example.guitr.enderecocomapi.br.com.Impl;

import com.example.guitr.enderecocomapi.br.com.entity.Endereco;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.PATCH;
import retrofit2.http.Path;

public interface EnderecoImpl {
    @GET("{numeroCep}/json/")
    Call<Endereco>getCep(@Path("numeroCep") String numeroCep);

}
