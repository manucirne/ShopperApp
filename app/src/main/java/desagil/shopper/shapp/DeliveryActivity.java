package desagil.shopper.shapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class DeliveryActivity extends AppCompatActivity {

    //String boxes = "";
    String nameC = "";
    String adressC = "";

    public void onBackPressed(){
        super.onBackPressed();
        openNewDeliveryActivity();
        finish();
    }

    private void openNewDeliveryActivity() {
        Intent intent = new Intent(this, NewDeliveryActivity.class);
        startActivity(intent);

        finish();
    }


    private void openJumpActivity(String boxes) {
        Intent intent = new Intent(this, JumpActivity.class);
        intent.putExtra("boxes",boxes);
        startActivity(intent);

        finish();
    }

    private void openSingnActivity(String boxes) {
        Intent intent = new Intent(this, SingnActivity.class);
        intent.putExtra("boxes",boxes);
        startActivity(intent);

        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delivery);
        final TextView clientname = (TextView) findViewById(R.id.clientname);
        final TextView clientadress = (TextView) findViewById(R.id.clientadress);

        Intent intent = getIntent();
        nameC = intent.getStringExtra("name");
        adressC = intent.getStringExtra("adress");
        clientname.setText(nameC);
        clientadress.setText(adressC);


        final TextView boxesText = (TextView) findViewById(R.id.boxes);

        Button buttonSaveB = (Button) findViewById(R.id.saveboxes);
        Button buttonentrega = (Button) findViewById(R.id.entrega);


        buttonSaveB.setOnClickListener(new View.OnClickListener() {
            String boxes = boxesText.getText().toString();

            @Override
            public void onClick(View view) {
                boxesText.setText(boxes);


            }

        });

        buttonentrega.setOnClickListener(new View.OnClickListener() {
            String boxes = boxesText.getText().toString();


            @Override
            public void onClick(View view) {
                boxesText.setText(boxes);
                openSingnActivity(boxes);


            }

        });


    }
}
