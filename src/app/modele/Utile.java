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

    public static int getCaseDepart(Missile missile){
        for (int i = 0 ; i < missile.env.getMap().size(); i++){
            if(env.getMap().get(i)/900 ==1){
                if (count == random){
                    Acteur attaquant = new Normal(env,Utile.toX(i),Utile.toPixel(Utile.toY(i)) + (int) (Math.random()*50));
                    this.env.getActeurs().add(attaquant);
                    count = 0;
                }
                else
                    count++;
            }
        }

    }

}
