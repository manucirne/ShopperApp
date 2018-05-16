package desagil.shopper.shapp;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class SignatureActivity extends AppCompatActivity {


    //função para ir para OpenCamera
    private void openOpenCamera() {
        // Exemplo de código para abrir uma activity.
        Intent intent = new Intent(this, OpenCamera.class);
        startActivity(intent);
        finish();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signature);

        //criar botão para envair a justificativa
        Button buttonContinue = (Button) findViewById(R.id.button);

        //TODO: fazer a parte de pegar assinatura:
        //aqui em baixo é pra pegar de text view
        //final TextView text = (TextView) findViewById(R.id.textView);
        //text.setText(justification);


        //Atividade do botão enviar - Vai para a página de próxima entrega:
        buttonContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //aqui tem que fazer algo com a assinatura p enviar para o servidos
                openOpenCamera();

            }
        });

    }
    ////


}
