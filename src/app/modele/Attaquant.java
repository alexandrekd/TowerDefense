package app.modele;

import org.omg.CosNaming.BindingIterator;

public abstract class Attaquant extends Acteur {
	private int pv;
	private int constante;
	private int dx; // dx et dy representent la direction de l'attaquant
	private int dy;
	private int vitesse;
	private int consVitesse;
	private int img;

	public Attaquant(Environnement env, int pv, int vitesse, int x, int y, int img) {
		super(x, y, env, "Attaquant");
		this.pv = pv;
		this.vitesse = vitesse;
		this.img = img;
		this.constante = pv;
		this.consVitesse = vitesse;
	}

	public int getConsVitesse() {
		return consVitesse;
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

	public void gagneDeLaVie(int pv){
		this.pv += pv;
	}

	public void setPv(int pv) { this.pv = pv; }

	public int getVitesse() {
		return vitesse;
	}

	public void setVitesse(int vitesse) {
		this.vitesse = vitesse;
	}

	public int getDx() {
		return dx;
	}

	public int getDy() {
		return dy;
	}

	public int getPV() {
		return constante;
	}

	public Environnement getEnv(){
		return this.env;
	}

	public void bouge(){
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

		this.setX(this.getX() + getVitesse() * getDx());
		//Pas besoin de else, vu que si le prochain deplacement en x depasse l'environnement, il ne bouge pas
		this.setY(this.getY() + getVitesse() * getDy());
		//Pas besoin de else, vu que si le prochain deplacement en y depasse l'environnement, il ne bouge pas
	}
}