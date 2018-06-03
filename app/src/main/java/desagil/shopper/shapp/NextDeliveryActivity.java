package desagil.shopper.shapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import android.widget.ImageView;


public class NextDeliveryActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.next_delivery);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        ImageView map_image = (ImageView) findViewById(R.id.map_image);

        map_image.setOnClickListener(new View.OnClickListener() {
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