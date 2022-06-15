package br.ufjf.dcc196.teste.calculajuros;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    public static final int RESULT_JUROS_SIMPLES = 1;
    public static final int RESULT_JUROS_COMPOSTOS = 2;
    public static final int RESULT_RELACAO_VALOR_FUTURO = 3;

    ActivityResultLauncher<Intent> laucher;
    EditText editTextValorPresente;
    TextView textViewValorFinal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editTextValorPresente = findViewById(R.id.editTextValorPresente);
        textViewValorFinal = findViewById(R.id.textViewValorFinal);

        laucher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        Double valorFInal;
                        Double porcentagem;
                        Bundle extras;

                        switch (result.getResultCode()){
                            case RESULT_JUROS_SIMPLES:
                                extras = result.getData().getExtras();
                                valorFInal = extras.getDouble("valorFinal");
                                porcentagem = extras.getDouble("porcentagem");
                                textViewValorFinal.setText("Simples: R$" + valorFInal.toString()+"\nRelacao: " + porcentagem + "%");
                                break;
                            case RESULT_JUROS_COMPOSTOS:
                                extras = result.getData().getExtras();
                                valorFInal = extras.getDouble("valorFinal");
                                porcentagem = extras.getDouble("porcentagem");
                                textViewValorFinal.setText("Compostos: R$" + valorFInal.toString()+"\nRelacao: " + porcentagem + "%");
                                break;
                            case RESULT_RELACAO_VALOR_FUTURO:
                                extras = result.getData().getExtras();
                                valorFInal = extras.getDouble("valorFinal");
                                textViewValorFinal.setText("Relacao: " + valorFInal.toString() + "%");
                                break;

                        }
                    }
                }
        );
    }

    public void jurosSimplesClick(View view){
        try {
            Double valorPresente = Double.parseDouble((editTextValorPresente.getText().toString()));
            Intent intent = new Intent(MainActivity.this,JurosSimplesActivity.class);
            intent.putExtra("valorPresente", valorPresente);
            //startActivityForResult(intent, REQUEST_JUROS_SIMPLES);
            laucher.launch(intent);
        }catch (Exception e){
            e.printStackTrace();
            editTextValorPresente.selectAll();
            editTextValorPresente.requestFocus();
        }
    }

    public void jurosCompostosClick(View view){
        try {
            Double valorPresente = Double.parseDouble((editTextValorPresente.getText().toString()));
            Intent intent = new Intent(MainActivity.this,JurosCompostosActivity.class);
            intent.putExtra("valorPresente", valorPresente);
            //startActivityForResult(intent, REQUEST_JUROS_COMPOSTOS);
            laucher.launch(intent);
        }catch (Exception e){
            editTextValorPresente.selectAll();
            editTextValorPresente.requestFocus();
        }
    }

    public void relacaoValorFuturoClick(View view){
        try {
            Double valorPresente = Double.parseDouble((editTextValorPresente.getText().toString()));
            Intent intent = new Intent(MainActivity.this,ValorFuturo.class);
            intent.putExtra("valorPresente", valorPresente);
            laucher.launch(intent);
        }catch (Exception e){
            editTextValorPresente.selectAll();
            editTextValorPresente.requestFocus();
        }
    }

    /*@Override
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
    */

}