package desagil.shopper.shapp;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private static final int REQUEST_CAMERA = 0;

    private void openCamera() {
        //Para abrir a pagina em que a foto sera tirada
        Intent intent = new Intent(this, OpenCamera.class);
        startActivity(intent);
        finish();
    }

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
                    openCamera(); //O app esta usando o aplicativo camera ao inves de acessar a camera por si só
                }
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int request, String[] permissions, int[] results) {
        // Se o pedido de permissão foi para utilizar a camera....
        if(request == REQUEST_CAMERA) {
            // ...e a permissão foi de fato concedida, abrimos o OpenCamera.
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