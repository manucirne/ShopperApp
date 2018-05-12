package desagil.shopper.shapp;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity {
    private static final int REQUEST_CAMERA = 0;
    String mCurrentPhotoPath;

    private void openCamera() {
        File photo;
        Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
        startActivity(intent);
        //nao finalizo o MainActivitivy porque precisa dele depois

    }}

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button buttonOpenCamera = (Button) findViewById(R.id.button_open_camera);

        buttonOpenCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.CAMERA)
                        == PackageManager.PERMISSION_DENIED) {

                    String[] permissions = new String[1];
                    permissions[0] = Manifest.permission.CAMERA;

                    ActivityCompat.requestPermissions(MainActivity.this, permissions, REQUEST_CAMERA);
                }
                else{
                    System.out.println("permissão concedida!");
                    openCamera();
                }
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int request, String[] permissions, int[] results) {
        // Se o pedido de permissão foi para enviar SMS...
        if(request == REQUEST_CAMERA) {
            // ...e a permissão foi de fato concedida, abrimos a SendActivity.
            if(results.length > 0 && results[0] == PackageManager.PERMISSION_GRANTED) {
                openCamera();

            }
            // Senão, permanecemos na mesma activity e mostramos uma bolha de mensagem.
            else {
                Utils.showToast(this, "Você precisa conceder permissão!");
            }
        }
    }
}
