package com.example.apo2;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.util.Log;


import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapaFragment extends Fragment implements OnMapReadyCallback {

    private GoogleMap mMap;

    public MapaFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_mapa, container, false);

        // Obtém o SupportMapFragment e notifica quando o mapa estiver pronto para ser usado.
        SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager()
                .findFragmentById(R.id.map);

        if (mapFragment != null) {
            mapFragment.getMapAsync(this);
        }

        return view;
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Requisito: Marcador fixo indicando a cidade de consulta.
        // Usaremos São Paulo (WOEID 455827) como exemplo.
        LatLng cidade = new LatLng(-23.5505, -46.6333); // Coordenadas de São Paulo

        mMap.addMarker(new MarkerOptions()
                .position(cidade)
                .title("Previsão para São Paulo"));

        // Move a câmera para o marcador e aplica um zoom
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(cidade, 10));
    }

    // Dentro da classe MapaFragment
    public void atualizarMapa(String novoWoeid) {
        // Aqui você faria uma busca de coordenadas baseada no WOEID,
        // mas para simplificar, vamos apenas usar um valor fixo ou um ponto central.
        // O ideal seria usar uma API de geocodificação.

        // Como simplificação, você pode apenas forçar a recarga do mapa ou esperar o próximo requisito.
        // Por enquanto, apenas um log:
        Log.d("MAPA", "Solicitada atualização para WOEID: " + novoWoeid);
    }

}
