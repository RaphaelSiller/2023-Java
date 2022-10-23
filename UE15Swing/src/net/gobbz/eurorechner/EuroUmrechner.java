package net.gobbz.eurorechner;

/**
 * Dieser Euroumrechner stellt Konstanten für die Kurse und die Namen der Währungen
 * bereit. Weiters kann dem Rechner ein Betrag und die Ausgangswährung übergeben werden
 * in der dieser Betrag vorliegt: z. B. betrag = 1000, waehrung = 7 für Italienische Lire.
 * Der Umrechner kann dann diesen Betrag in eine andere Währung durch die Methode
 * getBetrag umrechnen. Dieser Methode wird die Währung übergeben in die umgerechnet
 * werden soll: z. B. getBetrag(1) würde im obigen Fall 7,106 ergeben weil 1 für 
 * Österreichische Schillinge steht. 
 * @author Michael Wild
 */
public class EuroUmrechner
{
	/*
	 * Private Membervariablen
	 */
	/**
	 * Speichert die Währung ab in welcher der Betrag eingegeben wurde
	 */
	private int waehrung = -1;
	/**
	 * Speichert den umzurechnenden Betrag ab, wobei in waehrung die Währung des 
	 * umzurechnenden Betrages eingestellt wurde
	 */
	private double betrag = 0;
	
	/*
	 * Öffentlichen Konstanten 
	 */
	/**
	 * Die Umrechnungskurse der einzelnen Währungen in einem konstanten Feld
	 */
	public final double[] KURSE =	
	  {1, 13.7603, 40.3399, 1.95583, 166.386, 6.55957, 0.787564,	
	  1936.27, 40.399, 2.20371, 200.482, 5.94573, 340.75};
	/**
	 * Die Namen der einzelnen Währungen in einem konstanten Feld
	 */
	public final String[] WAEHRUNGEN =	
	  {"Euro", "Österreichische Schilling", "Belgische Franc", "Deutsche Mark",	
	  "Spanische Peseten", "Französische Franc", "Irische Pfund",	
	  "Italienische Lire", "Luxenburgische Franc", "Niederländische Gulden",	
	  "Portugiesische Escudos", "Finnmark", "Griechische Drachmen"};
	public final int EURO = 0;
	public final int OESTERREICHISCHE_SCHILLING = 1;
	public final int BELGISCHE_FRANC = 2;
	public final int DEUTSCHE_MARK = 3;
	public final int SPANISCHE_PESETEN = 4;
	public final int FRANZOESISCHE_FRANC = 5;
	public final int IRISCHE_PFUND = 6;
	public final int ITALIENISCHE_LIRE = 7;
	public final int LUXENBURGISCHE_FRANC = 8;
	public final int NIEDERLAENDISCHE_GULDEN = 9;
	public final int PORTUGIESISCHE_ESCUDO = 10;
	public final int FINMARK = 11;
	public final int GRIECHISCHE_DRACHMEN = 12;
	
	/**
	 * Setzt die Währung, welche den Währungsbetrag entspricht. Es muss eine gültige
	 * Währungsnummer eingegeben werden, ansonsten macht die Methode nichts
	 * @param waehrung die zu setzen ist
	 */
	public void setWaehrung(int waehrung) {
		if (waehrung >= 0 && waehrung < KURSE.length)
			this.waehrung = waehrung;
	}
	/**
	 * Liefert die Nummer der Währung, welcher der eingegebene Währungsbetrag entspricht
	 * @return die Nummer der Währung
	 */
	public int getWaehrung() {
		int ret = this.waehrung;
		return ret;
	}
	
	/**
	 * Setzt den umzurechnenden Betrag. Der Betrag muss größer oder gleich 0 sein
	 * @param betrag der gesetzt werden soll
	 */
	public void setBetrag(double betrag) {
		if (betrag >= 0)
			this.betrag = betrag;
	}
	/**
	 * Liefert den in die übergebene Währung umgerechneten Währungsbetrag zurück. Dabei
	 * muss währung die Nummer einer gültigen Währung sein
	 * @param waehrung in die umgerechnet werden soll
	 * @return den in die übergebene Währung umgerechnete Währungsbetrag
	 */
	public double getBetrag(int waehrung) {
		double ret = 0;
		if (waehrung >= 0 && waehrung < KURSE.length && this.waehrung >= 0)
			ret = this.betrag / KURSE[this.waehrung] * KURSE[waehrung];
		return ret;
	}
}
