package com.example.apo2.model;

import com.google.gson.annotations.SerializedName;

public class WeatherResponse {
    @SerializedName("results")
    private Results results;

    // Getters
    public Results getResults() { return results; }
}

