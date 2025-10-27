package com.example.apo2.model;

import com.google.gson.annotations.SerializedName;
import java.util.List;

public class Results {
    // Adicione outros campos que você possa querer usar, como city_name, temp, etc.
    @SerializedName("city_name")
    private String cityName;

    @SerializedName("forecast")
    private List<Forecast> forecast;

    // Getters
    public String getCityName() { return cityName; }
    public List<Forecast> getForecast() { return forecast; }
}
