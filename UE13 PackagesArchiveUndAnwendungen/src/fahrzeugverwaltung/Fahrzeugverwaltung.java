package fahrzeugverwaltung;

import java.util.Vector;

import net.gobbz.TestScanner.TestScannerErweitert;
import net.gobbz.fahrzeuge.*;

public class Fahrzeugverwaltung {
	@SuppressWarnings("rawtypes")
	private Vector garage = new Vector();

	/**
	 * Methode zum erstellen/einfuegen eines Fahrzeuges in die Verwaltung
	 */
	@SuppressWarnings("unchecked")
	public void eingeben() {
		// Interface
		System.out.println();
		System.out.println("*****Eingeben*****");
		System.out.println("Bitte waehlen Sie ihr Fahrzeugtyp");
		System.out.println("1 - Fahrzeug");
		System.out.println("2 - Auto");
		System.out.println("3 - Lastwagen");
		System.out.println("4 - Fahrrad");
		int modus = TestScannerErweitert.readInt("Ihr Fahrzeugtyp: ");

		// Auswahl nach Fahrzeugtyp
		switch (modus) {
		case 1: { // Fahrzeug
			System.out.println("*****Fahrzeug*****");
			System.out.println("Bitte geben Sie die Spezifikationen ein");
			int geschwindigkeit = TestScannerErweitert.readInt("Geschwindigkeit: ");
			int kilometerstand = TestScannerErweitert.readInt("kilometerstand: ");
			garage.add(new Fahrzeug(geschwindigkeit, kilometerstand));
			break;

		}
		case 2: { // Auto
			System.out.println("*****Auto*****");
			System.out.println("Bitte geben Sie die Spezifikationen ein");
			int geschwindigkeit = TestScannerErweitert.readInt("Geschwindigkeit: ");
			int kilometerstand = TestScannerErweitert.readInt("kilometerstand: ");
			int ps = TestScannerErweitert.readInt("PS: ");
			garage.add(new Auto(geschwindigkeit, kilometerstand, ps));
			break;

		}
		case 3: {// Lastwagen
			System.out.println("*****Lastwagen*****");
			System.out.println("Bitte geben Sie die Spezifikationen ein");
			int geschwindigkeit = TestScannerErweitert.readInt("Geschwindigkeit: ");
			int kilometerstand = TestScannerErweitert.readInt("kilometerstand: ");
			int ps = TestScannerErweitert.readInt("PS: ");
			int ladeflaeche = TestScannerErweitert.readInt("Ladeflaeche: ");
			garage.add(new Lastwagen(geschwindigkeit, kilometerstand, ps, ladeflaeche));
			break;

		}
		case 4: {// Fahrrad
			System.out.println("*****Fahrrad*****");
			System.out.println("Bitte geben Sie die Spezifikationen ein");
			int geschwindigkeit = TestScannerErweitert.readInt("Geschwindigkeit: ");
			int kilometerstand = TestScannerErweitert.readInt("kilometerstand: ");
			boolean beleuchtung = (TestScannerErweitert.readChar("Beleuchtung(y/n): ") == 'y') ? true : false;
			garage.add(new Fahrrad(geschwindigkeit, kilometerstand, beleuchtung));
			break;

		}
		default: {// Fehlerhafte Eingabe
			System.out.println("Fehler bei der Eingabe, bitte versuchen Sie es erneut");
			break;
		}
		}
	}

	/**
	 * Fragt nach einer Fahrzeugnummer und gibt diese aus.
	 */
	public void suchen() {
		// Interface
		System.out.println();
		System.out.println("*****Suchen*****");
		int index = TestScannerErweitert.readInt("Bitte geben Sie die gesuchte Fahrzeugnummer ein: ");
		try { // Wenn objekt nicht existiert, wird Fehlermeldung ausgegeben
			Fahrzeug f = (Fahrzeug) garage.get(index);
			System.out.println(f.toString());
		} catch (ArrayIndexOutOfBoundsException e) {
			System.out.println("Ein Fahrzeug mit dieser nummer existiert nicht");
		}
	}

	/**
	 * Gibt Fahrzeug vom angegeben index zurueck und kontrolliert, ob dieser
	 * existiert.
	 * 
	 * @param index
	 *            vom gesuchten Fahrzeug
	 * @return das gesuchte Fahrzeug, wenn ein Fahrzeug mit dieser Nummer existiert,
	 *         ansonsten null
	 */
	public Fahrzeug suchen(int index) {
		try { // Wenn objekt nicht existiert, wird Fehlermeldung ausgegeben
			return (Fahrzeug) garage.get(index);
		} catch (ArrayIndexOutOfBoundsException e) {
			return null;
		}
	}

