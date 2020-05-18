package app.modele;

import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;

import java.util.Random;

public class Attaquant extends Acteur {
	/*private int x; // x et y representent les coordonnees actuelles de l'attaquant
	private int y;
	static String id;
	public static int compteur = 0;
	private Environnement env;*/

	private int pv;
	private int dx; // dx et dy representent la direction de l'attaquant
	private int dy;
	private int vitesse;
	private int img;
	private boolean estRalentit;


	public Attaquant(Environnement env, int pv, int vitesse, int img) {
		super(env);
		this.pv = pv;
		this.vitesse = vitesse;
		this.img = img;
		this.estRalentit = false;
	}

	public Attaquant(Environnement env, int pv, int vitesse, int x, int y, int img) {
		super(x, y, env,1);
		this.pv = pv;
		this.vitesse = vitesse;
		this.img = img;
		this.estRalentit = false;
	}


	public void agit() {// Cette methode permet a l'attaquant de se deplacer, meme s'il peut changer de direction

		int xB = Utile.toTexture(this.getX());
		int yB = Utile.toTexture(this.getY());
		int bestPos = 10000;


		if (RegardeUnVoisin(env.getUnNode(xB - 1, yB)) < bestPos) {
			donneDirection(-1, 0);
			bestPos = RegardeUnVoisin(env.getUnNode(xB - 1, yB));
		}
		if (RegardeUnVoisin(env.getUnNode(xB + 1, yB)) < bestPos) {
			donneDirection(1, 0);
			bestPos = RegardeUnVoisin(env.getUnNode(xB + 1, yB));
		}
		if (RegardeUnVoisin(env.getUnNode(xB, yB - 1)) < bestPos) {
			donneDirection(0, -1);
			bestPos = RegardeUnVoisin(env.getUnNode(xB, yB - 1));
		}
		if (RegardeUnVoisin(env.getUnNode(xB, yB + 1)) < bestPos) {
			donneDirection(0, 1);
			bestPos = RegardeUnVoisin(env.getUnNode(xB, yB + 1));
		}

		System.out.println(this.dx+"/"+this.dy);
		//System.out.println(dx +""+ dy);
		this.setX(this.getX() + this.vitesse * this.dx);
		//Pas besoin de else, vu que si le prochain deplacement en x depasse l'environnement, il ne bouge pas
		this.setY(this.getY() + this.vitesse * this.dy);
		//Pas besoin de else, vu que si le prochain deplacement en y depasse l'environnement, il ne bouge pas
	}

	public int RegardeUnVoisin(node voisin) {
		int uneDistance = 100000;
		if (voisin != null) {
			uneDistance = voisin.getDistance();
		}
		return uneDistance;
	}

	public void donneDirection(int dxA, int dyA) {
		this.dx = dxA;
		this.dy = dyA;
	}

	public void mourir() {
		this.pv = 0;
	}

	public boolean estVivant() {
		if (this.pv <= 0) {
			return false;
		}
		return true;
	}

	public void recevoirTir(int dégatsReçus) {
		this.pv = this.pv - dégatsReçus;
	}

	public int getPv() { return pv; }

	public void setPv(int pv) { this.pv = pv; }

	public void ralentissement(){
		this.vitesse -= (int) this.vitesse*0.5;
		this.estRalentit = true;
	}

	public boolean estRalentit(){
		return this.estRalentit;
	}
}