package br.unifei.imc.activity.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import br.unifei.imc.R;
import br.unifei.imc.jogos.Games;

public class AtualizaActivity extends AppCompatActivity {

    private Button buttonVoltarAtualiza;
    private Button buttonConfirmarAtualiza;
    private TextView textNomeJogoAtualiza, textValorAtualiza, textDescAtualiza, textFabAtualiza,
            textQtdAtualiza;

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
        Games game = (Games) dados.getSerializable("objeto");

        textNomeJogoAtualiza.setText(game.getNome());
        textValorAtualiza.setText(game.getValor().toString());
        textDescAtualiza.setText(game.getDescricao());
        textFabAtualiza.setText(game.getFabricante());
        textQtdAtualiza.setText(Integer.toString(game.getQtd()));



        buttonVoltarAtualiza.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });


    }
}