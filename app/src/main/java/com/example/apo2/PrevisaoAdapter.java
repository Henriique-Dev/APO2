package com.example.apo2; // Mantenha este pacote se for onde ele está

import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.apo2.model.Forecast; // Importe a classe Forecast
import com.example.apo2.R; // Importe o R para acessar os recursos

import java.util.List;

public class PrevisaoAdapter extends RecyclerView.Adapter<PrevisaoAdapter.ViewHolder> {

    private final List<Forecast> mValues;

    // O construtor agora aceita uma lista de Forecast
    public PrevisaoAdapter(List<Forecast> items) {
        mValues = items;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // Infla o seu layout de item (item_previsao.xml)
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_previsao, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        Forecast item = mValues.get(position);

        // Preenche os dados usando os getters da classe Forecast
        holder.diaSemana.setText(item.getWeekday());
        holder.data.setText(item.getDate());
        holder.temperatura.setText(item.getMin() + "°C / " + item.getMax() + "°C");
        holder.descricao.setText(item.getDescription());
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        // Declare os TextViews do seu item_previsao.xml
        public final TextView diaSemana;
        public final TextView data;
        public final TextView temperatura;
        public final TextView descricao;

        public ViewHolder(View view) {
            super(view);
            // Encontre os IDs no seu layout item_previsao.xml
            diaSemana = view.findViewById(R.id.text_dia_semana);
            data = view.findViewById(R.id.text_data);
            temperatura = view.findViewById(R.id.text_temperatura);
            descricao = view.findViewById(R.id.text_descricao);
        }
    }
}
