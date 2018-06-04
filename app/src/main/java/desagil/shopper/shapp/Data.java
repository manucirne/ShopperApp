package desagil.shopper.shapp;

import android.graphics.Bitmap;

public class Data {

    private String name;
    private String boxNum;
    private Bitmap imageBoxes;
    //TODO: Falta receber a assinatura

    public void setName(String newName){
        name = newName;
    }

    public void setNumBoxes(String num){
        boxNum = num;
    }

    public void setPhoto(Bitmap image){
        imageBoxes = image;
    }

    //TODO: Criar um setter para a assinatura

    public String getUserName(){
        return name;
    }

    public String getNumBoxes(){
        return boxNum;
    }

    public Bitmap getPhoto(){
        return imageBoxes;
    }

    //TODO: Criar um getter para a assinatura

}
