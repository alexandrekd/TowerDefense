package app.modele;

public class Missile {
    private String id;
    private Tourelle debActeur;
    private Attaquants finActeur;
    private int x;
    private int y;

    public Missile(Tourelle depart, Attaquants fin){
        this.debActeur = depart;
        this.finActeur = fin;
        this.x = depart.getX();
        this.y = depart.getY();
        this.id = depart.getId();
    }


    public void bouge(){
        int xarv = this.x - finActeur.getX();
        int yarv = this.y - finActeur.getY();

        if(xarv > 0)
            this.x--;
        else if (xarv < 0)
            this.x++;

        if (yarv == 0 && xarv == 0)
            finActeur.recevoirTir(debActeur.getDegat());
            debActeur.env.getProject().remove(chercheId());
        else if (yarv < 0)
            this.y++;
        else
            this.y--;

    }

    public int chercheId(){
        int value;
        for (int i = 0 ; i < debActeur.env.getProject().size(); i++){
            if (id)
        }
    }
}
