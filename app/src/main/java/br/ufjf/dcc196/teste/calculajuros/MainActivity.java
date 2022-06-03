package br.ufjf.dcc196.teste.calculajuros;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    public static final int REQUEST_JUROS_SIMPLES = 1;

    EditText editTextValorPresente;
    TextView textViewValorFinal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextValorPresente = findViewById(R.id.editTextValorPresente);
        textViewValorFinal = findViewById(R.id.textViewValorFinal);
    }

    public void jurosSimplesClick(View view){
        try {
            Double valorPresente = Double.parseDouble((editTextValorPresente.getText().toString()));
            Intent intent = new Intent(MainActivity.this,JurosSimplesActivity.class);
            intent.putExtra("valorPresente", valorPresente);
            startActivityForResult(intent, REQUEST_JUROS_SIMPLES);
        }catch (Exception e){
            editTextValorPresente.selectAll();
            editTextValorPresente.requestFocus();
        }
    }
}