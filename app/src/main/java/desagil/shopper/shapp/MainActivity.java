package desagil.shopper.shapp;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Handler;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


public class MainActivity extends AppCompatActivity {
    private static final int REQUEST_CAMERA = 0;
    Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.CAMERA)
                == PackageManager.PERMISSION_DENIED) {

            String[] permissions = new String[1];
            permissions[0] = Manifest.permission.CAMERA;

            ActivityCompat.requestPermissions(MainActivity.this, permissions, REQUEST_CAMERA);
        }

        final EditText credentials_view = (EditText) findViewById(R.id.credentials_input);


        final EditText password_view = (EditText) findViewById(R.id.password_input);


        Button login_button = (Button) findViewById(R.id.login_button);

        login_button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                String credentials = credentials_view.getText().toString();
                String password = password_view.getText().toString();

                if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.CAMERA)
                        == PackageManager.PERMISSION_DENIED) {

                    String[] permissions = new String[1];
                    permissions[0] = Manifest.permission.CAMERA;

                    ActivityCompat.requestPermissions(MainActivity.this, permissions, REQUEST_CAMERA);
                }
                else if(credentials.equals("") && password.equals("")){
                    final ProgressDialog progressDialog = ProgressDialog.show(context,"Logging in", "Por favor, aguarde",true, true);
                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            progressDialog.cancel();
                            openDeliveryActivity();
                        }
                    },600);

                }
                else{
                    mostrarTorrada("Email e senha são vazios");
                }

            }

        });
        }

    //Todo: Criar a classe da Delivery Activity
    private void openDeliveryActivity(){
        Intent intent = new Intent(this, NextDeliveryActivity.class);
        startActivity(intent);
        finish();
    }
    //TODO: Mudar o nome desse método
    private void mostrarTorrada(String message){
        Utils.showToast(this, message);
    }



    @Override
    public void onRequestPermissionsResult(int request, String[] permissions, int[] results) {
        // Se o pedido de permissão foi para utilizar a camera....
        if(request == REQUEST_CAMERA) {
            // ...e a permissão não foi concedida, avisamos e pedimos novamente.
            if(results.length > 0 && results[0] == PackageManager.PERMISSION_DENIED) {
                mostrarTorrada("Você precisa conceder permissão!");
            }
        }
    }
}