package desagil.shopper.shapp;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class SignatureActivity extends AppCompatActivity {

    Data data = new Data();

    //função para ir para OpenCamera
    private void openOpenCamera() {
        // Exemplo de código para abrir uma activity.
        Intent intent = new Intent(this, OpenCamera.class);
        startActivity(intent);
        finish();
    }

    private void showWarning(String message){
        Utils.showToast(this, message);
    }

    private OpenSignature openSignature;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signature);

        Intent intent = getIntent();
        String caixas = intent.getStringExtra("boxes");

        //criar botão para envair a justificativa
        Button buttonSend = (Button) findViewById(R.id.button_send);
        String texto = "Foram recebidos " + caixas + " volumes. Se você confirma assine abaixo:";
        //Texto antes da assinatura
        TextView text = (TextView) findViewById(R.id.text_send);
        text.setText(texto);

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
                if (OpenSignature.getBitmap()==null){
                    showWarning("É necessário uma assinatura para poder prosseguir");
                }
                else {
                    data.setSignature(OpenSignature.getBitmap());
                    openOpenCamera();
                }
            }

        });
    }
    public void clearCanvas(View v){
        openSignature.clearCanvas();
    }
}
