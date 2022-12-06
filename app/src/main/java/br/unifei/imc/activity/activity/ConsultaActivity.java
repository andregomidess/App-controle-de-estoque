package br.unifei.imc.activity.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;


import br.unifei.imc.R;

public class ConsultaActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private Button buttonVoltarConsulta;
    private Button buttonConfirmarConsulta;
    private String plataforma;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consulta);

        buttonConfirmarConsulta = findViewById(R.id.buttonConfirmarConsullta);
        buttonVoltarConsulta = findViewById(R.id.buttonVoltarConsulta);
        Spinner spinner = findViewById(R.id.textSpinnerConsulta);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.plataformas,
                android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);

        buttonVoltarConsulta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        buttonConfirmarConsulta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), ListaActivity.class);
                intent.putExtra("plataforma", plataforma);
                startActivity(intent);
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
}