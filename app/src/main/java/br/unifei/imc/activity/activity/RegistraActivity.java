package br.unifei.imc.activity.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import br.unifei.imc.R;
import br.unifei.imc.facade.RegistraJogo;

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

        String nome = textNomeJogoReg.getText().toString();
        String valor = textValorReg.getText().toString();
        Double valorD = Double.parseDouble(valor);
        String desc = textDescReg.getText().toString();
        String fab = textFabReg.getText().toString();
        String qtd = textQtdReg.getText().toString();
        int qtdD = Integer.parseInt(qtd);
        RegistraJogo registra = new RegistraJogo(nome, valorD, desc, fab, qtdD);
        registra.registrar(escolha, getApplicationContext());

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int i, long l) {
        escolha = parent.getItemAtPosition(i).toString();

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}