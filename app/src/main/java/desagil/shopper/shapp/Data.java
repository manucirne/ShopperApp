package desagil.shopper.shapp;

import android.graphics.Bitmap;

public class Data {

    private String name;
    private String boxNum;
    private Bitmap imageBoxes;
    private Bitmap Signature;
    private String justify;

    public void setName(String newName){
        name = newName;
    }

    public void setNumBoxes(String num){
        boxNum = num;
    }

    public void setPhoto(Bitmap image){
        imageBoxes = image;
    }

    private void setSignature(Bitmap image){
        Signature = image;
    }

    public void setJustify(String newJustify){
        justify = newJustify;
    }

    public String getUserName(){
        return name;
    }

    public String getNumBoxes(){
        return boxNum;
    }

    public Bitmap getPhoto(){
        return imageBoxes;
    }

    public Bitmap getSignature(){
        return Signature;
    }

    public String getJustify(){
        return justify;
    }

}
