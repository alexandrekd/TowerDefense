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
		this.pv = pv;
		this.vitesse = vitesse;
	}

	public Attaquant(Environnement env, int pv, int vitesse,int x,int y) {
		super(x,y,env);
		this.pv = pv;
		this.vitesse = vitesse;
	}

	public void seDeplacer() {// Cette methode permet a l'attaquant de se deplacer, meme s'il peut changer de direction

		int xB = (this.getX()/10);
		int yB = (this.getY()/10);

		int bestPos = 10000;
			node value = env.getUnNode(xB-1 , yB);
			if(value != null) {
				if (value.getDistance() < bestPos) {
					bestPos = value.getDistance();
					dx = -1;
					dy = 0;
				}
			}

			value = env.getUnNode(xB+1 , yB);
			if(value != null) {
				if (value.getDistance() < bestPos) {
					bestPos = value.getDistance();
					dx = 1;
					dy = 0;
				}
			}

			value = env.getUnNode(xB , yB-1);
			if(value != null) {
				if (value.getDistance() < bestPos) {
					bestPos = value.getDistance();
					dx = 0;
					dy = -1;
				}
			}

			value = env.getUnNode(xB , yB+1);
			if(value != null) {
				if (value.getDistance() < bestPos) {
					bestPos = value.getDistance();
					dx = 0;
					dy = 1;
				}
			}

		//System.out.println(dx +""+ dy);
		this.setX(this.getX() + this.vitesse * this.dx);
		//Pas besoin de else, vu que si le prochain deplacement en x depasse l'environnement, il ne bouge pas
		this.setY((this.getY() + this.vitesse * this.dy));
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
