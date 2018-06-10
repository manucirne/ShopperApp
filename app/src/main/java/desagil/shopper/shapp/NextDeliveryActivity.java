package desagil.shopper.shapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.TextView;


public class NextDeliveryActivity extends AppCompatActivity {
    Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.next_delivery);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        CardView deliveryCard = findViewById(R.id.deliveryCard);

        Intent intent = getIntent();
        if(intent.hasExtra("ShowToast")){
            String message = intent.getStringExtra("ShowToast");
            Utils.showToast(this,message);
        }

        TextView deliveryName = findViewById(R.id.delivery_name);
        TextView deliveryAddress = findViewById(R.id.delivery_address);
        TextView deliveryPhone = findViewById(R.id.delivery_phone);



        deliveryName.setText("João da Silva");
        deliveryAddress.setText("R. Quatá, 300 - Vila Olimpia São Paulo - SP, 04546-042");
        deliveryPhone.setText("(11) 99999-9999");

        createDummyDelivery(deliveryName.getText().toString());

        deliveryCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openDeliveryActivity();
            }

        });
    }

    private void openDeliveryActivity(){
        Intent intent = new Intent(this, DeliveryActivity.class);
        intent.putExtra("address","R. Quatá, 300 - Vila Olimpia São Paulo - SP, 04546-042");
        intent.putExtra("phone", "(11)97070-7070");
        intent.putExtra("name","João da Silva");
        startActivity(intent);

        finish();
    }

    private void createDummyDelivery(String name){
        SharedPreferences sharedPreferences;
        SharedPreferences.Editor editor;

        sharedPreferences = context.getSharedPreferences("SHOPPER_APP", Context.MODE_PRIVATE);

        if(!sharedPreferences.contains("Recipient")){
            editor = sharedPreferences.edit();
            editor.putString("Recipient",name);
            editor.apply();
        }
    }

}