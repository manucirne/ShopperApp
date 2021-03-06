package desagil.shopper.shapp;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class OpenCamera extends AppCompatActivity {

    Context context = this;

    static final int REQUEST_IMAGE_CAPTURE = 1;
    Bundle extras = new Bundle();
    Bitmap imageBitmap;
    ImageButton buttonPhotoImage;
    Data data = new Data();
    DataProcess dataProcess = new DataProcess(this);

    public void onBackPressed() {
        super.onBackPressed();
        openSignatureActivity();
        finish();
    }

    private void openSignatureActivity() {
        Intent intent = new Intent(this, SignatureActivity.class);
        startActivity(intent);
        finish();
    }

    private void openNextDeliveryActivity() {
        Intent intent = new Intent(this, NextDeliveryActivity.class);
        createJSON();
//        extras.putParcelable("imagebitmap", imageBitmap);
//        intent.putExtras(extras);
        intent.putExtra("ShowToast","Entrega finalizada com sucesso!");
        startActivity(intent);
//        Para receber em uma nova pagina
//        Bundle extras = getIntent().getExtras();
//        Bitmap bmp = (Bitmap) extras.getParcelable("imagebitmap");
//
//        image.setImageBitmap(bmp );
        finish();
    }

    private void createJSON(){
        Intent intent = getIntent();
        String numBoxes = intent.getStringExtra("boxes");

        data.setNumBoxes(numBoxes);
        data.setName(dataProcess.getName());
        data.setPhoto(imageBitmap);
        dataProcess.sendData(data);
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
                    final ProgressDialog progressDialog = ProgressDialog.show(context,"Enviando assinatura e foto", "Por favor, aguarde",true, true);
                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            progressDialog.cancel();
                            openNextDeliveryActivity();
                        }
                    },3000);
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
