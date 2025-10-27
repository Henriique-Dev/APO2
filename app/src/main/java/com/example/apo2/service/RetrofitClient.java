package com.example.apo2.service; // <-- CORREÇÃO AQUI!

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import com.example.apo2.service.WeatherServices; // Importa a interface do mesmo pacote

public class RetrofitClient {
    private static final String BASE_URL = "https://api.hgbrasil.com/";
    private static Retrofit retrofit = null;

    public static WeatherServices getClient( ) {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit.create(WeatherServices.class);
    }
}

