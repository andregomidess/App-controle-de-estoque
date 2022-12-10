package br.unifei.imc.activity.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.unifei.imc.DAO.GamesDAO;
import br.unifei.imc.R;
import br.unifei.imc.jogos.Jogo;
import br.unifei.imc.vendas.Caixas;
import br.unifei.imc.vendas.VendaUnitaria;

public class VendasActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    private Button buttonVoltarVendas;
    private Button buttonConfirmarVendas;
    private Button buttonVenda;
    private Button buttonCriarBox;
    private String plataforma;
    private ListView listaVendas;
    private TextView textNomeJogoVendas, textViewTotal, textViewBox;
    private Caixas cx = new Caixas(new ArrayList<>());
    private Caixas cxFinal = new Caixas(new ArrayList<>());
    private double valorBox;
    private double valorFinal;
    private List<String> nomeJogosVendas = new ArrayList<>();
    private List<String> precoJogoVendas = new ArrayList<>();
    private Map<String, Integer> contador = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vendas);

        buttonVoltarVendas = findViewById(R.id.buttonVoltarVendas);
        buttonConfirmarVendas = findViewById(R.id.buttonConfirmarVendas);
        buttonCriarBox = findViewById(R.id.buttonCriarBox);
        buttonVenda = findViewById(R.id.buttonVender);
        listaVendas = findViewById(R.id.listaVendas);
        textNomeJogoVendas = findViewById(R.id.textnomeJogoVendas);
        textViewTotal = findViewById(R.id.textViewTotal);
        textViewBox = findViewById(R.id.textViewBox);


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
                        cx.addJogo(new VendaUnitaria(jogo));
                        if(contaQtd(jogo)){
                            nomeJogosVendas.add(jogo.getNome());
                            precoJogoVendas.add(Double.toString(jogo.getValor()));
                            carregarLista();
                            buttonCriarBox.setEnabled(true);
                        }else {
                            Toast.makeText(getApplicationContext(), "Quantidade insuficiente em estoque",
                                    Toast.LENGTH_SHORT).show();
                        }

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
                Toast.makeText(getApplicationContext(), "Jogos vendidos com sucesso!",
                        Toast.LENGTH_SHORT).show();
                textViewTotal.setText("Total a pagar: ");
                textViewBox.setText("Box 1");
                nomeJogosVendas.clear();
                cx = new Caixas(new ArrayList<>());
                carregarLista();
            }
        });

        buttonCriarBox.setOnClickListener(new View.OnClickListener() {
            Integer num=2;
            @Override
            public void onClick(View view) {
                cxFinal.addJogo(cx);
                //Calculando o preço da Box
                valorBox = cx.calculaPrecoFinal();
                //Calculando o preço Final
                valorFinal = cxFinal.calculaPrecoFinal();
                textViewTotal.setText("Box: R$ " + Double.toString(valorBox)
                        +" Preço Total: R$ " +Double.toString(valorFinal));
                textViewBox.setText("Box "+Integer.toString(num));
                GamesDAO gamesDAO = new GamesDAO(getApplicationContext());
                gamesDAO.atualizaQtdVendas(contador, plataforma);
                Toast.makeText(getApplicationContext(), "Box criada com Sucesso!",
                        Toast.LENGTH_SHORT).show();
                carregarLista();
                nomeJogosVendas.clear();
                contador.clear();
                buttonVenda.setEnabled(true);
                cx = new Caixas(new ArrayList<>());
                num++;
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

    public void carregarLista() {

        buttonVenda.setEnabled(false);
        buttonCriarBox.setEnabled(false);
        //criar adaptador para a lista
        ArrayAdapter<String> adaptador = new ArrayAdapter<String>(getApplicationContext(),
                android.R.layout.simple_list_item_2, android.R.id.text1, nomeJogosVendas) {
            @Override
            public View getView(int position,
                                View convertView, ViewGroup parent) {
                View view = super.getView(position, convertView, parent);

                TextView text1 = (TextView) view.findViewById(android.R.id.text1);
                TextView text2 = (TextView) view.findViewById(android.R.id.text2);

                text1.setText(StringUtils.capitalize(nomeJogosVendas.get(position)));

                text2.setText("R$ " + precoJogoVendas.get(position));

                return view;
            }

        };
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

    public boolean contaQtd (Jogo jogo){
        if (!contador.containsKey(jogo.getNome())){
            contador.put(jogo.getNome(), jogo.getQtd()-1);
        }else {
            Integer qtd = contador.get(jogo.getNome());
            Integer qtdNova = qtd - 1;
            if (qtdNova < 0){
                return false;
            }else {
                contador.replace(jogo.getNome(), qtdNova);
            }
        }
        return true;
    }


}