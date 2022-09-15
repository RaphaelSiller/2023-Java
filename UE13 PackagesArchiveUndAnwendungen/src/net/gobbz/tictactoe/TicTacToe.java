package net.gobbz.tictactoe;

import java.util.Iterator;

public class TicTacToe {
	public static final int SPIELER1=-1, SPIELER2=-2;
	public int feldgroesse;
	private int[][] Spielfeld;

	/**
	 * Konstruktor initialisiert das Spielfeld mit Zahlen beginnend bei 0. Ist die
	 * Feldgröße kleiner als 3 dann wird diese auf 3 gesetzt und ein entsprechendes
	 * Spielfeld angelegt. Ist beispielsweise die Feldgr��e auf 3 eingestellt, so
	 * wird das Spielfeld folgenderma�en initialisiert:
	 * 
	 * @param feldgroesse
	 */
	public TicTacToe(int feldgroesse) {
		if(feldgroesse<3)
			feldgroesse=3;
		this.feldgroesse = feldgroesse;
		
		Spielfeld = new int[feldgroesse][feldgroesse];
		for(int i = 0; i < feldgroesse; i++) {
			for(int j = 0; j < feldgroesse; j++) {
				Spielfeld[i][j] = i*3+j;
			}
		}
	}
	
	/**
	 * Zeilenweise Ausgabe des Spielfeldes. Dabei werden an den gesetzten Postionen nicht die Spielernummern ausgegeben sondern f�r den ersten Spieler ein X und f�r den Zweiten ein O (O wie Otto). Beispielsweise wird f�r das Spielfeld
     * 0 1 -1
	 * 3 -2 -1
	 * 6 7 8
	 * folgendes zur�ck gegeben:
	 * 01X
	 * 3OX
	 * 678
	 * @return das Spielfeld aufgeteilt auf mehrere Zeilen
	 */
	public String toString() {
		String ret = "";
		
		for(int i = 0; i < this.feldgroesse; i++) {
			for(int j = 0; j < this.feldgroesse; j++) {
				switch (Spielfeld[i][j]) {
				case -2:
					ret+="O";
					break;
					
				case -1:
					ret+="X";
					break;

				default:
					ret+=Spielfeld[i][j];
					break;
				}
			}
			ret+="\n";
		}
		return ret;
	}
	
	/**
	 * Liefert die Feldgr��e des Spielfeldes zur�ck
	 * @return die Feldgr��e des Spielfeldes
	 */
	public int getFeldgroesse() {
		return this.feldgroesse;
	}
	
	/**
	 * Ermittelt wie das Spielfeld an der Stelle zeile/spalte gesetzt ist
	 * @param zeile des Spielfeldes an der nachgeschaut werden soll
	 * @param spalte des Spielfeldes an der nachgeschaut werden soll
	 * @return 0 falls an der �bergebenen Position noch kein Spieler gesetzt hat
	 * 		SPIELER1 falls an der �bergebenen Position der erste Spieler gesetzt hat
	 * 		SPIELER2 falls an der �bergebenen Position der zweite Spieler gesetzt hat
	 * 		-3 falls zeile und/oder spalte au�erhalb des Spielfeldes zugreifen wollen
	 */
	public int getSpielfeld(int zeile, int spalte) {
		if (zeile >= this.feldgroesse || spalte >= this.feldgroesse)
			return -3;
		
		switch (this.Spielfeld[zeile][spalte]) {
		case -1:
			return this.SPIELER1;
			
		case -2:
			return this.SPIELER2;

		default:
			return 0;
		}
	}
	
	/**
	 * Setzt den �bergebenen Zug im Spielfeld f�r den Spieler dessen Nummer ebenfalls �bergeben wurde
	 * @param zug 
	 * @param spielernummer
	 * @return 
	 *   0 falls Zug erfolgreich gesetzt werden konnte
	 * 	-1 falls der Zug au�erhalb des Spielfeldes liegt
	 * 	-2 falls der Zug bereits gesetzt wurde
	 */
	private int setZug(int zug, int spielernummer) {
		int zeile = zug / this.feldgroesse;
		int spalte = zug % feldgroesse;
		//Zug ausserhalb Feldgroesse
		if (zeile >= this.feldgroesse || spalte >= this.feldgroesse)
			return -1;
		
		//Zug bereits gesetzt
		if(this.Spielfeld[zeile][spalte] == -1 || this.Spielfeld[zeile][spalte] == -2)
			return -2;
		
		this.Spielfeld[zeile][spalte] = spielernummer;
		return 0;
	}
	
	/**
	 * Setzt den Zug des Spielers 1
	 * @param zug den zu setzenden Zug
	 * @return 
	 *   0 falls Zug erfolgreich gesetzt werden konnte
	 * 	-1 falls der Zug au�erhalb des Spielfeldes liegt
	 * 	-2 falls der Zug bereits gesetzt wurde
	 */
	public int setZugSpieler1(int zug) {
		return this.setZug(zug, this.SPIELER1);
	}
	
	/**
	 * Setzt den Zug des Spielers 2
	 * @param zug den zu setzenden Zug
	 * @return 
	 *   0 falls Zug erfolgreich gesetzt werden konnte
	 * 	-1 falls der Zug au�erhalb des Spielfeldes liegt
	 * 	-2 falls der Zug bereits gesetzt wurde
	 */
	public int setZugSpieler2(int zug) {
		return this.setZug(zug, this.SPIELER2);
	}
	
	/**
	 * Ermittelt die Nummer des Spielers der gewonnen hat
	 */
	public int getGewonnen() {
		/**
		 * Dies funktioniert, da nur Felder, die von Spielern belegt wurden einen gleichen Integer haben können
		 */
		for(int i = 0; i < this.feldgroesse-2; i++) {
			for(int j = 0; j < this.feldgroesse-2; j++) {
				//Horizontal
				if(this.Spielfeld[i][j] == this.Spielfeld[i+1][j] &&this.Spielfeld[i][j] == this.Spielfeld[i+2][j])
					return this.Spielfeld[i][j];
				
				//Vertikal
				if(this.Spielfeld[i][j] == this.Spielfeld[i][j+1] &&this.Spielfeld[i][j] == this.Spielfeld[i][j+2])
					return this.Spielfeld[i][j];
				
				//Diagonal
				if(this.Spielfeld[i][j] == this.Spielfeld[i+1][j+1] &&this.Spielfeld[i][j] == this.Spielfeld[i+2][j+2])
					return this.Spielfeld[i][j];
			}
		}
		return 0;
	}
	
	/**
	 * Ermittelt ob einer der Spieler das Spiel noch gewinnen kann
	 * @return true falls das Spiel noch gewonnen werden kann
	 */
	public boolean getEinerKannGewinnen() {
		for(int i = 0; i < this.feldgroesse-2; i++) {
			for(int j = 0; j < this.feldgroesse-2; j++) {
				//Horizontal
				if(this.Spielfeld[i][j] >= 0 && this.Spielfeld[i+1][j] >= 0 && this.Spielfeld[i+2][j] >= 0)
					return true;
				
				//Vertikal
				if(this.Spielfeld[i][j] >= 0 && this.Spielfeld[i][j+1] >= 0 && this.Spielfeld[i][j+2] >= 0)
					return true;
				
				//Diagonal
				if(this.Spielfeld[i][j] >= 0 && this.Spielfeld[i+1][j+1] >= 0 && this.Spielfeld[i+2][j+2] >= 0)
					return true;
			}
		}
		return false;
	}
	
}
