package app.modele;

public class Utile {

    public static int toPixel(int val){
        return val * 10;
    }

    public int toTexture(int val){
        return val/10;
    }

    public static int toWidth(int val){return  val*55;}

    public int toHeight(int val){return val*40;}



}
