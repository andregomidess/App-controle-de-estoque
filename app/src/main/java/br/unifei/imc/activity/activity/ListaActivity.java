package br.unifei.imc.activity.activity;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.Toast;

import br.unifei.imc.DAO.GamesDAO;
import br.unifei.imc.R;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import br.unifei.imc.activity.adapter.AdapterJogo;
import br.unifei.imc.activity.listener.RecyclerItemClickListener;
import br.unifei.imc.facade.Facade;
import br.unifei.imc.jogos.Games;
import br.unifei.imc.jogos.Jogo;

public class ListaActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private List<Jogo> listaJogos = new ArrayList<>();
    private String plataforma;
    private AdapterJogo adapterJogo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista);

        GamesDAO gamesDAO = new GamesDAO(getApplicationContext());

        recyclerView = findViewById(R.id.recyclerView);
        Bundle dados = getIntent().getExtras();
        plataforma = dados.getString("plataforma");
        listaJogos = gamesDAO.consultar(plataforma);

        carregarListaTarefas();

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
                                AlertDialog.Builder dialog = new AlertDialog.Builder(view.getContext());
                                dialog.setTitle("Escolha uma opção");
                                dialog.setMessage("Você deseja atualizar ou deletar esse jogo?");

                                dialog.setPositiveButton("Atualizar", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        Jogo game = listaJogos.get(position);
                                        Intent intent = new Intent(getApplicationContext(), AtualizaActivity.class);
                                        intent.putExtra("objeto", (Serializable) game);
                                        intent.putExtra("plataforma", plataforma);
                                        startActivity(intent);
                                    }
                                });
                                dialog.setNegativeButton("Deletar", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        Jogo jg = listaJogos.get(position);
                                        Facade facade = new Facade(jg.getNome(),
                                                jg.getValor(), jg.getDescricao(),
                                                jg.getFabricante(), jg.getQtd());
                                        Jogo jogo = facade.inicializa(plataforma);
                                        if(gamesDAO.deletar(jogo, plataforma)){
                                            carregarListaTarefas();
                                            Toast.makeText(getApplicationContext(),
                                                    "Jogo excluido com sucesso",
                                                    Toast.LENGTH_SHORT).show();
                                        }else {
                                            Toast.makeText(getApplicationContext(),
                                                    "Erro ao excluir jogo",
                                                    Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                });
                                dialog.create();
                                dialog.show();

                            }

                            @Override
                            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                            }
                        }
                )
        );

    }
    public void carregarListaTarefas(){

        //Listar tarefas
        GamesDAO dao = new GamesDAO(getApplicationContext());
        listaJogos = dao.consultar(plataforma);

        /*
            Exibe lista de tarefas no Recyclerview
        */

        //Configurar um adapter
        adapterJogo = new AdapterJogo( listaJogos );

        //Configurar Recyclerview
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager( getApplicationContext() );
        recyclerView.setLayoutManager( layoutManager );
        recyclerView.setHasFixedSize(true);
        recyclerView.addItemDecoration(new DividerItemDecoration(getApplicationContext(), LinearLayout.VERTICAL));
        recyclerView.setAdapter(adapterJogo);

    }
}