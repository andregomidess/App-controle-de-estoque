package br.unifei.imc.activity.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import br.unifei.imc.DAO.GamesDAO;
import br.unifei.imc.R;
import br.unifei.imc.facade.Facade;
import br.unifei.imc.jogos.Jogo;

public class RegistraActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private Button buttonVoltarRegistra;
    private Button buttonConfirmarRegistra;
    private TextView textPlataformaReg, textNomeJogoReg, textValorReg, textDescReg, textFabReg, textQtdReg;
    private String escolha;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registra);

        buttonVoltarRegistra = findViewById(R.id.buttonVoltarRegistra);
        buttonConfirmarRegistra = findViewById(R.id.buttonConfirmarRegistra);
        textNomeJogoReg = findViewById(R.id.textNomeJogoReg);
        textValorReg = findViewById(R.id.textValorReg);
        textDescReg = findViewById(R.id.textDescReg);
        textFabReg = findViewById(R.id.textFabReg);
        textQtdReg = findViewById(R.id.textQtdReg);
        Spinner spinner = findViewById(R.id.textSpinnerAtualiza);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.plataformas,
                android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);


        buttonVoltarRegistra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }

    public void reg(View view){

        if (verificaCampoVazio()) {
            if (verificaExistencia()) {
                String nome = textNomeJogoReg.getText().toString().toLowerCase().trim();
                String valor = textValorReg.getText().toString();
                Double valorD = Double.parseDouble(valor);
                String desc = textDescReg.getText().toString();
                String fab = textFabReg.getText().toString();
                String qtd = textQtdReg.getText().toString();
                int qtdD = Integer.parseInt(qtd);
                Facade registra = new Facade(nome, valorD, desc, fab, qtdD);
                registra.registrar(escolha, getApplicationContext());
                Toast.makeText(getApplicationContext(), "Jogo registrado com sucesso",
                        Toast.LENGTH_SHORT).show();
                finish();
            }
        }

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int i, long l) {
        escolha = parent.getItemAtPosition(i).toString();

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    public boolean verificaCampoVazio(){
        String nome = textNomeJogoReg.getText().toString();
        String valor = textValorReg.getText().toString();
        String desc = textDescReg.getText().toString();
        String fab = textFabReg.getText().toString();
        String qtd = textQtdReg.getText().toString();
        if (nome.isEmpty() || valor.isEmpty() || desc.isEmpty() || fab.isEmpty() || qtd.isEmpty()){
            Toast.makeText(getApplicationContext(),
                    "Erro ao registrar. Por favor não deixe campos vazios!",
                    Toast.LENGTH_SHORT).show();
            return false;
        }else {
            return true;
        }
    }

    public boolean verificaExistencia(){
        String nome = textNomeJogoReg.getText().toString();
        GamesDAO gamesDAO = new GamesDAO(getApplicationContext());
        List<Jogo> listaJogos;
        listaJogos = gamesDAO.consultar(escolha);
        for (Jogo p : listaJogos) {
            if (p.getNome().toLowerCase(Locale.ROOT).equals(nome.toLowerCase(Locale.ROOT))) {
                Toast.makeText(getApplicationContext(), "Esse jogo já está registrado!",
                        Toast.LENGTH_SHORT).show();
                return false;
            }
        }
        return true;
    }

}