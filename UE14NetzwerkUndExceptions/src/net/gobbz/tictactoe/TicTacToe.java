package net.gobbz.tictactoe;

public class TicTacToe {
	public static final int SPIELER1 = -1, SPIELER2 = -2;
	public int              feldgroesse;
	protected int[][]       spielfeld;

	/**
	 * Konstruktor initialisiert das Spielfeld mit Zahlen beginnend bei 0. Ist die
	 * Feldgröße kleiner als 3 dann wird diese auf 3 gesetzt und ein entsprechendes
	 * Spielfeld angelegt. Ist beispielsweise die Feldgr��e auf 3 eingestellt, so
	 * wird das Spielfeld folgenderma�en initialisiert:
	 * 
	 * @param feldgroesse
	 */
	public TicTacToe(int feldgroesse) {
		if (feldgroesse < 3)
			feldgroesse = 3;
		this.feldgroesse = feldgroesse;

		spielfeld = new int[feldgroesse][feldgroesse];
		for (int i = 0; i < feldgroesse; i++) {
			for (int j = 0; j < feldgroesse; j++) {
				spielfeld[i][j] = i * feldgroesse + j;
			}
		}
	}

	/**
	 * Zeilenweise Ausgabe des Spielfeldes. Dabei werden an den gesetzten Postionen
	 * nicht die Spielernummern ausgegeben sondern f�r den ersten Spieler ein X und
	 * f�r den Zweiten ein O (O wie Otto). Beispielsweise wird f�r das Spielfeld
	 * 0 1 -1
	 * 3 -2 -1
	 * 6 7 8
	 * folgendes zur�ck gegeben:
	 * 01X
	 * 3OX
	 * 678
	 * 
	 * @return das Spielfeld aufgeteilt auf mehrere Zeilen
	 */
	public String toString() {
		String ret = "";

		for (int i = 0; i < this.feldgroesse; i++) {
			for (int j = 0; j < this.feldgroesse; j++) {
				switch (spielfeld[i][j]) {
					case -2:
						ret += "O";
						break;

					case -1:
						ret += "X";
						break;

					default:
						ret += spielfeld[i][j];
						break;
				}
			}
			ret += "\n";
		}
		return ret;
	}

	/**
	 * Liefert die Feldgr��e des Spielfeldes zur�ck
	 * 
	 * @return die Feldgr��e des Spielfeldes
	 */
	public int getFeldgroesse() {
		return this.feldgroesse;
	}

	/**
	 * Ermittelt wie das Spielfeld an der Stelle zeile/spalte gesetzt ist
	 * 
	 * @param zeile  des Spielfeldes an der nachgeschaut werden soll
	 * @param spalte des Spielfeldes an der nachgeschaut werden soll
	 * @return 0 falls an der �bergebenen Position noch kein Spieler gesetzt hat
	 *         SPIELER1 falls an der �bergebenen Position der erste Spieler gesetzt
	 *         hat
	 *         SPIELER2 falls an der �bergebenen Position der zweite Spieler gesetzt
	 *         hat
	 *         -3 falls zeile und/oder spalte au�erhalb des Spielfeldes zugreifen
	 *         wollen
	 */
	@SuppressWarnings("static-access")
	public int getSpielfeld(int zeile, int spalte) {
		if (zeile >= this.feldgroesse || spalte >= this.feldgroesse)
			return -3;

		switch (this.spielfeld[zeile][spalte]) {
			case -1:
				return this.SPIELER1;

			case -2:
				return this.SPIELER2;

			default:
				return 0;
		}
	}

	/**
	 * Setzt den �bergebenen Zug im Spielfeld f�r den Spieler dessen Nummer
	 * ebenfalls �bergeben wurde
	 * 
	 * @param zug
	 * @param spielernummer
	 * @return
	 *         0 falls Zug erfolgreich gesetzt werden konnte
	 *         -1 falls der Zug au�erhalb des Spielfeldes liegt
	 *         -2 falls der Zug bereits gesetzt wurde
	 */
	public int setZug(int zug, int spielernummer) {
		int zeile  = zug / this.feldgroesse;
		int spalte = zug % this.feldgroesse;
		// Zug ausserhalb Feldgroesse
		if (zeile >= this.feldgroesse || spalte >= this.feldgroesse)
			return -1;

		// Zug bereits gesetzt
		if (this.spielfeld[zeile][spalte] == -1 || this.spielfeld[zeile][spalte] == -2)
			return -2;

		this.spielfeld[zeile][spalte] = (spielernummer == SPIELER1 ? SPIELER1 : SPIELER2);
		return 0;
	}

