package desagil.shopper.shapp;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;

import android.util.Base64;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;


public class DataProcess extends Activity{

    private JSONObject deliveryInformation = new JSONObject() ;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    String stringfiedJSON;
    private Context context;
    public DataProcess(Context context){
        this.context = context;
    }

    public String getName(){
        return "name";
    }

    public void getAddress(){}

    public void getNumBoxes(){}

    public void getId(){}//Se tiver!

    public void sendData(Data infos){



        String userName = infos.getUserName();
        String numBoxes = infos.getNumBoxes();
        String photoImage = BitmapToString(infos.getPhoto());
        //String userSignature = SignatureToString();//TODO: Transformar em String a assinatura

        try {
            deliveryInformation.put("Nome", userName);
            deliveryInformation.put("Quantidade de caixas recebidas", numBoxes);
            deliveryInformation.put("Foto da entrega", photoImage);
            //deliveryInformation.put("Assinatura do cliente", userSignature);
        }
        catch (JSONException e){
            e.printStackTrace();
        }
        //TODO: Mandar o JSON para algum lugar
        stringfiedJSON = deliveryInformation.toString();
        sharedPreferences = context.getSharedPreferences("SHOPPER_APP",Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();

        editor.putString("deliveryJSON", stringfiedJSON);
        editor.commit();

        //Test of the stored JSON file

        String jsonRecov = sharedPreferences.getString("deliveryJSON","");
        System.out.println(jsonRecov);

    }

    public void sendJustify(Data infos){
        String userName = infos.getUserName();
        String userJustify = infos.getJustify();

        try {
            deliveryInformation.put("Nome", userName);
            deliveryInformation.put("Justificativa da falta de entrega", userJustify);
        }
        catch (JSONException e){
            e.printStackTrace();
        }
    }

    //Code from: http://androidapplicationdeveloper.weebly.com/android-tutorial/how-to-convert-bitmap-to-string-and-string-to-bitmap
    //Last access june-07-2018
    private String BitmapToString(Bitmap bitmap){
        ByteArrayOutputStream baos = new  ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG,100, baos);
        byte [] arr=baos.toByteArray();
        return Base64.encodeToString(arr, Base64.DEFAULT);
    }

    private String SignatureToString(){
        return "";
    }
}
