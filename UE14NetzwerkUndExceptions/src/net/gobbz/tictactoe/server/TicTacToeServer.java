package net.gobbz.tictactoe.server;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import net.gobbz.tictactoe.TicTacToe;

public class TicTacToeServer extends TicTacToe {

	private Socket       clientSocket;
	@SuppressWarnings("unused")
	private static int   FELDGROESSE, PORT;
	private ServerSocket server;

	@SuppressWarnings("static-access")
	/**
	 * Parameterbehafteter Konstruktor, der das Spielfeld anlegt und den
	 * ServerSocket erstellt
	 * 
	 * @param FELDGROESSE - des Spielfeldes
	 * @param PORT        - auf dem der Server läuft
	 * @throws IOException - wenn ServerSocket nicht erstellt werden kann
	 */
	public TicTacToeServer(int FELDGROESSE, int PORT) throws IOException {
		super(FELDGROESSE);
		this.FELDGROESSE = FELDGROESSE;
		this.PORT = PORT;
		this.server = null;
	}

	/**
	 * Legt zuerst einen ServerSocket an, gibt das Spielfeld aus und wartet auf den
	 * Zug des Clients. Nachdem dieser empfangen wurde, wird der Zug des Servers
	 * gesetzt und an den Client zurück geschickt. Dies wird solange gemacht, bis
	 * einer der Spieler gewonnen hat, oder erkannt wurde, dass niemand mehr das
	 * Spiel gewinnen kann. In diesen Fällen wird das Programm beendet
	 * 
	 * @param Args
	 */
	public static void main(String[] Args) {
		TicTacToeServer ticTacToeServer = null;
		try {
			ticTacToeServer = new TicTacToeServer(3, 60000);

			ticTacToeServer.server = new ServerSocket(60000);
			ticTacToeServer.behandleClient();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			//@formatter:off
			try { ticTacToeServer.close(); } catch (Exception e) { ; }
			//@formatter:on
		}
	}

	private void behandleClient() throws IOException {
		boolean isGameRunning    = true;
		boolean Spieler1isActive = false;

		while (isGameRunning) {
			// Ausgabe des Spielfeldes
			System.out.println(super.toString());

			if (Spieler1isActive) {
				// Abfrage des Spielzuges
				int zug = -1;
				do {
					zug = readInt("1. Spieler: Ihr Zug: ");
				} while (zug < 0 || zug > feldgroesse * feldgroesse);
				this.setMeinZug(zug);
			} else {
				getGegnerZug();
			}

			// Kontrolle, ob das Spiel noch weitergehen kann
			if (super.getGewonnen() != 0 || !super.getEinerKannGewinnen()) {
				System.out.println(this.toString());

				// Wenn Sieger gewonnen hat
				if (super.getGewonnen() != 0)
					System.out.println("Spieler " + (Spieler1isActive ? "1" : "2") + " hat gewonnen!!!");
				else // Wenn keiner gewonnen hat
					System.out.println("Es kann keiner mehr gewinnen");

				// Spiel läuft nicht mehr
				isGameRunning = false;
			}
			// Spielerwechsel
			Spieler1isActive = !Spieler1isActive;
		}
		// Schließe alle Sockets
		this.close();
	}

	/**
	 * Schließt den internen ServerSocket und ClientSocket
	 * 
	 * @throws IOException
	 */
	private void close() throws IOException {
		this.server.close();
		this.clientSocket.close();
	}

	/**
	 * Es wird über den bereits vorhandenen ClientSocket der Zug des Servers an den
	 * Client geschickt.
	 * 
	 * @param zug
	 * @return
	 * @throws IOException
	 */
	private int setMeinZug(int zug) throws IOException {
		OutputStream out = clientSocket.getOutputStream();

		out.write(zug);
		clientSocket.close();
		return super.setZug(zug, SPIELER1);
	}

	/**
	 * @formatter:off
	 * Wartet dass der Client die Verbindung mit dem Server aufnimmt und einen Zug
	 * sendet. Wenn der Zug gesendet wurde, wird der ClientSocket nicht geschlossen,
	 * denn der Server analysiert den Zug, trägt ihn ins Spielfeld ein und schickt
	 * seinen Zug über denselben ClientSocket zurück zum Client
	 * 
	 * @return 0 falls erfolgreich empfangen oder Zug 0 geschickt
	 *         -1 falls der empfangene Zug außerhalb des Spielfeldes liegt
	 *         -2 falls der empfangene Zug bereits gesetzt wurde
	 *         -3 falls Clientsocket bereits existiert
	 * @throws IOException - falls beim Erstellen des Clientsockets ein Fehler
	 *                     aufgetreten ist
	 * @formatter:on
	 */
	private int getGegnerZug() throws IOException {
		// Kontrolliere, ob noch kein Socket offen ist
		if (clientSocket != null && !clientSocket.isClosed())
			return -3;

		// Erstelle neuen Socket
		clientSocket = this.server.accept();
		InputStream in = clientSocket.getInputStream();

		// LeseZug
		int zug = (byte) in.read();
		return super.setZug(zug, SPIELER2);
	}

	@SuppressWarnings("resource")
	private static int readInt(String text) {
		System.out.print(text);
		return new java.util.Scanner(System.in).nextInt();
	}

}
