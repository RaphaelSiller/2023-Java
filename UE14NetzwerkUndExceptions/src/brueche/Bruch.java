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

	private void kuerze() {
		int ggT;
		while ((ggT = getGGT(this.zaehler, this.nenner)) > 1) {
			this.zaehler /= ggT;
			this.nenner /= ggT;
		}
	}

	int getGGT(int a, int b) {
        // stores minimum(a, b)
        int i;
        if (a < b)
            i = a;
        else
            i = b;
 
        // take a loop iterating through smaller number to 1
        for (i = i; i > 1; i--) {
 
            // check if the current value of i divides both
            // numbers with remainder 0 if yes, then i is
            // the GCD of a and b
            if (a % i == 0 && b % i == 0)
                return i;
        }
 
        // if there are no common factors for a and b other
        // than 1, then GCD of a and b is 1
        return 1;
    }

	public String toString() {
		return this.zaehler + "/" + this.nenner;
	}

	public boolean equals(Bruch b) throws NullPointerException {
		if (b == null)
			throw new NullPointerException("Zweiter Bruch leer");
		if (!(b instanceof Bruch))
			throw new ClassCastException("Nicht passend");
		return (this.getNenner() == b.getNenner() && this.getZaehler() == b.getZaehler());
	}

	public Bruch clone() {
		try {
			return new Bruch(this.getZaehler(), this.getNenner());
		} catch (BruchException e) {
			System.out.println("Fehler beim Klonen");
			return null;
		}
	}

	public void addiere(Bruch b) {
		if (b == null)
			throw new NullPointerException("Zweiter Bruch leer");
		this.nenner *= b.nenner;
		this.zaehler *= b.nenner;
		this.zaehler += (b.zaehler * (this.nenner/b.nenner));
		this.kuerze();
	}

	public void subtrahiere(Bruch b) {
		if (b == null)
			throw new NullPointerException("Zweiter Bruch leer");
		this.nenner *= b.nenner;
		this.zaehler *= b.nenner;
		this.zaehler -= (b.zaehler * (this.nenner/b.nenner));
		this.kuerze();
	}

	public void multipliziere(Bruch b) {
		if (b == null)
			throw new NullPointerException("Zweiter Bruch leer");
		this.nenner *= b.nenner;
		this.zaehler *= b.zaehler;
		this.kuerze();
	}

	public void dividiere(Bruch b) {
		if (b == null)
			throw new NullPointerException("Zweiter Bruch leer");
		this.nenner *= b.zaehler;
		this.zaehler *= b.nenner;
		this.kuerze();
	}

}
