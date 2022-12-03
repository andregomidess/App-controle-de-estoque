package br.unifei.imc.activity.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import br.unifei.imc.R;

public class MainActivity extends AppCompatActivity {

    private Button buttonRegistra;
    private Button buttonConsulta;
    private Button buttonAtualiza;
    private Button buttonDeleta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonRegistra = findViewById(R.id.buttonRegistrar);
        buttonConsulta = findViewById(R.id.buttonConsultar);
        buttonAtualiza = findViewById(R.id.buttonAtualizar);
        buttonDeleta = findViewById(R.id.buttonDeletar);

        //Setando onclick botão registrar
        buttonRegistra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), RegistraActivity.class);
                startActivity(intent);
            }
        });

        // setando onclick botão consultar
        buttonConsulta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), ConsultaActivity.class);
                startActivity(intent);
            }
        });

        //setando onclick botão atualizar
        buttonAtualiza.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), AtualizaActivity.class);
                startActivity(intent);
            }
        });

        //setando onclick botão deletar
        buttonDeleta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), DeletaActivity.class);
                startActivity(intent);
            }
        });

    }
}