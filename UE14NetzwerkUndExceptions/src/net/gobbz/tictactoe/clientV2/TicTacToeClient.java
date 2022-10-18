package net.gobbz.tictactoe.clientV2;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import net.gobbz.tictactoe.TicTacToe;
import net.gobbz.tictactoe.gui.TicTacToeJFrame;

public class TicTacToeClient extends TicTacToe {
	// Variable über welche der TicTacToeClient verwaltet wird. Das Objekt wird
	// durch die Methode setMeinZug erstellt, in der Methode getGegnerZug weiter
	// verwendet und dann geschlossen
	private Socket          client;
	@SuppressWarnings("unused")
	private static int      FELDGROESSE;  // Die vorgegebene Feldgröße des Spielfeldes
	private static String   ipAdresse;    // Die IP-Adresse an welcher der TicTacToeServer läuft
	private static int      PORT = 65535; // Port auf welchem der Server läuft
	private TicTacToeJFrame gui;          // Variable für die Gui, damit jede Methode darauf zugreifen kann

	/**
	 * Parameterbehafteter Konstruktor, der das Spielfeld anlegt
	 * 
	 * @param feldgroesse - des Spielfeldes
	 */
	public TicTacToeClient(int feldgroesse) {
		super(feldgroesse);
	}

	/**
	 * Im ersten Parameter wird die IP-Adresse des Servers übergeben. Ist dies nicht
	 * der Fall, so bricht das Programm ab
	 * 
	 * @param Args - in args[0] muss die IP-Adresse des TicTacToeServers übergeben
	 *             werden
	 */
	public static void main(String[] Args) {
		if (Args.length == 0)
			return;
		ipAdresse = Args[0];

		TicTacToeClient ticTacToeClient  = null;
		TicTacToeJFrame ticTacToeFenster = null;

		try {
			ticTacToeClient = new TicTacToeClient(3);
			ticTacToeFenster = new TicTacToeJFrame("Client", ticTacToeClient);
			ticTacToeClient.setGui(ticTacToeFenster);
			ticTacToeClient.behandleServer();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				ticTacToeClient.close();
			} catch (Exception e) {
				;
			}
		}

	}

	private void behandleServer() throws UnknownHostException, IOException {
		boolean isGameRunning    = true;
		boolean Spieler1isActive = false;

		while (isGameRunning) {
			// Ausgabe des Spielfeldes
			System.out.println(super.toString());

			if (!Spieler1isActive) {
				gui.setStatusleistentext("Du bist am Zug");
				// Abfrage des Spielzuges
				this.setMeinZug(this.gui.getGewaehlteFeldnummer());
			} else {
				gui.setStatusleistentext("Gegner ist am Zug");
				getGegnerZug();
			}

			// Spielerwechsel
			Spieler1isActive = !Spieler1isActive;

			// Kontrolle, ob das Spiel noch weitergehen kann
			if (super.getGewonnen() != 0 || !super.getEinerKannGewinnen()) {
				System.out.println(this.toString());
				// Wenn Sieger gewonnen hat
				if (super.getGewonnen() != 0) {
					gui.setStatusleistentext(Spieler1isActive ? "Du hast gewonnen" : "Gegner hat gewonnen");
					System.out.println("Spieler " + (Spieler1isActive ? "1" : "0") + " hat gewonnen!!!");
				} else { // Wenn keiner gewonnen hat
					gui.setStatusleistentext("Es kann keiner mehr gewinnen");
					System.out.println("Es kann keiner mehr gewinnen");
				}
				// Soll noch eine Runde gestartet werden?
				isGameRunning = false;
			}
		}
		// Wenn kein Spiel mehr gespielt werden soll
		this.close();
	}

	/**
	 * Schließt den ClientSocket
	 * 
	 * @throws IOException - falls beim Schließen ein Fehler aufgetreten ist
	 */
	void close() throws IOException {
		this.client.close();
	}

	/**
	 * Es wird über den bereits vorhandenen Socket auf den Zug des Servers gewartet.
	 * Dabei muss der Socket existieren. Nach dem Empfangen wird der Socket
	 * geschlossen. Weiters wird der empfangene Zug ins Spielfeld eingetragen
	 * 
	 * @return 0 oder größer falls Zug erfolgreich geholt werden konnte -1 falls der
	 *         Zug außerhalb des Spielfeldes liegt -2 falls der Zug bereits gesetzt
	 *         wurde -3 falls kein Socket vorhanden ist
	 * @throws IOException - falls beim Empfangen ein Fehler aufgetreten ist
	 */
	int getGegnerZug() throws IOException {
		if (client.isClosed())
			return -3;

		InputStream in  = client.getInputStream();
		int         zug = (byte) in.read();
		this.close();
		gui.setXor0(zug, 'X');
		gui.repaint();
		return super.setZug(zug, SPIELER1);
	}

	/**
	 * Setzt den Zug im Spielfeld. Wenn der Zug korrekt gesetzt werden konnte, dann
	 * nimmt die Methode die Verbindung mit dem Server auf und sendet den Zug. Wenn
	 * der Zug gesendet wurde, wird der ClientSocket nicht geschlossen, denn der
	 * Server analysiert den Zug und schickt seinen Zug über denselben Socket zurück
	 * zum Client
	 * 
	 * @param zug - der zu sendende Zug
	 * @return 0 falls Zug erfolgreich gesetzt werden konnte -1 falls der Zug
	 *         außerhalb des Spielfeldes liegt -2 falls der Zug bereits gesetzt
	 *         wurde -3 falls Clientsocket bereits existiert
	 * @throws IOException
	 * @throws UnknownHostException
	 */
	public int setMeinZug(int zug) throws IOException, UnknownHostException {
		// Setze Zug, wenn nicht korrekt gesetzt worden konnte, wird 0 zurückgegeben
		int status;
		if ((status = super.setZug(zug, SPIELER2)) != 0)
			return status;
		if (client != null && !client.isClosed())
			return -3;

		client = new Socket(ipAdresse, PORT);
		OutputStream out = client.getOutputStream();
		out.write(zug);
		gui.setXor0(zug, '0');
		;
		gui.repaint();
		return 0;
	}

	// Speichert die Gui als Membervariable
	private void setGui(TicTacToeJFrame gui) {
		this.gui = gui;
	}

}
