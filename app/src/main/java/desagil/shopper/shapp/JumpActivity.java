package desagil.shopper.shapp;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;




public class JumpActivity extends AppCompatActivity {
    String justification = "";


    //função para ir para DeliveryActivity
    private void openDeliveryActivity() {
        // Exemplo de código para abrir uma activity.
        Intent intent = new Intent(this, DeliveryActivity.class);
        startActivity(intent);
        finish();
    }

    //se mudar o nome da deliveryactivty mudar aqui **********88
    //função para ir para DeliveryActivity NewDeliveryActivity
    private void openNewDeliveryActivity() {
        // Exemplo de código para abrir uma activity.
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jump);

        //criar botão para envair a justificativa
        Button buttonSend = (Button) findViewById(R.id.button_send);
        Button buttonBack = (Button) findViewById(R.id.button_back);

        //para pegar a mensagem
        final TextView text = (TextView) findViewById(R.id.textView);
        text.setText(justification);


        //Atividade do botão enviar - Vai para a página de próxima entrega:
        buttonSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //aqui tem que fazer algo com a variavel justification - enviar para o servidos
                openNewDeliveryActivity();  //NewDeliveryActivity - pag do wes (proxima entrega)

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


