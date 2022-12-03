package br.unifei.imc.activity.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import br.unifei.imc.R;

public class DeletaActivity extends AppCompatActivity {

    private Button buttonVoltarDeleta;
    private Button buttonConfirmarDeleta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deleta);

        buttonVoltarDeleta = findViewById(R.id.buttonVoltarDeleta);
        buttonConfirmarDeleta = findViewById(R.id.buttonConfirmarDeleta);
        buttonVoltarDeleta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}