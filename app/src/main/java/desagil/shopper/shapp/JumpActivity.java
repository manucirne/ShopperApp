package desagil.shopper.shapp;


import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


public class JumpActivity extends AppCompatActivity {
    private String justification = "";
    private String name = "";
    Data data = new Data();
    Context context = this;
    DataProcess dataProcess = new DataProcess(context);

    //função para ir para DeliveryActivity
    private void openDeliveryActivity() {
        // Exemplo de código para abrir uma activity.
        Intent intent = new Intent(this, DeliveryActivity.class);
        startActivity(intent);
        finish();
    }


    //função para ir para NextDeliveryActivity
    private void openNextDeliveryActivity() {
        // Exemplo de código para abrir uma activity.
        Intent intent = new Intent(this, NextDeliveryActivity.class);
        intent.putExtra("ShowToast","Justificativa enviada com sucesso!");
        startActivity(intent);
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jump);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Button buttonSend = (Button) findViewById(R.id.button_send);
        Button buttonBack = (Button) findViewById(R.id.button_back);

        //para pegar a mensagem
        final EditText text = (EditText) findViewById(R.id.TextJustif);


        SharedPreferences sharedPreferences = context.getSharedPreferences("SHOPPER_APP", Context.MODE_PRIVATE);
        name = sharedPreferences.getString("Recipient", "Name");


        //Atividade do botão enviar - Vai para a página de próxima entrega:
        buttonSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //aqui tem que fazer algo com a variavel justification - enviar para o servidor
                justification = text.getText().toString();
                data.setJustify(justification);
                data.setName(name);
                dataProcess.sendJustify(data);
                final ProgressDialog progressDialog = ProgressDialog.show(context,"Enviando justificativa", "Por favor, aguarde",true, true);
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        progressDialog.cancel();
                        openNextDeliveryActivity();  //NewDeliveryActivity - pag do wes (proxima entrega)
                    }
                },1500);

            }
        });

        //Atividade do botão VOLTAR - Volta para a página de próxima descriçaõ:
        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openDeliveryActivity();

            }
        });
    }
}


