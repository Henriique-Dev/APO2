package com.example.apo2;

import androidx.appcompat.widget.Toolbar;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.google.android.material.floatingactionbutton.FloatingActionButton; // Para o FAB
import com.google.zxing.integration.android.IntentIntegrator; // Para iniciar o scanner
import com.google.zxing.integration.android.IntentResult; // Para o resultado do scanner
import android.widget.Toast; // Para a mensagem de notificação
import android.util.Log;
import androidx.fragment.app.Fragment;





public class MainActivity extends AppCompatActivity {

    private TabLayout tabLayout;
    private ViewPager2 viewPager;
    private ViewPagerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Dentro do onCreate() da MainActivity

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab_qrcode);
        fab.setOnClickListener(v -> {
            // Inicia o scanner Zxing
            new IntentIntegrator(this)
                    .setOrientationLocked(false) // Permite rotação
                    .setPrompt("Aponte para o QR Code da cidade")
                    .initiateScan();
        });


        // 1. Inicializar os componentes do layout
        tabLayout = findViewById(R.id.tab_layout); // Certifique-se de ter este ID no seu XML
        viewPager = findViewById(R.id.view_pager); // Certifique-se de ter este ID no seu XML

        // 2. Criar e configurar o Adapter
        adapter = new ViewPagerAdapter(this);
        viewPager.setAdapter(adapter);

        // 3. Conectar o TabLayout ao ViewPager2
        new TabLayoutMediator(tabLayout, viewPager,
                (tab, position) -> {
                    switch (position) {
                        case 0:
                            tab.setText("Previsão");
                            break;
                        case 1:
                            tab.setText("Mapa");
                            break;
                    }
                }
        ).attach();
    }

    // 4. Implementar o Menu na AppBar
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu); // Crie o arquivo main_menu.xml
        return true;
    }

    // Já está correto, apenas para referência:
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_sobre) {
            startActivity(new Intent(this, SobreActivity.class));
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if(result != null) {
            if(result.getContents() == null) {
                // Usuário cancelou
                Toast.makeText(this, "Escaneamento Cancelado", Toast.LENGTH_LONG).show();
            } else {
                // Conteúdo do QR Code lido (deve ser o WOEID ou CID da nova cidade)
                String novoWoeid = result.getContents();
                Toast.makeText(this, "Nova Cidade: " + novoWoeid, Toast.LENGTH_LONG).show();

                // **Ação:** Chamar um método para atualizar a previsão e o mapa
                atualizarPrevisaoEMapa(novoWoeid);
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }


    private void atualizarPrevisaoEMapa(String novoWoeid) {
        Log.d("QR_CODE", "WOEID lido: " + novoWoeid);

        // Obtém o Fragment atual do ViewPager2
        Fragment previsaoFragment = adapter.createFragment(0); // Posição 0 é a Previsão
        Fragment mapaFragment = adapter.createFragment(1); // Posição 1 é o Mapa

        if (previsaoFragment instanceof PrevisaoFragment) {
            ((PrevisaoFragment) previsaoFragment).atualizarCidade(novoWoeid);
        }

        if (mapaFragment instanceof MapaFragment) {
            ((MapaFragment) mapaFragment).atualizarMapa(novoWoeid);
        }
    }

}


