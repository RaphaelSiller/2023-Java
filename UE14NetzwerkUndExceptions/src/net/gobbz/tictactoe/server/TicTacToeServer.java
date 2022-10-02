package net.gobbz.tictactoe.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import net.gobbz.tictactoe.TicTacToe;

public class TicTacToeServer extends TicTacToe {

	private Socket clientSocket;
	private static int FELDGROESSE, PORT;
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
		TicTacToeServer server = null;
		try {
			server = new TicTacToeServer(3, 63000);
			//TODO: Hier Seite 7 von Theorie Unterlagen
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

}
