package app.modele;

import java.util.stream.Collectors;

public class Utile {

    public static int toPixel(int val){
        return val * 50;
    }

    public static int toTexture(int val){
        return val/50;
    }

    public static int toWidth(int val){return  val*32;}

    public static int toHeight(int val){return val*16;}

    public static int toX(int val){return val%32;}

    public static int toY(int val){return val/32;}

    public static int[] goToCaseDepart(Missile missile){
        int random = (int) (Math.random() * missile.getEnv().getMap().parallelStream().filter(n-> n/900 == 1).collect(Collectors.toList()).size());
        int x,y,count = 0;
        for (int i = 0 ; i < missile.getEnv().getMap().size(); i++){
            if(missile.getEnv().getMap().get(i)/900 ==1){
                if (count == random){
                    missile.finActeur = new Normal(env,Utile.toX(i),Utile.toPixel(Utile.toY(i)) + (int) (Math.random()*50));
                    this.env.getActeurs().add(attaquant);
                    count = 0;
                }
                else
                    count++;
            }
        }

    }

}
