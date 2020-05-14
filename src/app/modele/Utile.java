package app.modele;

public class Utile {

    public static int toPixel(int val){
        return val * 50;
    }

    public static int toTexture(int val){
        return val/50;
    }

    public static int toWidth(int val){return  val*11;}

    public static int toHeight(int val){return val*8;}

    public static int toX(int val){return val%11;}

    public static int toY(int val){return val/11;}

}
