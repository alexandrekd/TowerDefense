package app.modele;

public interface Effets {

    public void agit();
    public void Entrer(Attaquant acteur);
    public void Sortir(Attaquant acteur);
    public void Explosion(Missile missile);
}
