package app.modele;

import java.util.Random;

public class Attaquant extends Acteur{
	/*private int x; // x et y representent les coordonnees actuelles de l'attaquant
	private int y;
	static String id;
	public static int compteur = 0;
	private Environnement env;*/

	private int pv;
	private int dx; // dx et dy representent la direction de l'attaquant
	private int dy;
	private int vitesse;


	public Attaquant(Environnement env, int pv, int vitesse) {
		/*this.env = env;
		this.id = "A"+compteur;
		Random random=new Random();
		int x = random.nextInt(env.getWidth()-1);
		int y=random.nextInt(env.getHeight()-1);*/

		super(env);
		System.out.println(this.getX() + " ; " + this.getY());
		this.pv = pv;
		this.vitesse = vitesse;
	}

	public void seDeplacer() {// Cette methode permet a l'attaquant de se deplacer, meme s'il peut changer de direction
		if ((Math.random() * 1 < 0.2)) { // Ici, les 10% de chances de changer de direction s'appliquent
			this.dx = ((int) (Math.random() * 3))-1;
			this.dy = ((int) (Math.random() * 3))-1;
		}

		if ((this.getX() + this.vitesse * this.dx) >= 0 && (this.getX() + this.vitesse * this.dx) < this.env.getWidth()) { //Si son prochain d�placement ne d�passe pas l'environnement en x
			this.setX(this.getX() + this.vitesse * this.dx);
		}

		//Pas besoin de else, vu que si le prochain deplacement en x depasse l'environnement, il ne bouge pas

		if ((this.getY() + this.vitesse * this.dy) >= 0 && (this.getY() + this.getY() * this.dy) < this.env.getHeight()) { //Si son prochain d�placement ne d�passe pas l'environnement en y
			this.setY(this.getY() + this.vitesse * this.dy);
		}

		//Pas besoin de else, vu que si le prochain deplacement en y depasse l'environnement, il ne bouge pas
	}

	public void mourir() {
		this.pv = 0;
	}

	public boolean estVivant(){
		if(this.pv <= 0){
			return false;
		}
		return true;
	}

	public void recevoirTir(int dégatsReçus) {
		this.pv = this.pv - dégatsReçus;
		System.out.println("PV = " + this.pv);
	}

	public void arriver() {

	}

	/*public int getX(){
		System.out.println(this.x);
		return this.x;
	}
	public int getY(){
		System.out.println(this.y);
		return this.y;
	}*/
}
