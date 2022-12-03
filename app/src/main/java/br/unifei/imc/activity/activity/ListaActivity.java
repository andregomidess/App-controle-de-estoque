package br.unifei.imc.activity.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.Toast;

import br.unifei.imc.DAO.GamesDAO;
import br.unifei.imc.R;


import java.util.ArrayList;
import java.util.List;

import br.unifei.imc.activity.adapter.AdapterJogo;
import br.unifei.imc.activity.listener.RecyclerItemClickListener;
import br.unifei.imc.arquivo.LerArquivo;
import br.unifei.imc.jogos.Games;

public class ListaActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private List<Games> listaJogos = new ArrayList<>();
    private List<Games> listaJogos2 = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista);

        GamesDAO gamesDAO = new GamesDAO(getApplicationContext());

        recyclerView = findViewById(R.id.recyclerView);
        Bundle dados = getIntent().getExtras();
        String plataforma = dados.getString("plataforma");
        listaJogos = gamesDAO.consultar(plataforma);

        // config adapter
        AdapterJogo adapterJogo = new AdapterJogo(listaJogos);
        // config RecyclerView
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayout.VERTICAL));
        recyclerView.setAdapter(adapterJogo);

        // evenro click
        recyclerView.addOnItemTouchListener(
                new RecyclerItemClickListener(
                        getApplicationContext(),
                        recyclerView,
                        new RecyclerItemClickListener.OnItemClickListener() {
                            @Override
                            public void onItemClick(View view, int position) {

                            }

                            @Override
                            public void onLongItemClick(View view, int position) {
                                Games game = listaJogos.get(position);
                                Intent intent = new Intent(getApplicationContext(), AtualizaActivity.class);
                                intent.putExtra("objeto", game);
                                intent.putExtra("plataforma", plataforma);
                                startActivity(intent);
                            }

                            @Override
                            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                            }
                        }
                )
        );

    }
}