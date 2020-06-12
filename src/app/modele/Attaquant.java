package app.modele;

public abstract class Attaquant extends Acteur {
	private int pv;
	private int PVMAX;
	private int dx; // direction sur x
	private int dy; // direction sur y
	private int vitesse;
	private int consVitesse;

	public Attaquant(Environnement env, int pv, int vitesse, int x, int y,String nom) {
		super(x, y, env, nom);
		this.pv = pv;
		this.vitesse = vitesse;
		this.PVMAX = pv;
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

	public int getPVMAX() {
		return PVMAX;
	}

	public Environnement getEnv(){
		return this.env;
	}
}