	/**
	 * Benutzer kann fahrzeug auswaehlen und dessen Werte aendern
	 */
	public void aendern() {
		// Interface
		System.out.println();
		System.out.println("*****Aendern*****");
		int index = TestScannerErweitert.readInt("Bitte geben Sie die gesuchte Fahrzeugnummer ein: ");
		Fahrzeug f;
		while ((f = suchen(index)) == null)
			System.out.println("Fehler beim suchen des Fahrzeugs; Fahrzeugnummer falsch?");
		System.out.println(f.toString());

		/**
		 * Modus wird auf passenden Fahrzeugtyp gesetzt 
		 * 		-1 Fehler 
		 * 		 0 Fahrzeug 
		 * 		 1 Auto 
		 * 		 2 Lastwagen 
		 * 		 3 Fahrrad
		 */
		int modus;
		if (f instanceof Fahrzeug) {
			modus = 0;
			if (f instanceof Auto) {
				modus = 1;
				if (f instanceof Lastwagen)
					modus = 2;
			} else if (f instanceof Fahrrad)
				modus = 3;
		} else
			modus = -1;

		// Aendern der Werte
		char modusWert = ' ';
		do {
			System.out.println("q - aendern beenden");
			switch (modus) {
			// Fahrzeug
			case 0: {
				System.out.println("*****Fahrzeug*****");
				System.out.println("g - geschwindigkeit = " + f.getGeschwindigkeit());
				System.out.println("k - kilometerstand  = " + f.getKilometerstand());
				modusWert = TestScannerErweitert.readChar("Welchen Wert moechten Sie aendern? ");
				
				switch (modusWert) {
				case 'g': //geschwindigkeit
					f.setGeschwindigkeit(TestScannerErweitert.readInt("neue Geschwindigkeit: "));
					break;
				
				case 'k': //kilometerstand
					f.setKilometerstand(TestScannerErweitert.readInt("neuer Kilometerstand: "));
					break;
					
				default:
					break;
				}
				break;
			}

			// Auto
			case 1: {
				System.out.println("*****Auto*****");
				System.out.println("g - geschwindigkeit = " + f.getGeschwindigkeit());
				System.out.println("k - kilometerstand  = " + f.getKilometerstand());
				System.out.println("p - ps = " + ((Auto) f).getPs());
				modusWert = TestScannerErweitert.readChar("Welchen Wert moechten Sie aendern? ");
				
				switch (modusWert) {
				case 'g': //geschwindigkeit
					f.setGeschwindigkeit(TestScannerErweitert.readInt("neue Geschwindigkeit: "));
					break;
				
				case 'k': //kilometerstand
					f.setKilometerstand(TestScannerErweitert.readInt("neuer Kilometerstand: "));
					break;
					
				case 'p': //ps
					((Auto) f).setPs(TestScannerErweitert.readInt("neue ps-Anzahl: "));
					break;
					
				default:
					break;
				}
				break;
				
				
			}

			// Lastwagen
			case 2: {
				System.out.println("*****Lastwagen*****");
				System.out.println("g - geschwindigkeit = " + f.getGeschwindigkeit());
				System.out.println("k - kilometerstand  = " + f.getKilometerstand());
				System.out.println("p - ps = " + ((Lastwagen) f).getPs());
				System.out.println("l - ladeflaeche = " + ((Lastwagen) f).getLadeflaeche());
				modusWert = TestScannerErweitert.readChar("Welchen Wert moechten Sie aendern? ");
				
				switch (modusWert) {
				case 'g': //geschwindigkeit
					f.setGeschwindigkeit(TestScannerErweitert.readInt("neue Geschwindigkeit: "));
					break;
				
				case 'k': //kilometerstand
					f.setKilometerstand(TestScannerErweitert.readInt("neuer Kilometerstand: "));
					break;
					
				case 'p': //ps
					((Lastwagen) f).setPs(TestScannerErweitert.readInt("neue ps-Anzahl: "));
					break;
					
				case 'l': //Ladeflaeche
					((Lastwagen) f).setLadeflaeche(TestScannerErweitert.readInt("neue Ladeflaeche: "));
					break;
					
				default:
					break;
				}
				break;
			}

			// Fahrrad
			case 3: {
				System.out.println("*****Fahrrad*****");
				System.out.println("g - geschwindigkeit = " + f.getGeschwindigkeit());
				System.out.println("k - kilometerstand  = " + f.getKilometerstand());
				System.out.println("b - beleuchtung = " + ((Fahrrad) f).isBeleuchtung());
				modusWert = TestScannerErweitert.readChar("Welchen Wert moechten Sie aendern? ");
				
				switch (modusWert) {
				case 'g': //geschwindigkeit
					f.setGeschwindigkeit(TestScannerErweitert.readInt("neue Geschwindigkeit: "));
					break;
				
				case 'k': //kilometerstand
					f.setKilometerstand(TestScannerErweitert.readInt("neuer Kilometerstand: "));
					break;
					
				case 'b': //Beleuchtung
					((Fahrrad) f).setBeleuchtung(TestScannerErweitert.readChar("Beleuchtung y/n: ") == 'y');
					break;
					
				default:
					break;
				}
				
				break;
			}

			default:
				System.out.println("Fahrzeugtyp unbekannt");
				break;
			}
		} while (modusWert != 'q');
	}

	/**
	 * Fragt nach einen Index und loescht das Fahrzeug welches an diesem Index liegt
	 */
	public void loeschen() {
		// Interface
		System.out.println();
		System.out.println("*****Loeschen*****");
		int index = TestScannerErweitert.readInt("Bitte geben Sie die zu loeschende Fahrzeugnummer ein: ");
		while (suchen(index) == null)
			System.out.println("Fehler beim suchen des Fahrzeugs; Fahrzeugnummer falsch?");
		garage.remove(index);
	}
	
	/**
	 * Gibt alle gespeicherten Fahrzeuge in einer Liste aus
	 */
	@SuppressWarnings("rawtypes")
	public void liste() {
		java.util.Enumeration e = garage.elements();
		int i = 0;
		while (e.hasMoreElements()) {
			Fahrzeug x = (Fahrzeug)e.nextElement();
			System.out.println(i + ": " + x.toString());
			i++;
		}
	}

}