	/**
	 * Setzt den Zug des Spielers 1
	 * 
	 * @param zug den zu setzenden Zug
	 * @return
	 *         0 falls Zug erfolgreich gesetzt werden konnte
	 *         -1 falls der Zug au�erhalb des Spielfeldes liegt
	 *         -2 falls der Zug bereits gesetzt wurde
	 */
	@SuppressWarnings("static-access")
	public int setZugSpieler1(int zug) {
		return this.setZug(zug, this.SPIELER1);
	}

	/**
	 * Setzt den Zug des Spielers 2
	 * 
	 * @param zug den zu setzenden Zug
	 * @return
	 *         0 falls Zug erfolgreich gesetzt werden konnte
	 *         -1 falls der Zug au�erhalb des Spielfeldes liegt
	 *         -2 falls der Zug bereits gesetzt wurde
	 */
	@SuppressWarnings("static-access")
	public int setZugSpieler2(int zug) {
		return this.setZug(zug, this.SPIELER2);
	}

	/**
	 * Ermittelt die Nummer des Spielers der gewonnen hat
	 * 
	 * @return Nummer des Spielers, welcher gewonnen hat, bzw. 0, wenn noch kein
	 *         Sieger feststeht
	 */
	@SuppressWarnings("static-access")
	public int getGewonnen() {
		/**
		 * Kontrolliert, ob im Spielfeld eine Dreiherreihe mit der Nummer eines Spielers
		 * gibt
		 */
		for (int i = 0; i < this.feldgroesse; i++) {
			for (int j = 0; j < this.feldgroesse; j++) {

				//@formatter:off
				// → Horizontal
				if (i < this.feldgroesse - 2 // Vorbeugung IndexOutOfBoundsException
						&& this.spielfeld[i][j]     == this.SPIELER1
						&& this.spielfeld[i + 1][j] == this.SPIELER1 
						&& this.spielfeld[i + 2][j] == this.SPIELER1)
					return this.SPIELER1;

				// ↓ Vertikal
				if (j < this.feldgroesse - 2 // Vorbeugung IndexOutOfBoundsException
						&& this.spielfeld[i][j]     == this.SPIELER1
						&& this.spielfeld[i][j + 1] == this.SPIELER1 
						&& this.spielfeld[i][j + 2] == this.SPIELER1)
					return this.SPIELER1;

				// ↘ Diagonal - nach unten
				if (i < this.feldgroesse - 2 && j < this.feldgroesse - 2 // Vorbeugung IndexOutOfBoundsException
						&& this.spielfeld[i][j]         == this.SPIELER1
						&& this.spielfeld[i + 1][j + 1] == this.SPIELER1
						&& this.spielfeld[i + 2][j + 2] == this.SPIELER1)
					return this.SPIELER1;
				
				// ↗ Diagonal - nach oben
				if (i < this.feldgroesse - 2 && j >= 2 // Vorbeugung IndexOutOfBoundsException
						&& this.spielfeld[i][j]         == this.SPIELER1
						&& this.spielfeld[i + 1][j - 1] == this.SPIELER1
						&& this.spielfeld[i + 2][j - 2] == this.SPIELER1)
					return this.SPIELER1;

				// → Horizontal
				if (i < this.feldgroesse - 2 // Vorbeugung IndexOutOfBoundsException
						&& this.spielfeld[i][j]     == this.SPIELER2
						&& this.spielfeld[i + 1][j] == this.SPIELER2 
						&& this.spielfeld[i + 2][j] == this.SPIELER2)
					return this.SPIELER2;

				// ↓ Vertikal
				if (j < this.feldgroesse - 2 // Vorbeugung IndexOutOfBoundsException
						&& this.spielfeld[i][j]     == this.SPIELER2
						&& this.spielfeld[i][j + 1] == this.SPIELER2 
						&& this.spielfeld[i][j + 2] == this.SPIELER2)
					return this.SPIELER2;

				// ↘ Diagonal - nach unten
				if (i < this.feldgroesse - 2 && j < this.feldgroesse - 2 // Vorbeugung IndexOutOfBoundsException
						&& this.spielfeld[i][j]         == this.SPIELER2
						&& this.spielfeld[i + 1][j + 1] == this.SPIELER2
						&& this.spielfeld[i + 2][j + 2] == this.SPIELER2)
					return this.SPIELER2;
					
				// ↗ Diagonal - nach oben
				if (i < this.feldgroesse - 2 && j >= 2 // Vorbeugung IndexOutOfBoundsException
						&& this.spielfeld[i][j]         == this.SPIELER2
						&& this.spielfeld[i + 1][j - 1] == this.SPIELER2
						&& this.spielfeld[i + 2][j - 2] == this.SPIELER2)
					return this.SPIELER2;
				
				//@formatter:on
			}
		}
		return 0;
	}

