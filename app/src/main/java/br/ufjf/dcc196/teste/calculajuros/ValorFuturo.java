package br.ufjf.dcc196.teste.calculajuros;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ValorFuturo extends AppCompatActivity {
    private EditText editTextValorFuturo;

    private TextView textViewResultado;
    private TextView textViewValorPresente;

    private Double valorPresente;
    private Double valorFinal = 0.0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_valor_futuro);

        textViewValorPresente = findViewById(R.id.textViewValorPresente);

        Bundle extras = getIntent().getExtras();
        valorPresente = extras.getDouble("valorPresente");

        textViewValorPresente.setText(valorPresente.toString());

        editTextValorFuturo = findViewById(R.id.editTextValorFuturo);
        textViewResultado = findViewById(R.id.textViewResultado);
    }

    public void calcularRelacao(View view){
        Double valorFuturo;
        Double porcentagem;

        valorFuturo = Double.parseDouble(editTextValorFuturo.getText().toString());

        porcentagem = ((valorFuturo*100)/valorPresente);

        valorFinal = porcentagem;
        textViewResultado.setText(valorFinal.toString() + "%");
    }

    public void retornarClick(View view){
        Intent resultado = new Intent();
        resultado.putExtra("valorFinal", valorFinal);
        setResult(MainActivity.RESULT_RELACAO_VALOR_FUTURO, resultado);
        finish();
    }
}