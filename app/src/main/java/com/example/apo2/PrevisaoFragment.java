package com.example.apo2; // Pacote principal do Fragment

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

// Imports de Classes de Modelo (dentro do pacote model)
import com.example.apo2.model.Forecast;
import com.example.apo2.model.WeatherResponse;

// Imports de Classes de Serviço (dentro do pacote service)
import com.example.apo2.service.RetrofitClient;
import com.example.apo2.service.WeatherServices;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PrevisaoFragment extends Fragment {

    private RecyclerView recyclerView;
    private PrevisaoAdapter adapter;
    private List<Forecast> listaPrevisoes = new ArrayList<>();
    private String woeidAtual = "455827"; // WOEID padrão (São Paulo)

    public PrevisaoFragment() {
        // Construtor vazio obrigatório
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_item_list, container, false);

        recyclerView = view.findViewById(R.id.list);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        adapter = new PrevisaoAdapter(listaPrevisoes);
        recyclerView.setAdapter(adapter);

        // Carrega os dados iniciais
        carregarDadosPrevisao(woeidAtual);

        return view;
    }

    // Método público chamado pela MainActivity (QR Code)
    public void atualizarCidade(String novoWoeid) {
        this.woeidAtual = novoWoeid;
        carregarDadosPrevisao(novoWoeid);
    }

    // Método principal que faz a requisição à API, agora aceita o WOEID
    private void carregarDadosPrevisao(String woeid) {
        WeatherServices service = RetrofitClient.getClient();

        Call<WeatherResponse> call = service.getCurrentWeather(woeid, null);

        call.enqueue(new Callback<WeatherResponse>() {
            @Override
            public void onResponse(Call<WeatherResponse> call, Response<WeatherResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    listaPrevisoes.clear();

                    if (response.body().getResults() != null && response.body().getResults().getForecast() != null) {
                        listaPrevisoes.addAll(response.body().getResults().getForecast());
                        adapter.notifyDataSetChanged();
                    } else {
                        Toast.makeText(getContext(), "Nenhuma previsão encontrada.", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Log.e("API_CALL", "Erro ao carregar dados: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<WeatherResponse> call, Throwable t) {
                Log.e("API_CALL", "Erro de conexão: " + t.getMessage());
            }
        });
    }
}

