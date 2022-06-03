package br.ufjf.dcc196.teste.calculajuros;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class JurosCompostosActivity extends AppCompatActivity {

    private Double valorPresente;
    private Double valorFinal;

    private EditText editTextTaxaDeJuros;
    private EditText editTextPeriodos;

    private TextView textViewValorPresente;
    private TextView textViewResultado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_juros_compostos);


        Bundle extras = getIntent().getExtras();
        valorPresente = extras.getDouble("valorPresente");

        textViewValorPresente = findViewById(R.id.textViewValorPresente);
        textViewValorPresente.setText(valorPresente.toString());

        editTextTaxaDeJuros = findViewById(R.id.editTextTaxaDeJuros);
        editTextPeriodos = findViewById(R.id.editTextPeriodos);
        textViewResultado = findViewById(R.id.textViewResultado);
    }

    public void calcularClick(View view) {
        Double taxaDeJuros;
        Integer periodos;
        taxaDeJuros = Double.parseDouble(editTextTaxaDeJuros.getText().toString()) / 100.0;
        periodos = Integer.parseInt(editTextPeriodos.getText().toString());
        valorFinal = valorPresente * Math.pow(1 + taxaDeJuros, periodos);
        textViewResultado.setText(valorFinal.toString());

    }
}