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

    private OpenSignature openSignature;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signature);

        //TODO: criar o botao de enviar no xml
        //criar botão para envair a justificativa
        Button buttonSend = (Button) findViewById(R.id.button_send);

        //TODO: fazer a parte de pegar assinatura:
        openSignature = (OpenSignature) findViewById(R.id.signature);

        Button clear_signature = (Button) findViewById((R.id.clear_canvas_button));

        clear_signature.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearCanvas(v);
            }
        });

        //Atividade do botão enviar - Vai parA A CAMERA:
        buttonSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //aqui tem que fazer algo com a assinatura p enviar para o servidos
                openOpenCamera();
            }

        });
    }
    public void clearCanvas(View v){
        openSignature.clearCanvas();
    }

    ////


}
