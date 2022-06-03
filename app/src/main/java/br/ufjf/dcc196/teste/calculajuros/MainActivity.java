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

    public static final int REQUEST_JUROS_COMPOSTOS = 2;

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

    public void jurosCompostosClick(View view){
        try {
            Double valorPresente = Double.parseDouble((editTextValorPresente.getText().toString()));
            Intent intent = new Intent(MainActivity.this,JurosCompostosActivity.class);
            intent.putExtra("valorPresente", valorPresente);
            startActivityForResult(intent, REQUEST_JUROS_COMPOSTOS);
        }catch (Exception e){
            editTextValorPresente.selectAll();
            editTextValorPresente.requestFocus();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_OK){
            Bundle extras = data.getExtras();
            Double valorFinal = extras.getDouble("valorFinal");
            if(requestCode == REQUEST_JUROS_SIMPLES){
                textViewValorFinal.setText("Simples: R$"+valorFinal.toString());
            }
            else if(requestCode == REQUEST_JUROS_COMPOSTOS){
                textViewValorFinal.setText("Compostos: R$"+valorFinal.toString());
            }
        }
    }
}