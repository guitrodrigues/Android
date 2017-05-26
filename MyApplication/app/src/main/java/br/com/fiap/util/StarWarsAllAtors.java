package br.com.fiap.util;

import java.util.List;

import br.com.fiap.entity.Ator;
import retrofit2.Call;
import retrofit2.http.GET;

public interface StarWarsAllAtors {
    @GET ("starwars")
    Call<List<Ator>>getAllAtors();
}
