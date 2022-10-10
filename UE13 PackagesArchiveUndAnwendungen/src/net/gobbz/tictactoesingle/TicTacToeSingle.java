package net.gobbz.tictactoesingle;
import net.gobbz.TestScanner.TestScannerErweitert;
import net.gobbz.tictactoe.*;

public class TicTacToeSingle {

	public static void main(String[] args) {
		TicTacToe feld = new TicTacToe(3);
		boolean isGameRunning = true;
		boolean Spieler1isActive = true;

		//Dekoration
		System.out.println("TicTacToe\n=========");

		//Ein Zyklus stellt einen Spielzug dar
		while (isGameRunning) {
			//Ausgabe des Spielfeldes
			System.out.println(feld.toString());

			int status = -1; //Status, ob Zug korrekt gesetzt werden konnte
			while (status < 0) {
				//Abfrage des Spielzuges
				int zug = TestScannerErweitert.readInt((Spieler1isActive ? "1" : "2") + ". Spieler: Ihr Zug: ");
				
				//Setzen des Spielzuges und abspeichern des Rückgabewert
				status = feld.setZug(zug, (Spieler1isActive ? -1 : -2));
				
				//Ausgabe einer Fehlermeldung, falls Rückgabewert vom Spielzugsetzen eine Fehlermeldung ist
				switch (status) {
					case -1:
						System.out.println("Dieser Zug liegt außerhalb des Spielfeldes");
						break;

					case -2:
						System.out.println("Dieser Zug wurde bereits gesetzt");
						break;
				
					default:
						break;
				}
			}

			//Kontrolle, ob das Spiel noch weitergehen kann
			if (feld.getGewonnen() != 0 || !feld.getEinerKannGewinnen()) {
				//Wenn Sieger gewonnen hat
				if (feld.getGewonnen() != 0) {
					System.out.println("Spieler " + (Spieler1isActive ? "1" : "2") + " hat gewonnen!!!");
				} else { //Wenn keiner gewonnen hat
					System.out.println("Es kann keiner mehr gewinnen");
				}
				//Soll noch eine Runde gestartet werden?
				isGameRunning = (TestScannerErweitert.readChar("Noch ein Spiel (j/n)? ") == 'j' ? true : false);
				feld = new TicTacToe(3); //Ersetze aktuelles Spielfeld mit einem neuen
			}
			//Spielerwechsel
			Spieler1isActive = !Spieler1isActive;
		}

	}

}
