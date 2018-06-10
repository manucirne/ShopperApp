package desagil.shopper.shapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;

import android.widget.ImageView;
import android.widget.TextView;


public class NextDeliveryActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.next_delivery);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        CardView deliveryCard = findViewById(R.id.deliveryCard);

        TextView deliveryName = findViewById(R.id.delivery_name);
        TextView deliveryAddress = findViewById(R.id.delivery_address);
        TextView deliveryPhone = findViewById(R.id.delivery_phone);

        deliveryName.setText("João da Silva");
        deliveryAddress.setText("Rua Quata 300");
        deliveryPhone.setText("(11) 99999-9999");

        deliveryCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openDeliveryActivity();

            }

        });
    }

    private void openDeliveryActivity(){
        Intent intent = new Intent(this, DeliveryActivity.class);
        intent.putExtra("address","R.Evans, 130");
        intent.putExtra("phone", "(11)97070-7070");
        startActivity(intent);

        finish();
    }

}