	/**
	 * Ermittelt ob einer der Spieler das Spiel noch gewinnen kann
	 * 
	 * @return true falls das Spiel noch gewonnen werden kann
	 */
	public boolean getEinerKannGewinnen() {
		/**
		 * Geht jede mögliche Dreierkombination im Spielfeld durch und schaut, ob die 3
		 * Felder entweder von Spieler1/Spieler2 besetzt oder unbesetzt sind.
		 */
		for (int i = 0; i < this.feldgroesse; i++) {
			for (int j = 0; j < this.feldgroesse; j++) {
				//@formatter:off
				// → Horizontal
				if(i < this.feldgroesse-2 // Vorbeugung IndexOutOfBoundsException
						&& (this.spielfeld[i][j]   >= 0 || this.spielfeld[i][j]   == SPIELER1) 
						&& (this.spielfeld[i+1][j] >= 0 || this.spielfeld[i+1][j] == SPIELER1) 
						&& (this.spielfeld[i+2][j] >= 0 || this.spielfeld[i+2][j] == SPIELER1))
					return true;

				// ↓ Vertikal
				if(j < this.feldgroesse-2 // Vorbeugung IndexOutOfBoundsException
						&& (this.spielfeld[i][j]   >= 0 || this.spielfeld[i][j]   == SPIELER1)
						&& (this.spielfeld[i][j+1] >= 0 || this.spielfeld[i][j+1] == SPIELER1) 
						&& (this.spielfeld[i][j+2] >= 0 || this.spielfeld[i][j+2] == SPIELER1))
					return true;

				// ↘ Diagonal - nach unten
				if(i < this.feldgroesse-2 && j < this.feldgroesse-2 // Vorbeugung IndexOutOfBoundsException
						&& (this.spielfeld[i][j]     >= 0 || this.spielfeld[i][j]     == SPIELER1) 
						&& (this.spielfeld[i+1][j+1] >= 0 || this.spielfeld[i+1][j+1] == SPIELER1) 
						&& (this.spielfeld[i+2][j+2] >= 0 || this.spielfeld[i+2][j+2] == SPIELER1))
					return true;
				
				// ↗ Diagonal - nach oben
				if(i < this.feldgroesse-2 && j > 2 // Vorbeugung IndexOutOfBoundsException
						&& (this.spielfeld[i][j]     >= 0 || this.spielfeld[i][j]     == SPIELER1) 
						&& (this.spielfeld[i+1][j+1] >= 0 || this.spielfeld[i+1][j-1] == SPIELER1) 
						&& (this.spielfeld[i+2][j+2] >= 0 || this.spielfeld[i+2][j-2] == SPIELER1))
					return true;
				
				
				// → Horizontal
				if(i < this.feldgroesse-2 // Vorbeugung IndexOutOfBoundsException
						&& (this.spielfeld[i][j]   >= 0 || this.spielfeld[i][j]   == SPIELER2) 
						&& (this.spielfeld[i+1][j] >= 0 || this.spielfeld[i+1][j] == SPIELER2) 
						&& (this.spielfeld[i+2][j] >= 0 || this.spielfeld[i+2][j] == SPIELER2))
					return true;

				// ↓ Vertikal
				if(j < this.feldgroesse-2 // Vorbeugung IndexOutOfBoundsException
						&& (this.spielfeld[i][j]   >= 0 || this.spielfeld[i][j]   == SPIELER2)
						&& (this.spielfeld[i][j+1] >= 0 || this.spielfeld[i][j+1] == SPIELER2) 
						&& (this.spielfeld[i][j+2] >= 0 || this.spielfeld[i][j+2] == SPIELER2))
					return true;

				// ↘ Diagonal - nach unten
				if(i < this.feldgroesse-2 && j < this.feldgroesse-2 // Vorbeugung IndexOutOfBoundsException
						&& (this.spielfeld[i][j]     >= 0 || this.spielfeld[i][j]     == SPIELER2) 
						&& (this.spielfeld[i+1][j+1] >= 0 || this.spielfeld[i+1][j+1] == SPIELER2) 
						&& (this.spielfeld[i+2][j+2] >= 0 || this.spielfeld[i+2][j+2] == SPIELER2))
					return true;
				
				// ↗ Diagonal - nach oben
				if(i < this.feldgroesse-2 && j > 2 // Vorbeugung IndexOutOfBoundsException
						&& (this.spielfeld[i][j]     > 0 || this.spielfeld[i][j]     == SPIELER2) 
						&& (this.spielfeld[i+1][j+1] > 0 || this.spielfeld[i+1][j-1] == SPIELER2) 
						&& (this.spielfeld[i+2][j+2] > 0 || this.spielfeld[i+2][j-2] == SPIELER2))
					return true;
				//@formatter:on
			}
		}
		return false;
	}

}
