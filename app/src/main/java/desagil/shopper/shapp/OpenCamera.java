package desagil.shopper.shapp;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class OpenCamera extends AppCompatActivity {

    ImageView imageViewCamera;
    static final int REQUEST_IMAGE_CAPTURE = 1;

    public void onBackPressed() {
        super.onBackPressed();
        openMainActivity();
        finish();
    }

    private void openMainActivity() {
        // Exemplo de código para abrir uma activity. Especificamente, a SendActivity.
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    private void openNextDeliveryActivity() {
        // Exemplo de código para abrir uma activity. Especificamente, a SendActivity.
        Intent intent = new Intent(this, NextDeliveryActivity.class);
        intent.putExtra("imageViewCamera", imageViewCamera);
        startActivity(intent);
        //https://stackoverflow.com/questions/17878907/get-imageviews-image-and-send-it-to-an-activity-with-intent
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);

        Button buttonPhoto = (Button)findViewById(R.id.button_photo);
        Button buttonFinish = (Button)findViewById(R.id.button_finish);

        imageViewCamera = (ImageView)findViewById(R.id.imageView_camera);

        buttonFinish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (imageViewCamera == null){
                    Utils.showToast(this, "Você precisa tirar a foto antes de prosseguir");
                }
                else{
                    openNextDeliveryActivity();
                }
            }
        });
    }

    public void dispatchTakePictureIntent(View view) {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            imageViewCamera.setImageBitmap(imageBitmap);
        }
    }
}
