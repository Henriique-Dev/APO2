package com.example.apo2;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class ViewPagerAdapter extends FragmentStateAdapter {

    public ViewPagerAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 0:
                // Instanciação correta do Fragment
                return new PrevisaoFragment();
            case 1:
                // Instanciação do MapaFragment (assumindo que ele está no mesmo pacote)
                return new MapaFragment();
            default:
                return new PrevisaoFragment();
        }
    }

    @Override
    public int getItemCount() {
        return 2; // Retorna o número de abas (Previsão e Mapa)
    }

}

