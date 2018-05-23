package desagil.shopper.shapp;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class OpenCamera extends AppCompatActivity {

    static final int REQUEST_IMAGE_CAPTURE = 1;
    Bundle extras = new Bundle();
    Bitmap imageBitmap;
    ImageButton buttonPhotoImage;

    public void onBackPressed() {
        super.onBackPressed();
        openMainActivity();
        finish();
    }

    private void openMainActivity() {
        Log.i("Send Email","email teste");
        Intent emailSend = new Intent(Intent.ACTION_SEND);
        emailSend.setData(Uri.parse("mailto:decoejz@gmail.com"));
        emailSend.setType("text/plain");
        emailSend.putExtra(Intent.EXTRA_EMAIL, "wesleygas@al.insper.edu.br");
        emailSend.putExtra(Intent.EXTRA_TEXT,"Isso é um email de teste para ver se chega alguma coisa");
        startActivity(Intent.createChooser(emailSend,"mandando...."));

        Intent intent = new Intent(this, NextDeliveryActivity.class);
        startActivity(intent);
        finish();
    }

    private void openNextDeliveryActivity() {
        // Exemplo de código para abrir uma activity. Especificamente, a SendActivity.
        Intent intent = new Intent(this, MainActivity.class);//NextDeliveryActivity.class);
        extras.putParcelable("imagebitmap", imageBitmap);
        intent.putExtras(extras);
        startActivity(intent);
        //Para receber em uma nova pagina
        //Bundle extras = getIntent().getExtras();
        //Bitmap bmp = (Bitmap) extras.getParcelable("imagebitmap");
        //
        //image.setImageBitmap(bmp );
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);

        Button buttonPhoto = (Button)findViewById(R.id.button_photo);
        buttonPhotoImage = (ImageButton) findViewById(R.id.image_button);//ImageButton
        Button buttonFinish = (Button)findViewById(R.id.button_finish);

        buttonFinish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (imageBitmap == null){
                    System.out.println("Não existe nenhuma foto no momento");
                    showWarning("É necessário uma foto para poder prosseguir");
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

    private void showWarning(String message){
        Utils.showToast(this, message);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            extras = data.getExtras();
            imageBitmap = (Bitmap) extras.get("data");
            BitmapDrawable bdrawable = new BitmapDrawable(getResources(),imageBitmap);
            buttonPhotoImage.setBackground(bdrawable);
        }
    }
}
