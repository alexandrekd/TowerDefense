package app.modele;

public class node {
    private int  distance;
    private int  x,y;

    public node(int distance,int x,int y){
        this.distance = distance;
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getDistance() {
        return distance;
    }

}
