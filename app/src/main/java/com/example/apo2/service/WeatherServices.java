package com.example.apo2.service; // <-- CORREÇÃO AQUI!

import com.example.apo2.model.WeatherResponse; // Importe a classe de resposta
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface WeatherServices { // Use o nome exato da sua interface
    // Define o método HTTP GET para o endpoint 'weather'
    @GET("weather"  )
    Call<WeatherResponse> getCurrentWeather(
            @Query("woeid") String woeid,
            @Query("key") String apiKey
    );
}
