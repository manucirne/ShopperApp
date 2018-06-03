package desagil.shopper.shapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class DeliveryActivity extends AppCompatActivity {

    //String boxes = "";
    String nameC = "";
    String adressC = "";
    String boxes = "";

    public void onBackPressed(){
        super.onBackPressed();
        openNewDeliveryActivity();
        finish();
    }

    private void openNewDeliveryActivity() {
        Intent intent = new Intent(this, MainActivity.class);
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delivery);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        final TextView clientname = (TextView) findViewById(R.id.clientname);
        final TextView clientadress = (TextView) findViewById(R.id.clientadress);
        final EditText editboxes = (EditText) findViewById(R.id.boxes);

        Intent intent = getIntent();
        nameC = intent.getStringExtra("name");
        adressC = intent.getStringExtra("adress");
        clientname.setText(nameC);
        clientadress.setText(adressC);

        Button buttonentrega = (Button) findViewById(R.id.entrega);
        Button buttonpass = (Button) findViewById(R.id.pass);

        buttonentrega.setOnClickListener(new View.OnClickListener() {
           // String meio = editboxes.getText().toString();


            @Override
            public void onClick(View view) {
                boxes = editboxes.getText().toString();
                editboxes.setText(boxes);
                openOpenSingnature(boxes);

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
