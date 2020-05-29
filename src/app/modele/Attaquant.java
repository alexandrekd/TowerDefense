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

	public Attaquant(Environnement env, int pv, int vitesse, int img) {
		super(env,"Simonot");
		init(pv,vitesse,img);
	}

	public Attaquant(Environnement env, int pv, int vitesse, int x, int y, int img) {
		super(x, y, env, "Attaquant");
		init(pv,vitesse,img);
	}

	public void init(int pv, int vitesse, int img){
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

	public void gagneDeLaVie(int pv){this.pv += pv;
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
}