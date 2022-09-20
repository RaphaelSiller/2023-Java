package fahrzeugverwaltung;

import net.gobbz.TestScanner.TestScannerErweitert;

public class fahrzeugverwaltungTest {
	public static void main(String[] args) {
		boolean userStillWorking = true;
		Fahrzeugverwaltung garage = new Fahrzeugverwaltung();
		while (userStillWorking) {
			// Interface
			System.out.println();
			System.out.println("Fahrzeugverwaltung");
			System.out.println("==================");
			System.out.println("1 - Eingeben");
			System.out.println("2 - Suchen");
			System.out.println("3 - Aendern");
			System.out.println("4 - Loeschen");
			System.out.println("5 - Liste");
			System.out.println("6 - Ende");
			int modus = TestScannerErweitert.readInt("Ihre Wahl (1 - 6): ");

			// ModusAuswahl
			switch (modus) {
			case 1:
				garage.eingeben();
				break;

			case 2:
				garage.suchen();
				break;

			case 3:
				garage.aendern();
				break;

			case 4:
				garage.loeschen();
				break;

			case 5:
				garage.liste();
				break;

			case 6:
				userStillWorking = false;
				break;

			default:
				System.out.println("Fehler bei der Modusauswahl; falsche Eingabe");
				break;
			}
		}
	}
}
