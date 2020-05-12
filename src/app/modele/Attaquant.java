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

		int x = this.getX();
		int y = this.getY();
		do {
			this.dx = ((int) (Math.random() * 3))-1;
			if ((this.getX() + this.vitesse * this.dx) >= 0 && (this.getX() + this.vitesse * this.dx) < this.env.getWidth()) { //Si son prochain d�placement ne d�passe pas l'environnement en x
				x =(this.getX() + this.vitesse * this.dx);
			}
			this.dy = ((int) (Math.random() * 3))-1;
			if ((this.getY() + this.vitesse * this.dy) >= 0 && (this.getY() + this.vitesse * this.dy) < this.env.getHeight()) { //Si son prochain d�placement ne d�passe pas l'environnement en y
				y = (this.getY() + this.vitesse * this.dy);
			}
		}while (env.getMap().get((y/10)*55+(x/10))%2 != 0);
		this.setX(x);
		//Pas besoin de else, vu que si le prochain deplacement en x depasse l'environnement, il ne bouge pas
		this.setY(y);
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
