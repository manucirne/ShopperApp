package desagil.shopper.shapp;

import android.graphics.Bitmap;
import android.util.Base64;

import java.io.ByteArrayOutputStream;

public class DataProcess {

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
        //TODO: Transformar em String a assinatura
    }

    public void sendJustify(){}

    private String BitmapToString(Bitmap bitmap){
        ByteArrayOutputStream baos = new  ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG,100, baos);
        byte [] arr=baos.toByteArray();
        String result = Base64.encodeToString(arr, Base64.DEFAULT);
        return result;
    }
}
