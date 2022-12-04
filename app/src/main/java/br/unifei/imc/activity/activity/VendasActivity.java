package br.unifei.imc.activity.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import br.unifei.imc.R;

public class VendasActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    private Button buttonVoltarDeleta;
    private Button buttonConfirmarDeleta;
    private String plataforma;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vendas);

        buttonVoltarDeleta = findViewById(R.id.buttonVoltarDeleta);
        buttonConfirmarDeleta = findViewById(R.id.buttonConfirmarDeleta);
        Spinner spinner = findViewById(R.id.spinnerVendas);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.plataformas,
                android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);

        buttonVoltarDeleta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
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