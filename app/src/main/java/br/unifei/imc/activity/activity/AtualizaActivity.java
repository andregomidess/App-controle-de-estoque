package br.unifei.imc.activity.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import br.unifei.imc.R;
import br.unifei.imc.facade.Facade;
import br.unifei.imc.jogos.Jogo;

public class AtualizaActivity extends AppCompatActivity {

    private Button buttonVoltarAtualiza;
    private Button buttonConfirmarAtualiza;
    private TextView textNomeJogoAtualiza, textValorAtualiza, textDescAtualiza, textFabAtualiza,
            textQtdAtualiza;
    private Jogo game;
    private String plataforma;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_atualiza);
        buttonConfirmarAtualiza = findViewById(R.id.buttonConfirmarAtualiza);
        buttonVoltarAtualiza = findViewById(R.id.buttonVoltarAtualiza);
        textNomeJogoAtualiza = findViewById(R.id.textNomeJogoAtualiza);
        textValorAtualiza = findViewById(R.id.textValorAtualiza);
        textDescAtualiza = findViewById(R.id.textDescAtualiza);
        textFabAtualiza = findViewById(R.id.textFabAtualiza);
        textQtdAtualiza = findViewById(R.id.textQtdAtualiza);
        Bundle dados = getIntent().getExtras();
        game = (Jogo) dados.getSerializable("objeto");
        plataforma = dados.getString("plataforma");
        textNomeJogoAtualiza.setEnabled(false);

        //puxando informação do objeto q vai ser editado
        if (game != null) {
            textNomeJogoAtualiza.setText(game.getNome());
            textValorAtualiza.setText(game.getValor().toString());
            textDescAtualiza.setText(game.getDescricao());
            textFabAtualiza.setText(game.getFabricante());
            textQtdAtualiza.setText(Integer.toString(game.getQtd()));
        }


        buttonVoltarAtualiza.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        buttonConfirmarAtualiza.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String valor = textValorAtualiza.getText().toString();
                String desc = textDescAtualiza.getText().toString();
                String fab = textFabAtualiza.getText().toString();
                String qtd = textQtdAtualiza.getText().toString();
                if (verificaCampoIgual()){
                    if(verificaCampoVazio()){
                        Facade facade = new Facade(game.getNome(), Double.parseDouble(valor),
                                desc, fab, Integer.parseInt(qtd));
                        facade.atualiza(plataforma, getApplicationContext());
                    }
                }

            }
        });


    }

    public boolean verificaCampoIgual(){
        String valor = textValorAtualiza.getText().toString();
        String desc = textDescAtualiza.getText().toString();
        String fab = textFabAtualiza.getText().toString();
        String qtd = textQtdAtualiza.getText().toString();
        if (valor.equals(game.getValor().toString()) && desc.equals(game.getDescricao()) &&
                fab.equals(game.getFabricante()) && qtd.equals(Integer.toString(game.getQtd()))){
            Toast.makeText(getApplicationContext(),
                    "Erro ao atualizar. Por favor altere o campo desejado!",
                    Toast.LENGTH_SHORT).show();
            return false;
        }else {
            return true;
        }
    }

    public boolean verificaCampoVazio(){
        String valor = textValorAtualiza.getText().toString();
        String desc = textDescAtualiza.getText().toString();
        String fab = textFabAtualiza.getText().toString();
        String qtd = textQtdAtualiza.getText().toString();
        if (valor.isEmpty() || desc.isEmpty() || fab.isEmpty() || qtd.isEmpty()){
            Toast.makeText(getApplicationContext(),
                    "Erro ao atualizar. Por favor não deixe campos vazios!",
                    Toast.LENGTH_SHORT).show();
            return false;
        }else {
            return true;
        }
    }
}