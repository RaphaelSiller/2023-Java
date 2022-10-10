package brueche;

public class Bruch {
	int zaehler, nenner;

	public Bruch(int zaehler, int nenner) throws BruchException {
		this.setNenner(nenner);
		this.setZaehler(zaehler);
	}

	public int getZaehler() {
		return zaehler;
	}

	public void setZaehler(int zaehler) {
		this.zaehler = zaehler;
		this.kuerze();
	}

	public int getNenner() {
		return nenner;
	}

	public void setNenner(int nenner) throws BruchException {
		if (nenner == 0)
			throw new BruchException("Nenner ist Null");
		this.nenner = nenner;
		this.kuerze();
	}

	/**
	 * Kürzt den Bruch, indem Nenner und Zähler solange durch den größten
	 * gemeinsamen Teiler gekürzt werden, bis der ggT 1 beträgt
	 */
	private void kuerze() {
		int ggT;
		while ((ggT = getGGT(this.zaehler, this.nenner)) > 1) {
			this.zaehler /= ggT;
			this.nenner /= ggT;
		}
	}

	/**
	 * gibt den größten gemeinsamen Teiler von a und b zurück
	 * 
	 * @param a
	 * @param b
	 * @return
	 */
	int getGGT(int a, int b) {
		// Schau ob eine Zahl von min(a, b) bis 1 durch a und b teilbar sind, d.h. der
		// größte gemeinsame Teiler ist
		for (int i = Math.min(a, b); i >= 1; i--) {
			if (a % i == 0 && b % i == 0)
				return i;
		}
		// Wenn 1 der ggT ist
		return 1;
	}

	public String toString() {
		return this.zaehler + "/" + this.nenner;
	}

	public boolean equals(Object b) throws NullPointerException {
		// Fehlererkennung
		if (b == null)
			throw new NullPointerException("Zweiter Bruch leer");
		if (!(b instanceof Bruch))
			throw new ClassCastException("Nicht passend");

		// Return true, wenn Nenner und Zaehler beider Brüche gleich sind
		return (this.getNenner() == ((Bruch) b).getNenner() && this.getZaehler() == ((Bruch) b).getZaehler());
	}

	/**
	 * Gibt einen neuen Bruch mit den gleichen Zähler und Nenner zurück
	 */
	public Bruch clone() {
		try {
			return new Bruch(this.getZaehler(), this.getNenner());
		} catch (BruchException e) {
			System.out.println("Wie zur Hölle bist du hier gelandet? Das sollte nicht möglich sein.");
			return null;
		}
	}

	public void addiere(Bruch b) throws NullPointerException {
		if (b == null)
			throw new NullPointerException("Zweiter Bruch leer");
		this.nenner *= b.nenner;
		this.zaehler *= b.nenner;
		this.zaehler += (b.zaehler * (this.nenner / b.nenner));
		this.kuerze();
	}

	public void subtrahiere(Bruch b) throws NullPointerException {
		if (b == null)
			throw new NullPointerException("Zweiter Bruch leer");
		this.nenner *= b.nenner;
		this.zaehler *= b.nenner;
		this.zaehler -= (b.zaehler * (this.nenner / b.nenner));
		this.kuerze();
	}

	public void multipliziere(Bruch b) throws NullPointerException {
		if (b == null)
			throw new NullPointerException("Zweiter Bruch leer");
		this.nenner *= b.nenner;
		this.zaehler *= b.zaehler;
		this.kuerze();
	}

	public void dividiere(Bruch b) throws NullPointerException {
		if (b == null)
			throw new NullPointerException("Zweiter Bruch leer");
		this.nenner *= b.zaehler;
		this.zaehler *= b.nenner;
		this.kuerze();
	}

}
