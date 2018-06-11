package desagil.shopper.shapp;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class DeliveryActivity extends AppCompatActivity {

    //String boxes = "";
    String nameC = "";
    String address = "";
    String boxes = "";

    public void onBackPressed(){
        super.onBackPressed();
        openNewDeliveryActivity();
        finish();
    }

    private void openNewDeliveryActivity() {
        Intent intent = new Intent(this, NextDeliveryActivity.class);
        startActivity(intent);

        finish();
    }

    private void openPassActivity() {
        Intent intent = new Intent(this, JumpActivity.class);
        startActivity(intent);

        finish();
    }

    private void openOpenSingnature(String boxes) {
        Intent intent = new Intent(this, SignatureActivity.class);
        intent.putExtra("boxes",boxes);
        startActivity(intent);

        finish();
    }

    private boolean isEmpty(EditText etText) {
        return etText.getText().toString().trim().length() == 0;
    }

    private void showWarning(String message){
        Utils.showToast(this, message);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delivery);

        final TextView clientname = (TextView) findViewById(R.id.clientname);
        final TextView clientadress = (TextView) findViewById(R.id.clientadress);
        final EditText editboxes = (EditText) findViewById(R.id.boxes);

        Intent intent = getIntent();
        nameC = intent.getStringExtra("name");
        address = intent.getStringExtra("address");
        clientname.setText(nameC);
        clientadress.setText(address);

        clientadress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri gmmIntentUri = Uri.parse("geo:0,0?q=" + address);
                Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                mapIntent.setPackage("com.google.android.apps.maps");
                startActivity(mapIntent);
            }
        });
        Button buttonentrega = (Button) findViewById(R.id.entrega);
        Button buttonpass = (Button) findViewById(R.id.pass);

        buttonentrega.setOnClickListener(new View.OnClickListener() {
           // String meio = editboxes.getText().toString();


            @Override
            public void onClick(View view) {
                if (!isEmpty(editboxes)){
                    boxes = editboxes.getText().toString();
                    editboxes.setText(boxes);
                    openOpenSingnature(boxes);
                }
                else{
                    showWarning("Por favor insira o número de volumes");
                }

            }

        });

        buttonpass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openPassActivity();
            }
        });
    }
}
