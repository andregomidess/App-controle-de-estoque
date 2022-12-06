package br.unifei.imc.activity.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import br.unifei.imc.DAO.GamesDAO;
import br.unifei.imc.R;
import br.unifei.imc.jogos.Games;
import br.unifei.imc.jogos.Jogo;
import br.unifei.imc.vendas.Caixas;
import br.unifei.imc.vendas.VendaUnitaria;

public class VendasActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    private Button buttonVoltarVendas;
    private Button buttonConfirmarVendas;
    private Button buttonVenda;
    private Button buttonCalcularPreco;
    private String plataforma;
    private ListView listaVendas;
    private TextView textNomeJogoVendas, textViewTotal;
    private List<Jogo> jogosVenda = new ArrayList<>();
    private double valorFinal;
    private List<String> nomeJogosVendas = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vendas);

        buttonVoltarVendas = findViewById(R.id.buttonVoltarVendas);
        buttonConfirmarVendas = findViewById(R.id.buttonConfirmarVendas);
        buttonCalcularPreco = findViewById(R.id.buttonCalcularPreco);
        buttonVenda = findViewById(R.id.buttonVender);
        listaVendas = findViewById(R.id.listaVendas);
        textNomeJogoVendas = findViewById(R.id.textnomeJogoVendas);
        textViewTotal = findViewById(R.id.textViewTotal);


        Spinner spinner = findViewById(R.id.spinnerVendas);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.plataformas,
                android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);
        carregarLista();




        buttonVoltarVendas.setOnClickListener(view -> finish());
        buttonConfirmarVendas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GamesDAO gamesDAO = new GamesDAO(getApplicationContext());
                if (!textNomeJogoVendas.getText().toString().isEmpty()) {
                    if (JogoExiste()){
                        String nomeJogo = textNomeJogoVendas.getText().toString().toLowerCase().trim();
                        Jogo jogo = gamesDAO.consultaVenda(plataforma, nomeJogo);
                        jogosVenda.add(jogo);
                        nomeJogosVendas.add(jogo.getNome());
                        carregarLista();
                        buttonVenda.setEnabled(true);
                        buttonCalcularPreco.setEnabled(true);
                    } else {
                        Toast.makeText(getApplicationContext(), "Esse jogo não está registrado!", Toast.LENGTH_SHORT).show();
                    }

                } else {
                    Toast.makeText(getApplicationContext(), "Preencha o nome do jogo!", Toast.LENGTH_SHORT).show();
                }

            }
        });

        buttonVenda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "Jogos vendidos com sucesso!", Toast.LENGTH_SHORT).show();
                textViewTotal.setText("");
                jogosVenda.clear();
                nomeJogosVendas.clear();
                carregarLista();
            }
        });

        buttonCalcularPreco.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Caixas cx = new Caixas(new ArrayList<>());
                cx.addJogo(new VendaUnitaria(jogosVenda));
                valorFinal = cx.calculaPrecoFinal();
                textViewTotal.setText("Total a pagar: R$ " + Double.toString(valorFinal));
            }
        });
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int i, long l) {
        plataforma = parent.getItemAtPosition(i).toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    public void carregarLista(){

        buttonVenda.setEnabled(false);
        buttonCalcularPreco.setEnabled(false);
        //criar adaptador para a lista
        ArrayAdapter<String> adaptador = new ArrayAdapter<String>(getApplicationContext(),
                android.R.layout.simple_list_item_1, android.R.id.text1, nomeJogosVendas);

        //Adiciona adaptador para a lista
        listaVendas.setAdapter(adaptador);

    }

    public boolean JogoExiste(){
        GamesDAO gamesDAO = new GamesDAO(getApplicationContext());
        List<Jogo> lj = gamesDAO.consultar(plataforma);
        String nomeJogo = textNomeJogoVendas.getText().toString().toLowerCase().trim();
        for (Jogo jogo: lj){
            if (nomeJogo.equals(jogo.getNome())){
                return true;
            }
        }
        return false;
    }


}