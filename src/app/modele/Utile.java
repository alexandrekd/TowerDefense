package app.modele;

import app.modele.Eleve.Normal;
import app.modele.Eleve.Theo;

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

    public static int toXPixel(int val){return  val%50;}

    public static int toX(int val){return val%32;}

    public static int toY(int val){return val/32;}

    public static void goToCaseDepart(Missile missile){
        int random = (int) (Math.random() * missile.getEnv().getMap().parallelStream().filter(n-> n/900 == 1).collect(Collectors.toList()).size());
        int x,y,count = 0;
        for (int i = 0 ; i < missile.getEnv().getMap().size(); i++){
            if(missile.getEnv().getMap().get(i)/900 ==1){
                if (count == random){
                    missile.getFinActeur().setX(Utile.toX(i));
                    missile.getFinActeur().setY(Utile.toPixel(Utile.toY(i)) + (int) (Math.random()*50));
                    count = 0;
                }
                else
                    count++;
            }
        }
    }

    // Cree et retourne des ennemis positionnes sur la case depart de maniere aleatoire
    public static Acteur creerEnnemi (Environnement env){
        int random = (int) (Math.random() * env.getMap().parallelStream().filter(n-> n/900 == 1).collect(Collectors.toList()).size());
        int count = 0;
        Acteur attaquant = null;
        for (int i = 0 ; i < env.getMap().size(); i++){
            if(env.getMap().get(i)/900 ==1){
                if (count == random){
                    attaquant = new Normal(env,Utile.toX(i),Utile.toPixel(Utile.toY(i)) + 5 +(int) (Math.random()*40));  // new Normal(env,Utile.toX(i),Utile.toPixel(Utile.toY(i)) + (int) (Math.random()*50));
                    count = 0;
                }
                else
                    count++;
            }
        }
        return attaquant;
    }

}
